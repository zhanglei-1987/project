package cn.quickly.project.utility.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.quickly.project.utility.annotation.Alias;
import cn.quickly.project.utility.annotation.Format;
import cn.quickly.project.utility.collection.Collections;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.text.Formats;
import cn.quickly.project.utility.time.Dates;

public final class Methods {

	private Methods() {
		throw new UnsupportedOperationException();
	}

	public static Type[] getComponentTypes(Method method, int index) {

		Type[] types = method.getGenericParameterTypes();

		if (types != null && types.length > index) {

			Type type = types[index];

			if (type instanceof ParameterizedType) {

				return ((ParameterizedType) type).getActualTypeArguments();

			}

		}

		return null;
	}

	public static Class<?>[] getComponentClasses(Method method, int index) {

		Type[] types = getComponentTypes(method, index);

		if (types != null && types.length > 0) {

			List<Class<?>> classes = new ArrayList<>(types.length);

			for (Type type : types) {

				if (type instanceof Class<?>) {

					classes.add((Class<?>) type);

				}

			}

			return Collections.toArray(Class.class, classes);

		}

		return null;

	}

	public static boolean isGetter(Method method) {
		String name = method.getName();
		return !"getClass".equals(name) && method.getParameterTypes().length == 0 && name.matches("(get|is).+");
	}

	public static Object getGetterValue(Object object, Method method) {

		try {

			Object value = method.invoke(object);

			Format format = method.getDeclaredAnnotation(Format.class);

			if (format != null) {

				value = Formats.format(value, format.pattern());

			}

			return value;

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static boolean isSetter(Method method) {
		return method.getName().startsWith("set") && method.getParameterTypes().length == 1;
	}

	public static void setSetterValue(Object object, Method method, Object value) {

		Class<?> targetType = method.getParameterTypes()[0];

		Class<?> keyClass = null, valueClass = null;

		Class<?>[] classes = getComponentClasses(method, 0);

		if (classes != null && classes.length > 0) {

			keyClass = classes[0];

			if (classes.length > 1) {

				valueClass = classes[1];

			}

		}

		try {

			if (value instanceof String) {

				Format format = method.getDeclaredAnnotation(Format.class);

				if (format != null && !Strings.isEmpty(format.pattern())) {

					value = Dates.date((String) value, format.pattern());

				}

			}

			method.setAccessible(true);

			method.invoke(object, Compat.get(value, targetType, keyClass, valueClass));

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static String getPropertyName(Method method) {

		Alias alias = method.getDeclaredAnnotation(Alias.class);

		if (alias != null && !Strings.isEmpty(alias.value())) {
			return alias.value();
		}

		String name = method.getName().replaceAll("^(get|is|set)", "");

		return (char) (name.charAt(0) + 32) + name.substring(1);

	}

	public static boolean hasMethod(Class<?> targetClass, String name) {

		for (Method method : getDeclaredMethods(targetClass)) {

			if (method.getName().equals(name)) {
				return true;
			}

		}

		return false;

	}

	public static boolean isCompatible(Method method, Class<?>... targetTypes) {

		Class<?>[] types = method.getParameterTypes();

		if (types != null) {

			if (targetTypes != null && types.length == targetTypes.length) {

				for (int i = 0, len = types.length; i < len; i++) {

					Class<?> targetType = targetTypes[i];

					if (targetType == null) {

					}

					if (!Reflect.isCompatible(types[i], targetTypes[i])) {
						return false;
					}

				}

				return true;

			}

		}

		return false;
	}

	public static boolean isCompatible(Method method, Object... arguments) {
		return isCompatible(method, Classes.getClasses(arguments));
	}

	public static List<Method> getDeclaredMethods(Class<?> targetClass) {

		List<Method> methods = new ArrayList<Method>();

		Class<?> superClass = targetClass.getSuperclass();

		if (superClass != null && superClass != Object.class) {

			methods.addAll(getDeclaredMethods(superClass));

		}

		methods.addAll(Arrays.asList(targetClass.getDeclaredMethods()));

		return methods;

	}

	public static Method getSuperMethod(Class<?> targetClass, boolean compatible, String name, Class<?>... types) {

		try {

			if (targetClass.equals(Object.class)) {
				return null;
			}

			// 查找匹配方法
			Method method = targetClass.getDeclaredMethod(name, types);

			if (method == null) {

				// 查找兼容方法
				if (compatible) {

					for (Method declaredMethod : targetClass.getDeclaredMethods()) {

						if (isCompatible(declaredMethod, types)) {

							return declaredMethod;

						}

					}

				}

				// 查找父类方法
				Class<?> superClass = targetClass.getSuperclass();

				if (superClass != null) {

					method = getSuperMethod(superClass, compatible, name, types);

				}

				// 查找接口方法
				if (!targetClass.isInterface()) {

					for (Class<?> superInterface : targetClass.getInterfaces()) {

						method = getSuperMethod(superInterface, compatible, name, types);

					}

				}

			}

			return method;

		} catch (Exception e) {
		}

		return null;

	}

	public static Method getSuperMethod(Method method) {

		Class<?> targetClass = method.getDeclaringClass();

		String name = method.getName();

		Class<?>[] types = method.getParameterTypes();

		Method superMethod = getSuperMethod(targetClass.getSuperclass(), false, name, types);

		if (superMethod == null) {

			if (!targetClass.isInterface()) {

				for (Class<?> superInterface : targetClass.getInterfaces()) {

					superMethod = getSuperMethod(superInterface, false, name, types);

				}

			}

		}

		return superMethod;

	}

}
