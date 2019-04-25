package cn.quickly.project.utility.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.io.Streams;

public final class Bencoding {

	static int read_length(PushbackInputStream in) throws IOException {
		byte[] buf = Streams.getBytes(in, new byte[] { ':' });
		return Integer.parseInt(new String(buf));
	}

	static byte[] read_binary(PushbackInputStream in) throws IOException {
		return Streams.getBytes(in, read_length(in));
	}

	static String read_string(PushbackInputStream in, String charset) throws IOException {
		return new String(read_binary(in));
	}

	static Integer read_integer(PushbackInputStream in, String charset) throws IOException {
		return Integer.parseInt(new String(Streams.getBytes(in, new byte[] { 'e' })));
	}

	static Object read_object(PushbackInputStream in, String charset, Set<String> binaryFields, boolean binary) throws IOException {
		char c = (char) in.read();
		if (c == 'd') {
			return read_dictionary(in, charset, binaryFields, binary);
		} else if (c == 'l') {
			return read_list(in, charset, binaryFields, binary);
		} else if (c == 'i') {
			return read_integer(in, charset);
		} else if (c >= '0' && c <= '9') {
			in.unread(c);
			if (binary) {
				return Hex.lower(read_binary(in));
			} else {
				return read_string(in, charset);
			}
		} else {
			throw new IOException("not support data type " + (char) c);
		}
	}

	static List<Object> read_list(PushbackInputStream in, String charset, Set<String> binaryFields, boolean binary) throws IOException {
		List<Object> list = new LinkedList<Object>();
		for (int i = in.read(); i != -1; i = in.read()) {
			char c = (char) i;
			if (c == 'e') {
				break;
			} else {
				in.unread(c);
				list.add(read_object(in, charset, binaryFields, binary));
			}
		}
		return list;
	}

	static Map<String, Object> read_dictionary(PushbackInputStream in, String charset, Set<String> binaryFields, boolean binary) throws IOException {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		String key = null;

		for (int i = in.read(); i != -1; i = in.read()) {

			char c = (char) i;

			if (c == 'e') {
				break;
			} else {
				in.unread(c);

				if (key != null) {

					if (binaryFields != null) {
						binary = binaryFields.contains(key);
					}

					map.put(key, read_object(in, charset, binaryFields, binary));

					key = null;

				} else {
					key = new String(read_binary(in));
				}

			}
		}
		return map;
	}

	public static Object decode(InputStream in, String charset) {
		return decode(in, charset, null);
	}

	public static Object decode(InputStream in, String charset, Set<String> binaryFields) {
		try {
			PushbackInputStream is = new PushbackInputStream(in);
			return read_object(is, charset, binaryFields, false);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Object decode(byte[] in, String charset) {
		return decode(in, charset, null);
	}

	public static Object decode(byte[] in, String charset, Set<String> binaryFields) {
		return decode(new ByteArrayInputStream(in), charset, binaryFields);
	}

	static void write_length(OutputStream out, int length) throws IOException {
		out.write(String.valueOf(length).getBytes());
		out.write(':');
	}

	static void write_binary(OutputStream out, byte[] data) throws IOException {
		write_length(out, data.length);
		out.write(data);
	}

	static void write_string(OutputStream out, String text, String charset) throws IOException {
		byte[] data = text.getBytes(charset);
		write_length(out, data.length);
		out.write(data);
	}

	static void write_integer(OutputStream out, Integer n) throws IOException {
		String text = n.toString();
		out.write('i');
		out.write(text.getBytes());
		out.write('e');
	}

	static void write_list(OutputStream out, Object[] list, String charset) throws IOException {
		out.write('l');
		for (Object value : list) {
			write_object(out, value, charset);
		}
		out.write('e');
	}

	static void write_dictionary(OutputStream out, Map<?, ?> dict, String charset) throws IOException {
		out.write('d');
		for (Map.Entry<?, ?> entry : dict.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (key != null && value != null) {
				write_object(out, key, charset);
				write_object(out, value, charset);
			}
		}
		out.write('e');
	}

	static void write_object(OutputStream out, Object value, String charset) throws IOException {
		if (value != null) {

			if (value instanceof byte[]) {
				write_binary(out, (byte[]) value);
			} else if (value instanceof String) {
				write_string(out, (String) value, charset);
			} else if (value instanceof Integer) {
				write_integer(out, (Integer) value);
			} else if (value.getClass().isArray()) {
				write_list(out, Arrays.cast(value, Object.class), charset);
			} else if (value instanceof Collection<?>) {
				write_list(out, ((Collection<?>) value).toArray(), charset);
			} else if (value instanceof Map<?, ?>) {
				write_dictionary(out, (Map<?, ?>) value, charset);
			} else {
				throw new IOException("not support encode type " + value.getClass());
			}
		}
	}

	public static byte[] encode(Object value, String charset) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			write_object(out, value, charset);
			return out.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
