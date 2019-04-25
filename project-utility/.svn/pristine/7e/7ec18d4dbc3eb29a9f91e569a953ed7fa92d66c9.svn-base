package cn.quickly.project.utility.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.reflect.Caller;
import cn.quickly.project.utility.reflect.Classes;

public final class StreamSeeker {

	private static final String CLASSPATH = "classpath:";

	private static final String FILE = "file:";

	private static final String HTTP = "http:";

	private static final String FTP = "ftp:";

	private static final String FIND = "find:";

	private StreamSeeker() {
		throw new UnsupportedOperationException();
	}

	public static InputStream classpath(String name) {
		return classpath(Caller.before(StreamSeeker.class), name);
	}

	public static InputStream classpath(Class<?> targetClass, String name) {
		return targetClass.getResourceAsStream(name);
	}

	public static InputStream jarpath(Class<?> targetClass, String name) {

		File file = new File(Classes.getJarPath(targetClass)).getParentFile();

		String filepath = Strings.concat(file.getAbsolutePath(), File.separator, name);

		return file(filepath);

	}

	public static InputStream file(String name) {
		try {
			return new FileInputStream(name);
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	public static InputStream file(File file) {
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
		}
		return null;
	}

	public static InputStream classloader(String name) {

		InputStream is = classpath(name);

		if (is == null) {

			is = ClassLoader.getSystemResourceAsStream(name);

		}

		if (is == null && name.charAt(0) != '/') {

			is = classpath("/" + name);

		}

		return is;

	}

	public static InputStream url(String url) {
		try {
			return new URL(url).openStream();
		} catch (Exception e) {
		}
		return null;
	}

	public static InputStream packages(String name, Package[] packages) {

		for (Package iPackage : packages) {

			String path = iPackage.getName().replace(".", "/");

			int index = -1, offset = path.length();

			while ((index = path.lastIndexOf("/", offset)) > 0) {

				InputStream is = classloader(Strings.concat("/", path, "/", name));

				if (is != null) {
					return is;
				}

				path = path.substring(0, index);

			}

		}

		return null;
	}

	public static InputStream directory(String name, File dir) {

		if (dir == null) {
			return null;
		}

		File[] children = dir.listFiles();

		if (children != null) {

			for (File child : children) {

				if (child.getName().equals(name)) {

					return file(child);

				} else if (child.isDirectory()) {

					File target = new File(child, name);

					if (target.exists()) {

						return file(target);

					}

				}

			}
		}

		return directory(name, dir.getParentFile());

	}

	public static InputStream find(String name) {

		InputStream is = null;

		if (name.startsWith(FIND)) {

			String path = name.substring(FIND.length());

			is = classloader(path);

			if (is == null) {

				is = directory(Strings.suffix(path, File.separator), new File(StreamSeeker.class.getResource("/").getFile()));

			}

			if (is == null) {

				is = packages(path, Package.getPackages());

			}

		} else if (name.startsWith(CLASSPATH)) {

			is = classloader(name.substring(CLASSPATH.length()));

		} else if (name.startsWith(FILE)) {

			is = file(name.substring(FILE.length()));

		} else if (name.startsWith(HTTP) || name.startsWith(FTP)) {

			is = url(name);

		} else {

			is = file(name);

			if (is == null) {

				is = classloader(name);

			}

		}

		Assert.isNotNull(is, Strings.concat("not found input stream through '", name, "'."));

		return is;
	}

}
