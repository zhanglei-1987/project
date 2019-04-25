package cn.quickly.project.utility.collection;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cn.quickly.project.utility.lang.Objects;

@SuppressWarnings("unchecked")
public final class Collections {

	private Collections() {
		throw new UnsupportedOperationException();
	}

	public static <T> T[] toArray(Class<T> componentType, Collection<?>... collections) {

		List<Object> list = new LinkedList<Object>();

		for (Collection<?> collection : collections) {
			list.addAll(collection);
		}

		return list.toArray((T[]) Array.newInstance(componentType, list.size()));

	}

	public static boolean contains(Collection<?> source, Collection<?> target) {
		for (Object item : target) {
			if (source.contains(item)) {
				return true;
			}
		}
		return false;
	}

	public static <T> Set<T> extract(Collection<?> objects, Class<T> keyType, String keyName) {
		return Arrays.extract(objects.toArray(), keyType, keyName);
	}

	public static <T extends Collection<V>, V> T clone(T c) {
		T n = (T) Objects.getInstance(c.getClass());
		n.addAll(c);
		return n;
	}
}
