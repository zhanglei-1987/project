package cn.quickly.project.utility.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.quickly.project.utility.reflect.Compat;

public class ConcurrentBothMap<V> extends ConcurrentHashMap<V, V> {

	private static final long serialVersionUID = 1L;

	public ConcurrentBothMap() {
	}

	public ConcurrentBothMap(Map<V, V> data) {
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
