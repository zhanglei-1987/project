package cn.quickly.project.utility.map;

import java.util.HashMap;

public class DefaultHashMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 1L;

	private V defaultValue;

	public DefaultHashMap(V defaultValue) {
		this.defaultValue = defaultValue;
	}

	public DefaultHashMap(V defaultValue, int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
		this.defaultValue = defaultValue;
	}

	public DefaultHashMap(V defaultValue, int initialCapacity) {
		super(initialCapacity);
		this.defaultValue = defaultValue;
	}

	public V get(Object name) {

		V value = super.get(name);

		if (value == null) {
			value = defaultValue;
		}

		return value;

	}

}
