package cn.quickly.project.utility.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.quickly.project.utility.reflect.Classes;

/**
 * 
 * 枚举工具类
 * 
 */
@SuppressWarnings("unchecked")
public final class Enums {

	private static volatile Map<Class<?>, Map<String, Enum<?>>> enums = new ConcurrentHashMap<>();

	public Enums() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 将枚举映射成Map，key为name或者toString，value为枚举值。
	 * 
	 * 
	 * 
	 * @param type
	 *            枚举的Class
	 * @param include
	 *            为true时，key值包含toString，否则只有name
	 * @return
	 */
	public static <T> Map<String, T> mapping(Class<T> type, boolean include) {

		Map<String, T> mapping = new HashMap<String, T>();

		for (Object o : type.getEnumConstants()) {

			Enum<?> e = (Enum<?>) o;

			if (include) {

				mapping.put(e.toString(), (T) e);

				if (Classes.isSuperClass(CodeEnum.class, type)) {

					mapping.put(((CodeEnum) e).code(), (T) e);

				}

			}

			mapping.put(e.name(), (T) e);

		}

		return mapping;

	}

	/**
	 * 尝试把字符串转换为指定的枚举，不能转换时抛出IllegalArgumentException
	 * 
	 * 
	 * @param type
	 *            枚举Class
	 * @param name
	 *            字符串
	 * @return
	 */
	public static <T> T valueOf(Class<T> type, String name) {
		return valueOf(type, name, true);
	}

	/**
	 * 尝试把字符串转化为指定的枚举，
	 * 
	 * 
	 * @param type
	 *            枚举Class
	 * @param name
	 *            字符串
	 * @param force
	 *            为true时，如何不能转换抛出IllegalArgumentException，否则返回null
	 * @return
	 */
	public static <T> T valueOf(Class<T> type, String name, boolean force) {

		Map<String, Enum<?>> mapping = enums.get(type);

		if (mapping == null) {

			synchronized (enums) {

				if (mapping == null) {

					mapping = (Map<String, Enum<?>>) mapping(type, true);

					enums.put(type, mapping);
				}

			}

		}

		T e = (T) mapping.get(name);

		if (e == null && force) {
			throw Exceptions.argument(Strings.concat("No enum const ", type, ".", name));
		}

		return e;

	}

	/**
	 * 尝试将枚举转换成指定类型的枚举
	 * 
	 * 
	 * @param enums
	 *            枚举
	 * @param enumClass
	 *            目标枚举Class
	 * @return
	 */
	public static <T> T convert(Enum<?> enums, Class<T> enumClass) {
		if (enums != null) {
			return Enums.valueOf(enumClass, enums.name());
		}
		return null;
	}

	/**
	 * 清除缓存
	 * 
	 */
	public static void clear() {
		enums.clear();
	}
}
