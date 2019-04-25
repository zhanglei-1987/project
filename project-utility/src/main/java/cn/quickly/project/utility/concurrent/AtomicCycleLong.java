package cn.quickly.project.utility.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCycleLong extends Number implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private AtomicLong value;

	private long min;

	private long max;

	public AtomicCycleLong(long min, long max) {
		this.min = min;
		this.max = max;
		this.value = new AtomicLong(min);
	}

	public long min() {
		return min;
	}

	public long max() {
		return max;
	}

	public long get() {
		return value.get();
	}

	public long getAndIncrement() {
		return getAndAdd(1);
	}

	public long getAndDecrement() {
		return getAndAdd(-1);
	}

	public synchronized long getAndAdd(long delta) {

		long cv = value.get();

		if (cv + delta > max) {
			value.set(min);
		} else if (cv + delta < min) {
			value.set(max);
		} else {
			value.getAndSet(delta);
		}

		return cv;
	}

	public long incrementAndGet() {
		return addAndGet(1);
	}

	public long decrementAndGet() {
		return addAndGet(-1);
	}

	public synchronized long addAndGet(long delta) {

		long cv = value.addAndGet(delta);

		if (cv > max) {
			value.set(min);
		} else if (cv < min) {
			value.set(max);
		}

		return value.get();

	}

	@Override
	public int intValue() {
		return value.intValue();
	}

	@Override
	public long longValue() {
		return value.longValue();
	}

	@Override
	public float floatValue() {
		return value.floatValue();
	}

	@Override
	public double doubleValue() {
		return value.doubleValue();
	}

}
