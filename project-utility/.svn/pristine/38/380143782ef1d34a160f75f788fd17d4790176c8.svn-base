package cn.quickly.project.utility.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public abstract class DelegateMap<K, V> implements Map<K, V> {

	protected abstract Map<K, V> getDelegate();

	public int size() {
		return getDelegate().size();
	}

	public boolean isEmpty() {
		return getDelegate().isEmpty();
	}

	public boolean containsKey(Object key) {
		return getDelegate().containsKey(key);
	}

	public boolean containsValue(Object value) {
		return getDelegate().containsValue(value);
	}

	public V get(Object key) {
		return getDelegate().get(key);
	}

	public V put(K key, V value) {
		return getDelegate().put(key, value);
	}

	public V remove(Object key) {
		return getDelegate().remove(key);
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		getDelegate().putAll(m);
	}

	public void clear() {
		getDelegate().clear();
	}

	public Set<K> keySet() {
		return getDelegate().keySet();
	}

	public Collection<V> values() {
		return getDelegate().values();
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return getDelegate().entrySet();
	}

}
