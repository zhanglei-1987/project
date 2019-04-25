package cn.quickly.project.utility.io;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public final class Files {

	private Files() {
		throw new UnsupportedOperationException();
	}

	public static File[] list(File file) {

		File[] children = file.listFiles();

		if (children != null) {

			return children;

		}

		return new File[0];

	}

	public static File[] list(File file, boolean recursion) {
		return filter(file, ".*", recursion);
	}

	public static File[] filter(String file, String matcher, boolean recursion) {
		return filter(new File(file), matcher, recursion);
	}

	public static File[] filter(File file, String matcher, boolean recursion) {

		Set<File> files = new LinkedHashSet<File>();

		if (file.getName().matches(matcher)) {
			files.add(file);
		}

		if (file.isDirectory()) {

			for (File itemFile : list(file)) {

				if (itemFile.getName().matches(matcher)) {
					files.add(itemFile);
				}

				if (recursion && file.isDirectory()) {
					files.addAll(Arrays.asList(filter(itemFile, matcher, recursion)));
				}

			}

		}

		return files.toArray(new File[files.size()]);
	}

	public static File[] suffix(File file, String matcher, boolean recursion) {
		return filter(file, ".*\\." + matcher, recursion);
	}

	public static File[] suffix(String file, String matcher, boolean recursion) {
		return suffix(new File(file), matcher, recursion);
	}

	public static String[] filtern(String file, String matcher, boolean recursion) {
		return filtern(new File(file), matcher, recursion);
	}

	public static String[] filtern(File file, String matcher, boolean recursion) {
		File[] files = filter(file, matcher, recursion);
		String[] names = new String[files.length];
		for (int i = 0, len = files.length; i < len; i++) {
			names[i] = files[i].getName();
		}
		return names;
	}

	public static String[] suffixn(File file, String matcher, boolean recursion) {
		return filtern(file, ".*\\." + matcher, recursion);
	}

	public static String[] suffixn(String file, String matcher, boolean recursion) {
		return suffixn(new File(file), matcher, recursion);
	}

	public static void move(File source, File target, boolean override) {

		if (target.isDirectory()) {

			if (source.isDirectory()) {

				for (File file : list(source)) {

					move(file, new File(target, source.getName()), override);

				}

				source.deleteOnExit();

			} else {

				File dst = new File(target, source.getName());

				if (override || !dst.exists()) {
					source.renameTo(dst);
				}

			}

		} else if (!target.exists()) {

			target.mkdirs();

			if (source.isDirectory()) {

				for (File file : list(source)) {

					move(file, target, override);

				}

				source.deleteOnExit();

			} else {

				move(source, target, override);

			}

		}

	}

	public static void copy(File source, File target, boolean override) {

		if (target.isDirectory()) {

			if (source.isDirectory()) {

				for (File file : list(source)) {

					copy(file, new File(target, source.getName()), override);

				}

			} else {

				File dst = new File(target, source.getName());

				if (override || !dst.exists()) {

					try {
						Streams.copy(source, dst);
					} catch (IOException e) {
					}

				}

			}

		} else if (!target.exists()) {

			target.mkdirs();

			if (source.isDirectory()) {

				for (File file : list(source)) {

					copy(file, target, override);

				}

			} else {

				copy(source, target, override);

			}

		}

	}

	public static void delete(File source) {

		if (source.isDirectory()) {

			for (File file : list(source)) {

				delete(file);

			}

		} else {

			source.deleteOnExit();

		}

	}

}
