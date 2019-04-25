package cn.quickly.project.utility.reflect;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;

import cn.quickly.project.utility.compress.Jars;
import cn.quickly.project.utility.io.Files;
import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.ClassLoaders;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.net.Urls;

public class ClassScanner {

	private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

	private String[] packageNames;

	private ClassMatcher classMatcher;

	private String[] jarPaths;

	private String charset = Charset.defaultCharset().displayName();

	private boolean scanJarOnly = false;

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public String[] getPackageNames() {
		return packageNames;
	}

	public void setPackageNames(String... packageNames) {
		this.packageNames = packageNames;
	}

	public ClassMatcher getClassMatcher() {
		return classMatcher;
	}

	public void setClassMatcher(ClassMatcher classMatcher) {
		this.classMatcher = classMatcher;
	}

	public String[] getJarPaths() {
		return jarPaths;
	}

	public void setJarPaths(String... jarPaths) {
		this.jarPaths = jarPaths;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public boolean isScanJarOnly() {
		return scanJarOnly;
	}

	public void setScanJarOnly(boolean scanJarOnly) {
		this.scanJarOnly = scanJarOnly;
	}

	public Set<Class<?>> scan() {

		Set<Class<?>> classes = new HashSet<Class<?>>();

		for (String packageName : packageNames == null ? new String[] { "" } : packageNames) {

			String path = packageName.replace('.', '/');

			if (!scanJarOnly) {
				scanClassloader(classes, packageName, path);
			}

			if (jarPaths != null) {
				scanJarFiles(classes, packageName, jarPaths);
			}

		}

		return classes;

	}

	protected void scanJarFiles(Set<Class<?>> classes, String packageName, String[] jarPaths) {

		for (String jarPath : jarPaths) {

			if (Urls.isUrl(jarPath)) {

				scanJarFile(classes, packageName, Streams.input(Urls.url(jarPath)), ClassLoaders.url(classLoader, jarPath));

			} else {

				File jar = new File(jarPath);

				if (!jar.exists()) {
					continue;
				}

				if (jar.isDirectory()) {

					scanJarFiles(classes, packageName, Files.suffixn(jar, "jar", true));

				} else if (jarPath.endsWith(".jar")) {

					String path = jarPath.startsWith("file:/") ? jarPath : Strings.concat("file:/", jarPath);

					scanJarFile(classes, packageName, Streams.input(jar), ClassLoaders.url(classLoader, path));

				}

			}

		}

	}

	protected void scanJarFile(Set<Class<?>> classes, String packageName, InputStream in, ClassLoader loader) {

		for (JarEntry entry : Jars.entries(in)) {

			if (!entry.isDirectory()) {

				String fileName = entry.getName();

				if (fileName.endsWith(".class")) {

					String className = Strings.prefix(fileName, ".class").replace('/', '.');

					if (!className.startsWith(packageName)) {
						continue;
					}

					Class<?> findedClass = Classes.getClass(className, loader);

					if (findedClass != null && classMatcher.isMatch(findedClass)) {
						classes.add(findedClass);
					}

				}

			}

		}

	}

	protected void scanClassloader(Set<Class<?>> classes, String packageName, String path) {

		try {

			Enumeration<?> enumeration = classLoader.getResources(path);

			while (enumeration.hasMoreElements()) {

				URL url = (URL) enumeration.nextElement();

				if ("jar".equalsIgnoreCase(url.getProtocol())) {

					URL jarUrl = new URL(Strings.prefix(url.getFile(), "!"));

					scanJarFile(classes, packageName, jarUrl.openStream(), classLoader);

				} else {

					File dir = new File(URLDecoder.decode(url.getFile(), charset));

					scanFile(classes, packageName, dir);

				}

			}

		} catch (Throwable e) {
		}

	}

	protected void scanFile(Set<Class<?>> classes, String packageName, File file) {

		if (!file.isDirectory()) {

			String fileName = file.getName();

			if (fileName.endsWith(".class")) {

				String className = Strings.concat(packageName, ".", Strings.prefix(fileName, ".class"));

				Class<?> findedClass = Classes.getClass(className, classLoader);

				if (findedClass != null && classMatcher.isMatch(findedClass)) {
					classes.add(findedClass);
				}

			}

		} else {

			for (File childFile : Files.list(file)) {

				StringBuilder basePackageName = new StringBuilder(packageName);

				if (childFile.isDirectory()) {

					if (!Strings.isEmpty(packageName)) {

						basePackageName.append(".");

					}

					basePackageName.append(childFile.getName());

				}

				scanFile(classes, basePackageName.toString(), childFile);
			}

		}

	}
}
