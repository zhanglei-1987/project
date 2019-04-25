package cn.quickly.project.utility.concurrent;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

import cn.quickly.project.utility.function.UnarySupplier;

public class Locks {

	public static <V> void dcl(V content, Predicate<V> predicate, Consumer<V> consumer) throws Exception {

		if (!predicate.test(content)) {

			synchronized (content) {

				if (!predicate.test(content)) {

					consumer.accept(content);

				}

			}

		}

	}

	public static <K, V> V dcl(Map<K, V> content, K key, UnarySupplier<K, V> supplier) throws Exception {

		V value = content.get(key);

		if (value == null) {

			synchronized (content) {

				value = content.get(key);

				if (value == null) {

					value = supplier.get(key);

					if (value != null) {

						content.put(key, value);

					}

				}

			}

		}

		return value;

	}

	public static <V> V get(Lock lock, Supplier<V> supplier) {

		lock.lock();

		try {

			return supplier.get();

		} finally {

			lock.unlock();
		}

	}

	public static void execute(Lock lock, Runnable runnable) {

		lock.lock();

		try {

			runnable.run();

		} finally {

			lock.unlock();
		}

	}

}
