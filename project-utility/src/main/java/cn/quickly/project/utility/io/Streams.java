package cn.quickly.project.utility.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.quickly.project.utility.collection.Collections;
import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;
import cn.quickly.project.utility.lang.Strings;

public final class Streams {

	private Streams() {
		throw new UnsupportedOperationException();
	}

	public static byte[] getBytes(File file) throws IOException {

		Assert.isNotNull(file, "the file is required.");

		return getBytes(input(file));

	}

	public static byte[] getBytes(URL url) throws IOException {

		Assert.isNotNull(url, "the url is required.");

		InputStream in = url.openStream();

		try {
			return getBytes(in);
		} finally {
			Quiet.close(in);
		}

	}

	public static byte[] getBytes(InputStream in) throws IOException {

		Assert.isNotNull(in, "the input stream is required.");

		try {

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];

			int count = -1;

			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}

			return out.toByteArray();

		} finally {

			Quiet.close(in);

		}

	}

	public static byte[] getBytes(InputStream in, int length) throws IOException {

		Assert.isNotNull(in, "the input stream is required.");

		if (length < 0) {
			return new byte[0];
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream(length);

		int b = -1;

		while (length > 0 && (b = in.read()) != -1) {

			out.write(b);

			length--;

		}

		return out.toByteArray();

	}

	/**
	 * 读取数据，遇到指定数组中的任何字节将停止读取，读取到的数据不包含数组中的字节.
	 * 
	 * @param in
	 * @param ends
	 * @return
	 * @throws IOException
	 */

	public static byte[] getBytes(InputStream in, byte[] ends) throws IOException {

		Assert.isNotNull(in, "the input stream is required.");

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		out: for (int c = in.read(); c != -1; c = in.read()) {

			for (byte b : ends) {

				if (b == c) {
					break out;
				}

			}

			buffer.write(c);

		}

		return buffer.toByteArray();
	}

	public static byte[] getBytes(InputStream in, char start, char end) throws IOException {
		return getBytes(in, (byte) start, (byte) end);
	}

	public static byte[] getBytes(InputStream in, byte start, byte end) throws IOException {

		Assert.isNotNull(in, "the input stream is required.");

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		for (int count = -1, c = in.read(); c != -1; c = in.read()) {

			if (c == end) {
				--count;
			} else if (c == start) {

				if (count < 0) {
					count = 1;
				} else {
					++count;
				}

			}

			if (count >= 0) {
				buffer.write(c);
			}

			if (count == 0) {
				break;
			}

		}

		return buffer.toByteArray();
	}

	/**
	 * 对称搜索，获取字符串
	 * 
	 * @param reader
	 * @param start
	 *            左对称字符
	 * @param end
	 *            右对称字符
	 * @return
	 * @throws IOException
	 */
	public static String getString(Reader reader, char start, char end, boolean transferred) throws IOException {

		Assert.isNotNull(reader, "the reader is required.");

		StringBuilder buffer = new StringBuilder();

		for (int count = 1, c = reader.read(), p = c; c != -1; c = reader.read()) {

			if (!transferred || p != '\\') {

				if (c == end) {
					--count;
				} else if (c == start) {
					++count;
				}

			}

			if (count == 0) {
				break;
			}

			buffer.append((char) c);

			p = c;

		}

		if (buffer.length() > 0) {
			return buffer.toString();
		}

		return null;
	}

	public static String getString(Reader reader, char[] chars, boolean transferred) throws IOException {

		Assert.isNotNull(reader, "the reader is required.");

		StringBuilder buffer = new StringBuilder();

		out: for (int c = reader.read(), p = c; c != -1; c = reader.read()) {

			if (!transferred || p != '\\') {

				for (char ec : chars) {

					if (ec == c) {
						break out;
					}

				}

			}

			buffer.append((char) c);
		}

		if (buffer.length() > 0) {
			return buffer.toString();
		}

		return null;
	}

	public static String getString(Reader reader, int length) throws IOException {

		Assert.isNotNull(reader, "the reader is required.");

		if (length <= 0) {
			return null;
		}

		char[] buffer = new char[length];

		length = reader.read(buffer);

		return new String(buffer, 0, length);
	}

	public static String getString(InputStream in, String charset) throws IOException {
		return new String(getBytes(in), charset);
	}

	public static String getString(Reader reader) throws IOException {

		Assert.isNotNull(reader, "the reader is required.");

		StringBuilder builder = new StringBuilder();

		char[] buffer = new char[1024];

		int count = -1;

		while ((count = reader.read(buffer)) > 0) {
			builder.append(buffer, 0, count);
		}

		return builder.toString();

	}

	public static String[] getLines(RandomAccessFile in, int count, String charset) throws IOException {
		return getLines(in, count, "ISO-8859-1", charset);
	}

	public static String[] getLines(DataInput in, int count, String fromCharset, String toCharset) throws IOException {

		Assert.isNotNull(in, "the DataInput required.");

		Assert.isNotNull(fromCharset, "the fromCharset required.");

		Assert.isNotNull(toCharset, "the toCharset required.");

		Assert.isTrue(count > 0, "the line count must bigger than zero.");

		List<String> lines = new ArrayList<String>(count);

		for (int i = count; i > 0; i--) {

			String line = getLine(in, fromCharset, toCharset);

			if (Strings.isEmpty(line)) {
				break;
			}

			lines.add(line);

		}

		return Collections.toArray(String.class, lines);

	}

	public static String getLine(RandomAccessFile in, String charset) throws IOException {
		return getLine(in, "ISO-8859-1", charset);
	}

	public static String getLine(DataInput in, String fromCharset, String toCharset) throws IOException {

		String line = in.readLine();

		if (Strings.isEmpty(line)) {
			return null;
		}

		return new String(line.getBytes(fromCharset), toCharset);

	}

	public static void copy(byte[] bytes, int offset, int length, File file) throws IOException {

		Assert.isNotNull(file, "the copy to file is required.");

		if (file.isDirectory()) {
			throw new IOException(Strings.concat("can't copy bytes to a dir file - [", file, "]."));
		}

		FileOutputStream out = new FileOutputStream(file);

		try {
			out.write(bytes, offset, length);
		} finally {
			Quiet.close(out);
		}

	}

	public static void copy(byte[] bytes, File file) throws IOException {

		Assert.isNotNull(bytes, "the copy byte array is required.");

		copy(bytes, 0, bytes.length, file);

	}

	public static void copy(InputStream in, int length, File file) throws IOException {

		Assert.isNotNull(file, "the copy to file is required.");

		if (file.isDirectory()) {
			throw new IOException(Strings.concat("can't copy bytes to a dir file - [", file, "]."));
		}

		FileOutputStream out = output(file);

		try {
			copy(in, length, out);
		} finally {
			Quiet.close(out);
		}
	}

	public static void copy(InputStream in, int length, OutputStream out) throws IOException {
		copy(in, length, out, 2048);
	}

	public static void copy(InputStream in, int length, OutputStream out, int bufferSize) throws IOException {

		Assert.isNotNull(in, "the input stream is required.");

		Assert.isNotNull(in, "the output stream is required.");

		byte[] buffer = new byte[1024];

		int count = -1, sum = 0, offset = 0;

		while ((count = in.read(buffer)) > 0) {
			sum += count;
			offset = length - sum;
			if (offset >= 0) {
				out.write(buffer, 0, count);
			} else {
				out.write(buffer, 0, count + offset);
			}
			out.flush();
		}

	}

	public static void copy(File in, File out) throws IOException {
		FileInputStream input = new FileInputStream(in);
		try {
			copy(input, out);
		} finally {
			Quiet.close(input);
		}
	}

	public static void copy(File in, OutputStream out) throws IOException {
		FileInputStream input = input(in);
		try {
			copy(input, out);
		} finally {
			Quiet.close(input);
		}
	}

	public static void copy(InputStream in, File file) throws IOException {

		Assert.isNotNull(file, "the copy to file is required.");

		if (file.isDirectory()) {
			throw new IOException(Strings.concat("can't copy bytes to a dir file - [", file, "]."));
		}

		FileOutputStream out = new FileOutputStream(file);

		try {
			copy(in, out);
		} finally {
			Quiet.close(out);
		}
	}

	public static void copy(InputStream in, OutputStream out, boolean closed) throws IOException {

		try {
			copy(in, out);
		} finally {

			if (closed) {
				Quiet.close(in);
			}

		}

	}

	public static void copy(InputStream in, OutputStream out) throws IOException {

		copy(in, out, 2048);

	}

	public static void copy(InputStream in, OutputStream out, int bufferSize) throws IOException {

		Assert.isNotNull(in, "the input stream is required.");

		Assert.isNotNull(in, "the output stream is required.");

		byte[] buffer = new byte[bufferSize];

		int count = -1;
		while ((count = in.read(buffer)) > 0) {
			out.write(buffer, 0, count);
			out.flush();
		}

	}

	public static ByteArrayInputStream input(byte[] buffer) {
		return new ByteArrayInputStream(buffer);
	}

	public static ByteArrayOutputStream output(int size) {
		return new ByteArrayOutputStream(size);
	}

	public static ByteArrayOutputStream output() {
		return new ByteArrayOutputStream();
	}

	public static FileInputStream input(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw Exceptions.argument(e);
		}
	}

	public static FileOutputStream output(File file) {
		try {
			return new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			throw Exceptions.argument(e);
		}
	}

	public static InputStream input(URL url) {
		try {
			return url.openStream();
		} catch (Exception e) {
			throw Exceptions.argument(e);
		}
	}
}
