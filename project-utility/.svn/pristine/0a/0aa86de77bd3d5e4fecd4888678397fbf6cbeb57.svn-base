package cn.quickly.project.utility.thirdparty;

import java.util.HashMap;
import java.util.Map;

import cn.quickly.project.utility.lang.Objects;
import cn.quickly.project.utility.reflect.Compat;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;

public final class Cglib {

	private Cglib() {
		throw new UnsupportedOperationException();
	}

	public static Map<String, Object> getMap(Object target) {

		Map<String, Object> map = new HashMap<String, Object>();

		BeanMap bmap = BeanMap.create(target);

		for (Object key : bmap.keySet()) {

			map.put(key == null ? null : key.toString(), bmap.get(key));

		}

		return map;
	}

	public static Object clone(Object bean) {
		return copy(bean, bean.getClass());
	}

	public static <T> T copy(Object value, Class<T> type) {

		T object = Objects.getInstance(type);

		copy(object, object);

		return object;
	}

	public static <T> T copy(Object value, Class<T> type, net.sf.cglib.core.Converter converter) {

		T target = Objects.getInstance(type);

		copy(value, target, converter);

		return target;

	}

	public static void copy(Object value, Object target) {

		BeanCopier copier = BeanCopier.create(target.getClass(), value.getClass(), false);

		copier.copy(value, target, null);

	}

	public static void copy(Object value, Object target, net.sf.cglib.core.Converter converter) {

		BeanCopier copier = BeanCopier.create(target.getClass(), value.getClass(), true);

		copier.copy(value, target, converter);

	}

	public static <T> T fillObject(T bean, Map<String, Object> map) {

		BeanMap bmap = BeanMap.create(bean);

		for (Object key : bmap.keySet()) {

			String name = String.valueOf(key);

			Class<?> type = bmap.getPropertyType(name);

			Object value = map.get(name);

			try {

				bmap.put(name, Compat.get(value, type));

			} catch (Throwable e) {
			}

		}

		return bean;

	}

	public static <T> T fill(Class<T> type, Map<String, Object> map) {

		T bean = Objects.getInstance(type);

		fillObject(bean, map);

		return bean;

	}

}
