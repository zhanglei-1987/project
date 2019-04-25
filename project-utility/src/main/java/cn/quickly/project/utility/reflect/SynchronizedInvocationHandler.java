package cn.quickly.project.utility.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedInvocationHandler implements InvocationHandler {

	private ReentrantLock lock = new ReentrantLock();

	private Object target;

	public SynchronizedInvocationHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		lock.lock();

		try {

			return method.invoke(target, args);

		} finally {

			lock.unlock();
		}

	}

}
