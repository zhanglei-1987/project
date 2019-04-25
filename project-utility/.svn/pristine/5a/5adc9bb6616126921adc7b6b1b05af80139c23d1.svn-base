package cn.quickly.project.utility.map;

import java.util.HashMap;
import java.util.Map;

import cn.quickly.project.utility.reflect.Compat;

public class BothMap<V> extends HashMap<V, V> {

	private static final long serialVersionUID = 1L;

	public BothMap() {
	}

	public BothMap(Map<V, V> data) {
		putAll(data);
	}

	@Override
	public V put(V key, V value) {
		super.put(value, key);
		return super.put(key, value);
	}

	public <T> T get(V key, Class<T> type) {
		return Compat.cast(super.get(key), type);
	}

}
