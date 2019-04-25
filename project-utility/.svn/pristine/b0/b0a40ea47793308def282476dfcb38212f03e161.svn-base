package cn.quickly.project.utility.map;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.quickly.project.utility.function.UnarySupplier;
import cn.quickly.project.utility.lang.Exceptions;

public class ComplexMap<K, V> extends AbstractMap<K, V> {

	private Map<K, V> cache;

	private Set<K> keySet;

	private UnarySupplier<K, V> supplier;

	public ComplexMap(UnarySupplier<K, V> supplier) {
		this(null, supplier);
	}

	public ComplexMap(Set<K> keySet, UnarySupplier<K, V> supplier) {
		this(keySet, supplier, new HashMap<>());
	}

	public ComplexMap(Set<K> keySet, UnarySupplier<K, V> supplier, Map<K, V> cache) {
		this.keySet = keySet;
		this.supplier = supplier;
		this.cache = cache;
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key) {

		if (keySet != null) {
			return keySet.contains(key);

		}

		return cache.containsKey(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(Object name) {

		try {

			K key = (K) name;

			V value = cache.get(key);

			if (value == null) {

				value = supplier.get(key);

				if (keySet != null) {

					keySet.add(key);

				}

				cache.put(key, value);

			}

			return value;

		} catch (Throwable e) {

			throw Exceptions.argument(e);

		}

	}

	@Override
	public V put(K key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<K> keySet() {

		if (keySet != null) {
			return keySet;
		}

		return cache.keySet();

	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return cache.entrySet();
	}

	@Override
	public int size() {

		if (keySet != null) {

			return keySet.size();

		}

		return cache.size();
	}

}
