package cn.quickly.project.utility.compress;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;

import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class Jars {

	private Jars() {
		throw new UnsupportedOperationException();
	}

	public static JarInputStream input(InputStream in) {

		try {

			return in instanceof JarInputStream ? (JarInputStream) in : new JarInputStream(in);

		} catch (IOException e) {

			throw Exceptions.argument(e);

		}

	}

	public static JarOutputStream output(OutputStream out) {

		try {

			return out instanceof JarOutputStream ? (JarOutputStream) out : new JarOutputStream(out);

		} catch (IOException e) {

			throw Exceptions.argument(e);

		}

	}

	public static Set<JarEntry> entries(InputStream in) {

		Set<JarEntry> entries = new HashSet<>();

		JarInputStream jar = input(in);

		try {

			for (;;) {

				try {

					JarEntry entry = jar.getNextJarEntry();

					if (entry == null) {
						break;
					}

					entries.add(entry);

				} catch (Throwable e) {
				}

			}

		} finally {
			Quiet.close(jar);
		}

		return entries;

	}

	public static Set<JarEntry> entries(File file) {
		return entries(Streams.input(file));
	}

	public static void forEach(JarInputStream input, Consumer<JarEntry> consumer) throws IOException {

		for (;;) {

			JarEntry entry = input.getNextJarEntry();

			if (entry == null) {
				break;
			}

			consumer.accept(entry);

		}

	}

}
