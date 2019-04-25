package cn.quickly.project.utility.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import cn.quickly.project.utility.function.BinaryPredicate;
import cn.quickly.project.utility.function.BinarySupplier;
import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.lang.Objects;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.reflect.Annotations;
import cn.quickly.project.utility.reflect.BeanInjector;
import cn.quickly.project.utility.reflect.Compat;
import cn.quickly.project.utility.reflect.FieldInjector;
import cn.quickly.project.utility.reflect.Reflect;
import cn.quickly.project.utility.regex.Patterns;
import cn.quickly.project.utility.thirdparty.Cglib;

public final class Maps {

	private Maps() {
		throw new UnsupportedOperationException();
	}

	public static Map<String, Object> getMap(Object bean, String... excludes) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (!Objects.isBasic(bean)) {

			if (bean instanceof Properties) {

				map.putAll(from((Properties) bean));

			} else if (bean instanceof Map<?, ?>) {

				Map<?, ?> vmap = (Map<?, ?>) bean;

				for (Map.Entry<?, ?> entry : vmap.entrySet()) {

					Object key = entry.getKey();

					map.put(key == null ? null : key.toString(), entry.getValue());

				}

			} else if (Objects.isAnnotation(bean)) {

				map.putAll(Annotations.getMap(bean));

			} else {

				if (Reflect.isCglibSupport()) {

					try {

						map.putAll(Cglib.getMap(bean));

					} catch (Throwable e) {

						map.putAll(getMapUseMethod(bean));

					}

				} else {

					map.putAll(getMapUseMethod(bean));

				}

			}

		}

		for (String exclude : excludes) {

			if (!Strings.isEmpty(exclude)) {

				map.remove(exclude);

			}

		}

