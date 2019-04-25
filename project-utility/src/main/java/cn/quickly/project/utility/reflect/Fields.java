package cn.quickly.project.utility.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cn.quickly.project.utility.annotation.Alias;
import cn.quickly.project.utility.annotation.Format;
import cn.quickly.project.utility.collection.Collections;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.text.Formats;
import cn.quickly.project.utility.time.Dates;

public final class Fields {

	private Fields() {
		throw new UnsupportedOperationException();
	}

	public static Field getField(Class<?> targetClass, String name, boolean accessible) {

		try {

			Field field = targetClass.getDeclaredField(name);
			field.setAccessible(accessible);

			return field;

		} catch (Exception e) {
		}

		return null;
	}

	public static List<Field> getDeclaredFields(Class<?> targetClass) {

		List<Field> fields = new LinkedList<Field>();

		Class<?> superClass = targetClass.getSuperclass();
		if (superClass != null && superClass != Object.class) {
			fields.addAll(getDeclaredFields(superClass));
		}
		fields.addAll(Arrays.asList(targetClass.getDeclaredFields()));

		return fields;

	}

	public static Type[] getComponentTypes(Field field) {

		Type type = field.getGenericType();

		if (type instanceof ParameterizedType) {

			return ((ParameterizedType) type).getActualTypeArguments();

		} else if (type instanceof Class<?>) {

			return Classes.getGenericTypes((Class<?>) type);

		}

		return null;

	}

	public static Class<?>[] getComponentClasses(Field field) {

		Type[] types = getComponentTypes(field);

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

	public static void setValue(Object object, Field field, Object value) {

		Class<?> targetType = field.getType();

		Class<?> keyClass = null, valueClass = null;

		Class<?>[] classes = getComponentClasses(field);

		if (classes != null && classes.length > 0) {

			keyClass = classes[0];

			if (classes.length > 1) {

				valueClass = classes[1];

			}

		}

		try {

			if (value instanceof String) {

				Format format = field.getDeclaredAnnotation(Format.class);

				if (format != null && !Strings.isEmpty(format.pattern())) {

					value = Dates.date((String) value, format.pattern());

				}

			}

			field.setAccessible(true);

			field.set(object, Compat.get(value, targetType, keyClass, valueClass));

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static Object getValue(Object object, Field field) {

		try {

			Object value = field.get(object);

			Format format = field.getDeclaredAnnotation(Format.class);

			if (format != null) {

				value = Formats.format(value, format.pattern());

			}

			return value;

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static String getName(Field field) {

		Alias alias = field.getDeclaredAnnotation(Alias.class);

		if (alias != null && !Strings.isEmpty(alias.value())) {

			return alias.value();

		} else {

			return field.getName();

		}

	}

}
