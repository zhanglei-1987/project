package cn.quickly.project.utility.map;

import java.util.concurrent.ConcurrentHashMap;

import cn.quickly.project.utility.function.UnarySupplier;

@SuppressWarnings("unchecked")
public class SupplierConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

	private static final long serialVersionUID = 1L;

	private UnarySupplier<K, V> supplier;

	private boolean store;

	public SupplierConcurrentHashMap(UnarySupplier<K, V> supplier, boolean store) {
		this.supplier = supplier;
		this.store = store;
	}

	public SupplierConcurrentHashMap(UnarySupplier<K, V> supplier, boolean store, int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
		this.supplier = supplier;
		this.store = store;
	}

	public SupplierConcurrentHashMap(UnarySupplier<K, V> supplier, boolean store, int initialCapacity) {
		super(initialCapacity);
		this.supplier = supplier;
		this.store = store;
	}

	public V get(Object name) {

		V value = super.get(name);

		if (value == null) {
			try {

				value = supplier.get((K) name);

				if (store && value != null) {
					put((K) name, value);
				}

			} catch (Exception e) {
			}
		}

		return value;

	}

	@Override
	public boolean containsKey(Object key) {
		try {
			return super.containsKey(key) || supplier.get((K) key) != null;
		} catch (Exception e) {
			return false;
		}
	}

}
