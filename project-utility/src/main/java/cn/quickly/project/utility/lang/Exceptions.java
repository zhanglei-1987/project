package cn.quickly.project.utility.lang;

import java.util.concurrent.Callable;

import cn.quickly.project.utility.function.UnarySupplier;

public class Exceptions {

	public static RuntimeException runtime(Throwable e) {

		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		}

		return new RuntimeException(e);
	}

	public static <V> V runtime(Callable<V> callable) {

		try {

			return callable.call();

		} catch (Throwable e) {

			throw runtime(e);

		}

	}

	public static <K, V> V runtime(K key, UnarySupplier<K, V> supplier) {

		try {

			return supplier.get(key);

		} catch (Throwable e) {

			throw runtime(e);

		}

	}

	public static IllegalArgumentException argument(Throwable e) {

		if (e instanceof IllegalArgumentException) {
			return (IllegalArgumentException) e;
		}

		return new IllegalArgumentException(e);
	}

	public static IllegalArgumentException argument(String message) {
		return new IllegalArgumentException(message);
	}

	public static <V> V argument(Callable<V> callable) {

		try {

			return callable.call();

		} catch (Throwable e) {

			throw argument(e);

		}

	}

	public static <K, V> V argument(K key, UnarySupplier<K, V> supplier) {

		try {

			return supplier.get(key);

		} catch (Throwable e) {

			throw argument(e);

		}

	}

	public static UnsupportedOperationException unsupported() {
		throw new UnsupportedOperationException();
	}

	public static UnsupportedOperationException unsupported(String message) {
		throw new UnsupportedOperationException(message);
	}

	public static UnsupportedOperationException unsupported(Throwable e) {

		if (e instanceof UnsupportedOperationException) {
			return (UnsupportedOperationException) e;
		}

		return new UnsupportedOperationException(e);
	}

	public static Exception exception(Throwable e) {

		if (e instanceof Exception) {
			return (Exception) e;
		}

		return runtime(e);

	}

}
