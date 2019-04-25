package cn.quickly.project.utility.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class NoneLock implements Lock {

	public static final NoneLock INSTANCE = new NoneLock();

	private NoneLock() {
	
	}

	@Override
	public void lock() {

	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public boolean tryLock() {
		return true;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return true;
	}

	@Override
	public void unlock() {

	}

	@Override
	public Condition newCondition() {
		return NoneCondition.INSTANCE;
	}

	static final class NoneCondition implements Condition {

		static final NoneCondition INSTANCE = new NoneCondition();

		@Override
		public void await() throws InterruptedException {

		}

		@Override
		public void awaitUninterruptibly() {

		}

		@Override
		public long awaitNanos(long nanosTimeout) throws InterruptedException {
			return 0;
		}

		@Override
		public boolean await(long time, TimeUnit unit) throws InterruptedException {
			return true;
		}

		@Override
		public boolean awaitUntil(Date deadline) throws InterruptedException {
			return true;
		}

		@Override
		public void signal() {

		}

		@Override
		public void signalAll() {

		}

	}

}
