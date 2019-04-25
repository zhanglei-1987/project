package cn.quickly.project.utility.reflect;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class Classes {

	private Classes() {
		throw new UnsupportedOperationException();
	}

	public static Class<?> getClass(String className) {
		try {
			return Class.forName(className);
		} catch (Throwable e) {
		}
		return null;
	}

	public static Class<?> getClass(String className, ClassLoader loader) {
		try {
			return Class.forName(className, true, loader);
		} catch (Throwable e) {
		}
		return null;
	}

	public static Class<?> getClass(Set<String> classNames) {

		for (String className : classNames) {

			Class<?> targetClass = getClass(className);

			if (targetClass != null) {
				return targetClass;
			}

		}

		return null;

	}

	public static Class<?> getClass(Set<String> classNames, ClassLoader loader) {

		for (String className : classNames) {

			Class<?> targetClass = getClass(className, loader);

			if (targetClass != null) {
				return targetClass;
			}

		}

		return null;

	}

	public static Class<?>[] getClasses(Object... objects) {

		if (objects != null) {

			Class<?>[] classes = new Class<?>[objects.length];

			for (int i = 0, len = objects.length; i < len; i++) {

				if (objects[i] != null) {

					classes[i] = objects[i].getClass();

				}

			}

			return classes;
		}

		return null;
	}

	public static boolean isSuperClass(Class<?> superClass, Class<?> targetClass) {

		if (superClass == null || targetClass == null) {
			return false;
		}

		return superClass.isAssignableFrom(targetClass);

	}

	public static String getJarPath(Class<?> targetClass) {

		return targetClass.getProtectionDomain().getCodeSource().getLocation().getPath();

	}

	public static Class<?> defineClass(String className, byte[] data) {

		Map<String, byte[]> classBytesMap = new HashMap<String, byte[]>(1);
		classBytesMap.put(className, data);

		CustomClassloader classloader = new CustomClassloader(classBytesMap);

		try {
			return classloader.loadClass(className);
		} catch (ClassNotFoundException e) {
			throw Exceptions.argument(e);
		}

	}

	public static byte[] lookupClass(String className) {

		InputStream in = StreamSeeker.classloader(className.replace('.', '/') + ".class");

		try {

			try {
				return Streams.getBytes(in);
			} finally {
				Quiet.close(in);
			}

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static byte[] lookupClass(Class<?> targetClass) {
		return lookupClass(targetClass.getName());
	}

	public static Type[] getGenericTypes(Class<?> type) {

		Type superClass = type.getGenericSuperclass();

		if (superClass == null || superClass instanceof Class<?>) {

			return null;

		}

		return ((ParameterizedType) superClass).getActualTypeArguments();

	}

	public static Class<?> getUnwrapperType(Class<?> type) {

		try {

			Field field = Fields.getField(Integer.class, "TYPE", true);

			if (field != null) {

				return (Class<?>) field.get(null);

			}

		} catch (Exception e) {
		}

		return type;

	}

	public static Set<Class<?>> getInterfaces(Class<?> type) {

		Set<Class<?>> interfaces = new LinkedHashSet<>();

		Class<?>[] basics = type.getInterfaces();

		if (basics != null && basics.length > 0) {

			interfaces.addAll(Arrays.asList(basics));

		}

		Class<?> superClass = type.getSuperclass();

		if (superClass != null) {

			interfaces.addAll(getInterfaces(superClass));

		}

		return interfaces;

	}

	public static Set<Type> getGenericInterfaces(Class<?> type) {

		Set<Type> interfaces = new LinkedHashSet<>();

		Type[] basics = type.getGenericInterfaces();

		if (basics != null && basics.length > 0) {

			interfaces.addAll(Arrays.asList(basics));

		}

		Class<?> superClass = type.getSuperclass();

		if (superClass != null) {

			interfaces.addAll(getGenericInterfaces(superClass));

		}

		return interfaces;

	}

}
