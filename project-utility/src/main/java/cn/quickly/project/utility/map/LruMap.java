package cn.quickly.project.utility.map;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class LruMap<K, V> extends LinkedHashMap<K, V> implements ConcurrentMap<K, V> {

	private static final long serialVersionUID = 1L;

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	private ReadLock readLock = lock.readLock();

	private WriteLock writeLock = lock.writeLock();

	private int capacity;

	public LruMap(int capacity) {
		super(capacity, 1, true);
		this.capacity = capacity;
	}

	@Override
	public V get(Object key) {
		readLock.lock();
		try {
			return super.get(key);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public V put(K key, V value) {
		writeLock.lock();
		try {
			return super.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public V remove(Object key) {
		writeLock.lock();
		try {
			return super.remove(key);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public void clear() {
		writeLock.lock();
		try {
			super.clear();
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public int size() {
		writeLock.lock();
		try {
			return super.size();
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		if (size() > capacity) {
			return true;
		} else {
			return false;
		}
	}

}
