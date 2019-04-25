package cn.quickly.project.utility.compress;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class Gzips {

	private Gzips() {
		throw new UnsupportedOperationException();
	}

	public static void gzip(File gzip, File file) {

		try {

			GZIPOutputStream out = new GZIPOutputStream(Streams.output(gzip));

			try {
				Streams.copy(file, out);
				out.flush();
				out.finish();
			} finally {
				Quiet.close(out);
			}

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static void ungzip(File gzip, File file) {

		try {

			GZIPInputStream in = new GZIPInputStream(Streams.input(gzip));

			try {
				Streams.copy(in, file);
			} finally {
				Quiet.close(in);
			}

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static byte[] gzip(byte[] source) {

		ByteArrayOutputStream out = Streams.output();

		try {

			GZIPOutputStream writer = new GZIPOutputStream(out);

			try {
				writer.write(source);
				writer.flush();
				writer.finish();
			} finally {
				Quiet.close(writer);
			}

			return out.toByteArray();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}
	}

	public static byte[] ungzip(byte[] source) {
		try {

			GZIPInputStream in = new GZIPInputStream(Streams.input(source));

			try {
				return Streams.getBytes(in);
			} finally {
				Quiet.close(in);
			}

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}
	}

}