		return map;
	}

	public static Map<String, Object> getMapUseMethod(Object bean, String... excludes) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (!Objects.isBasic(bean)) {

			Class<?> beanClass = bean.getClass();

			BeanInjector injector = BeanInjector.getInjector(beanClass);

			Map<String, Object> data = injector.getProperties(bean);

			if (data != null) {

				map.putAll(data);

			}

		}

		for (String exclude : excludes) {

			if (!Strings.isEmpty(exclude)) {

				map.remove(exclude);

			}

		}

		return map;
	}

	public static Map<String, Object> getMapUseField(Object bean, String... excludes) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (!Objects.isBasic(bean)) {

			Class<?> beanClass = bean.getClass();

			FieldInjector injector = FieldInjector.getInjector(beanClass);

			Map<String, Object> data = injector.getAttributes(bean);

			if (data != null) {

				map.putAll(data);

			}

		}

		for (String exclude : excludes) {

			if (!Strings.isEmpty(exclude)) {

				map.remove(exclude);

			}

		}

		return map;
	}

	public static Map<String, Object> from(Properties properties, String... excludes) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		for (Map.Entry<?, ?> entry : properties.entrySet()) {

			Object key = entry.getKey();

			map.put(key == null ? null : key.toString(), entry.getValue());

		}

		for (String exclude : excludes) {
			if (!Strings.isEmpty(exclude)) {
				map.remove(exclude);
			}
		}

		return map;
	}

	public static <K, V> Map<K, V> asMap(K key, V value) {
		Map<K, V> map = new LinkedHashMap<K, V>();
		map.put(key, value);
		return map;
	}

	public static <K, V> Map<K, V> multi(K[] keys, V[] values) {

		Assert.isTrue(keys.length == values.length, "the keys length must equals values length");

		Map<K, V> map = new LinkedHashMap<K, V>();
		for (int i = 0, length = keys.length; i < length; i++) {
			map.put(keys[i], values[i]);
		}
		return map;
	}

	public static <K, V> Map<K, V> flat(Iterable<? extends V> objects, Class<K> keyType, String keyName) {

		Map<K, V> values = new LinkedHashMap<K, V>();

		for (V object : objects) {

			K key = Objects.extract(object, keyType, keyName);

			if (key != null) {

				values.put(key, object);

			}

		}

		return values;
	}

	public static <K, V> Map<K, V> flat(V[] objects, Class<K> keyType, String keyName) {

		Map<K, V> values = new LinkedHashMap<K, V>();

		for (V object : objects) {

			K key = Objects.extract(object, keyType, keyName);

			if (key != null) {

				values.put(key, object);

			}

		}

		return values;
	}

	public static <K, V> Map<K, V> flat(Iterable<?> objects, Class<K> keyType, String keyName, Class<V> valueType, String valueName) {

		Map<K, V> values = new LinkedHashMap<K, V>();

		for (Object object : objects) {

			K key = Objects.extract(object, keyType, keyName);

			V value = Objects.extract(object, valueType, valueName);

			if (key != null) {

				values.put(key, value);

			}

		}

		return values;
	}

	public static <K, V> Map<K, V> flat(Object[] objects, Class<K> keyType, String keyName, Class<V> valueType, String valueName) {

		Map<K, V> values = new LinkedHashMap<K, V>();

		for (Object object : objects) {

			K key = Objects.extract(object, keyType, keyName);

			V value = Objects.extract(object, valueType, valueName);

			if (key != null) {

				values.put(key, value);

			}

		}

		return values;
	}

	public static <V> Map<String, V> match(Map<String, V> source, String pattern, boolean reject) {

		Map<String, V> target = new LinkedHashMap<String, V>();

		String regex = pattern.replace(".", "\\.").replace("*", "(.*)");

		for (String name : source.keySet()) {

			String key = Patterns.text(name, regex, 1);

			if (!Strings.isEmpty(key)) {

				if (reject) {
					target.put(key, source.get(name));
				} else {
					target.put(name, source.get(name));
				}

			}

		}

		return target;
	}

	public static <K, V extends Comparable<V>> Map<K, V> sort(Map<K, V> datas, boolean asc) {

		List<Map.Entry<K, V>> entries = new ArrayList<>(datas.entrySet());

		Collections.sort(entries, (s1, s2) -> {
			if (asc) {
				return s1.getValue().compareTo(s2.getValue());
			} else {
				return s2.getValue().compareTo(s1.getValue());
			}
		});

		Map<K, V> sorted = new LinkedHashMap<>();

		Iterables.forEach(entries, (i, entry) -> {

			sorted.put(entry.getKey(), entry.getValue());

		});

		return sorted;

	}

	public static <K, V extends Comparable<V>> Map<K, V> top(Map<K, V> map, int count) {

		Map<K, V> results = new HashMap<>();

		Iterables.forEach(map, (i, key, value) -> {

			if (results.size() < count) {

				results.put(key, value);

			} else {

				for (Object k : results.keySet().toArray()) {

					if (results.get(k).compareTo(value) < 0) {

						results.remove(k);

						results.put(key, value);

						break;

					}

				}

			}

		});

		return results;

	}

	public static <K, V> Map<K, V> cast(Map<?, ?> data, Class<K> keyType, Class<V> valueType) {

		return cast(data, (key, value) -> Compat.cast(key, keyType), (key, value) -> Compat.cast(value, valueType));

	}

	public static <K, V> Map<K, V> cast(Map<?, ?> data, BinarySupplier<Object, Object, K> keySupplier, BinarySupplier<Object, Object, V> valueSupplier) {

		Map<K, V> map = new HashMap<>();

		Iterables.forEach(data, (i, key, value) -> {

			map.put(keySupplier.get(key, value), valueSupplier.get(key, value));

		});

		return map;

	}

	public static <K, V> Map<K, V> filter(Map<K, V> data, BinaryPredicate<K, V> predicate) {

		Map<K, V> map = new HashMap<>();

		Iterables.forEach(data, (i, key, value) -> {

			if (predicate.test(i, key, value)) {

				map.put(key, value);

			}

		});

		return map;

	}

}
