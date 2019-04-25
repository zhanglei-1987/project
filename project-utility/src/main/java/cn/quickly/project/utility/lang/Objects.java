package cn.quickly.project.utility.lang;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

import cn.quickly.project.utility.io.Serialization;
import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.reflect.Classes;
import cn.quickly.project.utility.reflect.Compat;
import cn.quickly.project.utility.reflect.Constructors;
import cn.quickly.project.utility.reflect.FieldInjector;
import cn.quickly.project.utility.reflect.Reflect;

@SuppressWarnings("unchecked")
public final class Objects {

	private Objects() {
		throw new UnsupportedOperationException();
	}

	public static <T> T getInstance(Class<T> type) {
		try {

			return type.newInstance();

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static <T> T getInstance(Class<T> type, Object... args) {

		Constructor<T> constructor = Constructors.getConstructor(type, Classes.getClasses(args));

		Assert.isNotNull(constructor, "matched Constructor not found.");

		try {

			return constructor.newInstance(args);

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static Object getInstance(Object bean) {

		if (bean != null) {

			return getInstance(bean.getClass());

		}

		return null;

	}

	public static boolean isBasic(Object object) {

		if (object == null) {
			return true;
		}

		Class<?> type = object.getClass();

		if (Reflect.isBaseType(type)) {

			return true;

		}

		return false;
	}

	public static boolean isSerializable(Object object) {

		if (object != null && object instanceof Serializable) {

			try {
				Serialization.serialize(object);
				return true;
			} catch (Throwable e) {

			}

		}

		return false;
	}

	public static boolean isAnnotation(Object bean) {
		return bean instanceof Annotation;
	}

	public static Object depthClone(Serializable object) {
		return Serialization.deserialize(Serialization.serialize(object));
	}

	public static boolean isEmpty(Object source) {

		if (source == null) {
			return true;
		} else if (source instanceof Collection<?> && ((Collection<?>) source).isEmpty()) {
			return true;
		} else if (source.getClass().isArray() && Array.getLength(source) == 0) {
			return true;
		} else if (source instanceof Map<?, ?> && ((Map<?, ?>) source).isEmpty()) {
			return true;
		} else if (source instanceof String && Strings.isEmpty(source + "")) {
			return true;
		}

		return false;

	}

	public static <T> T empty(T source, T def) {

		if (isEmpty(source)) {
			return def;
		}

		return source;

	}

	public static <T> T empty(T source, Supplier<T> def) {

		if (isEmpty(source)) {

			return def.get();

		}

		return source;

	}

	/**
	 * 
	 * 相似性比较,比较的是属性，可以不通类型比较
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean like(Object source, Object target) {

		if (source == null || target == null) {
			return false;
		}

		if (isBasic(source) || isBasic(target)) {
			return source.equals(target);
		} else {

			Map<String, Object> sourceMap = Maps.getMap(source);

			Map<String, Object> targetMap = Maps.getMap(target);

			for (Map.Entry<String, Object> entry : sourceMap.entrySet()) {

				String name = entry.getKey();

				Object sourceObject = entry.getValue();

				Object targetObject = targetMap.get(name);

				if (sourceObject == null) {
					continue;
				} else if (targetObject == null) {
					return false;
				} else if (!like(sourceObject, targetObject)) {
					return false;
				}

			}

		}

		return true;

	}

	public static <T> T extract(Object object, Class<T> type, String name) {

		if (object instanceof Map<?, ?>) {

			return (T) Compat.get(((Map<?, ?>) object).get(name), type);

		} else {

			FieldInjector injector = FieldInjector.getInjector(object.getClass());

			return (T) Compat.get(injector.getAttribute(object, name), type);

		}

	}

	public static <T> T cast(Object object, Class<T> type) {
		return type.cast(object);
	}

	/**
	 * 同类型对象比较
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 * @return
	 */
	public static <T> boolean equals(T source, T... targets) {

		if (source == null) {
			return false;
		}

		for (Object target : targets) {

			if (source.equals(target)) {
				return true;
			}

		}

		return false;
	}

}
