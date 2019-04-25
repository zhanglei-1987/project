package cn.quickly.project.utility.lang;

import java.net.URL;
import java.net.URLClassLoader;

import cn.quickly.project.utility.reflect.Compat;

public class ClassLoaders {

	public static ClassLoader system() {
		return ClassLoader.getSystemClassLoader();
	}

	public static URLClassLoader url(String... urls) {
		return URLClassLoader.newInstance(Compat.cast(urls, URL[].class));
	}

	public static URLClassLoader url(ClassLoader parent, String... urls) {
		return URLClassLoader.newInstance(Compat.cast(urls, URL[].class), parent);
	}

}
