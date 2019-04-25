package cn.quickly.project.utility.lang;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import cn.quickly.project.utility.map.ImmutableProperties;

public class Immutables {

	public static <E> Collection<E> collection(Collection<E> collection) {
		return Collections.unmodifiableCollection(collection);
	}

	public static <E> List<E> list(List<E> list) {
		return Collections.unmodifiableList(list);
	}

	public static <E> Set<E> set(Set<E> set) {
		return Collections.unmodifiableSet(set);
	}

	public static <K, V> Map<K, V> map(Map<K, V> map) {
		return Collections.unmodifiableMap(map);
	}

	public static Properties properties(Properties properties) {

		return new ImmutableProperties(properties);
	}

}
