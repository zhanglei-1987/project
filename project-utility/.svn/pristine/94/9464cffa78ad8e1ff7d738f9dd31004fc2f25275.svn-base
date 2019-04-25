package cn.quickly.project.utility.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.type.TypeConverter;
import cn.quickly.project.utility.type.TypeConverterFactory;

@SuppressWarnings("unchecked")
public final class Compat {

	private static TypeConverterFactory factory = TypeConverterFactory.getFactory();

	private Compat() {
		throw new UnsupportedOperationException();
	}

	public static boolean isCompatible(Object object, Class<?> targetType) {
		if (targetType != null) {

			return cast(object, targetType) == null;

		}
		return false;
	}

	public static <T> List<T> list(Object object, Class<T> targetType) {

		return (List<T>) get(object, List.class, targetType);

	}

	public static <T> Set<T> set(Object object, Class<T> targetType) {

		return (Set<T>) get(object, Set.class, targetType);

	}

	public static <K, V> Map<K, V> map(Object object, Class<K> keyType, Class<V> valueType) {

		return (Map<K, V>) get(object, Map.class, keyType, valueType);

	}

	public static <T> T cast(Object object, Class<T> targetType) {

		return (T) get(object, targetType);

	}

	public static <T> T cast(Object object, Class<T> targetType, Class<?> componentClass) {

		return (T) get(object, targetType, componentClass);

	}

	public static <T> T cast(Object object, GenericType<T> type) {

		ParameterizedType genericType = (ParameterizedType) (Classes.getGenericTypes(type.getClass())[0]);

		Assert.isNotNull(genericType, "Internal error: TypeReference constructed without actual type information");

		Class<?> targetType = (Class<?>) genericType.getRawType();

		Type[] actualTypes = genericType.getActualTypeArguments();

		if (actualTypes != null) {

			if (actualTypes.length == 1) {

				return (T) get(object, targetType, (Class<?>) actualTypes[0]);

			} else if (actualTypes.length == 2) {

				return (T) get(object, targetType, (Class<?>) actualTypes[0], (Class<?>) actualTypes[1]);

			}

		}

		return (T) get(object, targetType);

	}

	public static Object get(Object object, Class<?> targetType) {

		Assert.isNotNull(targetType, "the target type is required.");

		if (object != null && Classes.isSuperClass(targetType, object.getClass())) {
			return object;
		}

		TypeConverter<?> converter = factory.geConverter(targetType);

		if (converter == null) {

			return null;

		}

		return converter.convert(targetType, object);
	}

	public static Object get(Object object, Class<?> targetType, Class<?> componentClass) {

		if (!Classes.isSuperClass(Collection.class, targetType)) {

			return cast(object, targetType);

		}

		if (componentClass == null || componentClass.equals(Object.class)) {

			return cast(object, targetType);

		} else {

			Collection<Object> datas = (Collection<Object>) cast(null, targetType);

			for (Object data : cast(object, List.class)) {

				datas.add(cast(data, componentClass));

			}

			return datas;

		}

	}

	public static Object get(Object object, Class<?> targetType, Class<?> keyClass, Class<?> valueClass) {

		if (Classes.isSuperClass(Collection.class, targetType)) {

			return get(object, targetType, keyClass);

		} else if (!Classes.isSuperClass(Map.class, targetType)) {

			return cast(object, targetType);

		}

		if ((keyClass == null || keyClass.equals(Object.class)) && (valueClass == null || valueClass.equals(Object.class))) {

			return cast(object, targetType);

		} else {

			Map<Object, Object> datas = (Map<Object, Object>) cast(null, targetType);

			Iterables.forEach(cast(object, Map.class), (i, key, value) -> {

				if (keyClass != null) {

					key = Compat.get(key, keyClass);

				}

				if (valueClass != null) {

					value = Compat.get(value, valueClass);

				}

				datas.put(key, value);

			});

			return datas;

		}

	}

}
