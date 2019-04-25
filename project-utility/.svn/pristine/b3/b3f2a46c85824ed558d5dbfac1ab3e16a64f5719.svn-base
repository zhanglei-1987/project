package cn.quickly.project.utility.io;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Directory {

	public static File temporary() {
		return new File(System.getProperty("java.io.tmpdir"));
	}

	public static File user() {
		return new File(System.getProperty("user.dir"));
	}

	public static File[] list(String directory, boolean recursion) {
		return list(new File(directory), recursion);
	}

	public static File[] list(File directory, boolean recursion) {

		Set<File> files = new LinkedHashSet<File>();

		if (directory.isDirectory()) {

			for (File file : Files.list(directory)) {

				if (file.isDirectory()) {

					files.add(file);

					if (recursion) {

						files.addAll(Arrays.asList(list(file, recursion)));

					}

				}

			}

		}

		return files.toArray(new File[files.size()]);
	}

	public static File create(String name) {

		File file = new File(name);

		if (!file.exists()) {

			file.mkdirs();
		}

		return file;
	}

	public static File create(String parent, String name) {

		File file = new File(parent, name);

		if (!file.exists()) {

			file.mkdirs();
		}

		return file;
	}

	public static File create(File parent, String name) {

		File file = new File(parent, name);

		if (!file.exists()) {

			file.mkdirs();
		}

		return file;

	}

}
