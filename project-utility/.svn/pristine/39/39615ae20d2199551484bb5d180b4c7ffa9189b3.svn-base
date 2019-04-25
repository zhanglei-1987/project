package cn.quickly.project.utility.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;
import cn.quickly.project.utility.lang.Strings;

public final class Bundles {

	private Bundles() {
		throw new UnsupportedOperationException();
	}

	public static Properties get(String resource, ClassLoader loader) {
		Properties properties = new Properties();

		try {

			Enumeration<URL> enumeration = loader.getResources(resource);

			while (enumeration.hasMoreElements()) {

				URL url = enumeration.nextElement();

				InputStream in = url.openStream();

				if (in != null) {

					try {
						if (resource.endsWith(".xml")) {
							properties.loadFromXML(in);
						} else {
							properties.load(in);
						}
					} catch (IOException e) {
					} finally {
						Quiet.close(in);
					}

				}
			}

		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return properties;
	}

	public static Properties get(String resource) {
		return get(resource, ClassLoader.getSystemClassLoader());
	}

	public static Properties get(File resource) {
		Properties properties = new Properties();

		InputStream in = null;

		try {

			in = new FileInputStream(resource);

			if (resource.getName().endsWith(".xml")) {
				properties.loadFromXML(in);
			} else {
				properties.load(in);
			}

		} catch (IOException e) {
			throw Exceptions.argument(e);
		} finally {
			Quiet.close(in);
		}
		return properties;
	}

	public static Properties get(String resource, String defaultResource, ClassLoader loader) {
		Properties properties = get(resource, loader);
		if (properties.isEmpty()) {
			properties.putAll(get(defaultResource, loader));
		}
		return properties;
	}

	public static Properties merge(String resource, String defaultResource) {
		return get(resource, defaultResource, ClassLoader.getSystemClassLoader());

	}

	public static Properties merge(String resource, Properties properties) {
		return merge(resource, properties, ClassLoader.getSystemClassLoader());
	}

	public static Properties merge(String resource, Properties properties, ClassLoader loader) {

		Properties bundles = get(resource);

		if (properties != null) {
			bundles.putAll(properties);
		}

		return bundles;
	}

	public static Properties find(String name) {

		Properties properties = new Properties();

		InputStream is = StreamSeeker.find(name);
		if (is != null) {
			try {
				properties.load(is);
			} catch (IOException e) {
				throw Exceptions.argument(e);
			} finally {
				Quiet.close(is);
			}
		}
		return properties;
	}

	public static Properties from(Map<String, ?> map, String... excludes) {

		Properties bundle = new Properties();

		for (Map.Entry<String, ?> entry : map.entrySet()) {
			Object value = entry.getValue();
			if (value != null) {
				bundle.put(entry.getKey(), Strings.valueOf(value));
			}
		}

		for (String exclude : excludes) {
			if (!Strings.isEmpty(exclude)) {
				bundle.remove(exclude);
			}
		}

		return bundle;

	}

	public static Properties match(Properties source, String pattern, boolean reject) {
		return from(Maps.match(Maps.from(source), pattern, reject));
	}
}
