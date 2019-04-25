package cn.quickly.project.utility.compress;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import cn.quickly.project.utility.io.Files;
import cn.quickly.project.utility.io.Serialization;
import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.map.Maps;

public final class Zips {

	private Zips() {
		throw new UnsupportedOperationException();
	}

	public static ZipInputStream input(InputStream in, String charset) {

		return in instanceof ZipInputStream ? (ZipInputStream) in : new ZipInputStream(in, Charset.forName(charset));

	}

	public static ZipOutputStream output(OutputStream out, String charset) {

		return out instanceof ZipOutputStream ? (ZipOutputStream) out : new ZipOutputStream(out, Charset.forName(charset));

	}

	public static void zip(File zip, File dir, String charset) {

		Assert.isTrue(dir.isDirectory(), "the second argument type must be directory");

		Map<String, File> files = new LinkedHashMap<String, File>();

		for (File file : Files.list(dir, true)) {

			String name = Strings.suffix(file.getAbsolutePath(), dir.getAbsolutePath());

			name = name.replace("\\", "/").substring(1);

			if (!file.isDirectory()) {
				files.put(name, file);
			}

		}

		zip(zip, files, charset);

	}

	public static void zip(File zip, File[] files, String charset) {

		zip(zip, Maps.flat(files, String.class, "name"), charset);

	}

	public static void zip(File zip, Map<?, ?> files, String charset) {
		zip(Streams.output(zip), files, charset);
	}

	public static byte[] zip(Map<?, ?> files, String charset) {
		ByteArrayOutputStream out = Streams.output();
		zip(out, files, charset);
		return out.toByteArray();
	}

	public static void zip(OutputStream out, Map<?, ?> files, String charset) {

		try {
			ZipOutputStream zip = output(out, charset);
			try {
				for (Map.Entry<?, ?> entry : files.entrySet()) {

					Object key = entry.getKey();

					if (key instanceof ZipEntry) {

						zip.putNextEntry((ZipEntry) key);

					} else if (key instanceof String) {

						ZipEntry zipEntry = new ZipEntry((String) key);
						zip.putNextEntry(zipEntry);

					} else {
						continue;
					}

					Object value = entry.getValue();

					if (value instanceof byte[]) {
						zip.write((byte[]) value);
					} else if (value instanceof InputStream) {
						Streams.copy((InputStream) value, zip);
					} else if (value instanceof File) {
						Streams.copy((File) value, zip);
					} else if (value instanceof Serializable) {
						zip.write(Serialization.serialize(value));
					}

					zip.flush();
					zip.closeEntry();

				}
			} finally {
				Quiet.close(zip);
			}
		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static byte[] unzip(byte[] zip, String filename, String charset) {

		try {

			ByteArrayOutputStream out = Streams.output();

			unzip(Streams.input(zip), filename, out, charset);

			return out.toByteArray();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static void unzip(File file, File dir, String charset) {

		Assert.isTrue(dir.isDirectory(), "the second argument type must be directory");

		try {

			ZipInputStream zip = new ZipInputStream(Streams.input(file), Charset.forName(charset));

			try {
				while (true) {

					ZipEntry zipEntry = zip.getNextEntry();

					if (zipEntry == null) {
						break;
					} else if (zipEntry.isDirectory()) {
						new File(dir, zipEntry.getName()).mkdirs();
					} else {

						Streams.copy(zip, new File(dir, zipEntry.getName()));

					}
				}
			} finally {
				Quiet.close(zip);
			}
		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static byte[] unzip(File zip, String filename, String charset) {

		ByteArrayOutputStream out = Streams.output();

		unzip(Streams.input(zip), filename, out, charset);

		return out.toByteArray();

	}

	public static void unzip(File zip, String filename, File file, String charset) {

		unzip(Streams.input(zip), filename, Streams.output(zip), charset);

	}

	public static void unzip(InputStream in, String filename, OutputStream out, String charset) {

		try {

			ZipInputStream zip = input(in, charset);

			try {
				for (;;) {

					ZipEntry zipEntry = zip.getNextEntry();

					if (zipEntry == null) {
						break;
					} else if (zipEntry.isDirectory()) {
						continue;
					}

					if (zipEntry.getName().equals(filename)) {
						Streams.copy(zip, out);
						break;
					}

				}
			} finally {
				Quiet.close(zip);
				Quiet.close(out);
				Quiet.close(in);
			}
		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static Map<ZipEntry, byte[]> unzip(File file, String charset) {
		return unzip(Streams.input(file), charset);
	}

	public static Map<ZipEntry, byte[]> unzip(byte[] data, String charset) {
		return unzip(Streams.input(data), charset);
	}

	public static Map<ZipEntry, byte[]> unzip(InputStream in, String charset) {

		Map<ZipEntry, byte[]> map = new LinkedHashMap<ZipEntry, byte[]>();

		ZipInputStream zip = input(in, charset);

		try {

			for (;;) {

				try {

					ZipEntry entry = zip.getNextEntry();

					if (entry == null) {
						break;
					}

					map.put(entry, Streams.getBytes(zip));

				} catch (Throwable e) {
				}

			}

		} finally {
			Quiet.close(zip);
		}

		return map;

	}

	public static Set<ZipEntry> entries(InputStream in, String charset) {

		Set<ZipEntry> entries = new HashSet<ZipEntry>();

		ZipInputStream zip = input(in, charset);

		try {

			for (;;) {

				try {

					ZipEntry entry = zip.getNextEntry();

					if (entry == null) {
						break;
					}

					entries.add(entry);

				} catch (Throwable e) {
				}

			}

		} finally {
			Quiet.close(zip);
		}

		return entries;

	}

	public static Set<ZipEntry> entries(File file, String charset) {
		return entries(Streams.input(file), charset);
	}

	public static void forEach(ZipInputStream input, Consumer<ZipEntry> consumer) throws IOException {

		for (;;) {

			ZipEntry entry = input.getNextEntry();

			if (entry == null) {
				break;
			}

			consumer.accept(entry);

		}

	}

}
