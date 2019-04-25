package cn.quickly.project.utility.concurrent;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Iterables;

public class Futures {

	private Futures() {

		throw new UnsupportedOperationException();

	}

	public static <E> List<E> get(Future<E>[] futures, long time, TimeUnit unit) throws Exception {

		return get(Arrays.asList(futures), time, unit);

	}

	public static <E> List<E> get(List<Future<E>> futures, long time, TimeUnit unit) throws Exception {

		return complex(futures).get(time, unit);

	}

	@SafeVarargs
	public static <E> Future<List<E>> complex(Future<E>... futures) {

		return complex(Arrays.asList(futures));

	}

	public static <E> Future<List<E>> complex(List<Future<E>> futures) {

		return new Future<List<E>>() {

			@Override
			public boolean cancel(boolean arg0) {

				AtomicBoolean result = new AtomicBoolean(true);

				Iterables.flat(futures, (i, future) -> result.compareAndSet(true, future.cancel(arg0)));

				return result.get();

			}

			@Override
			public boolean isCancelled() {

				AtomicBoolean result = new AtomicBoolean(true);

				Iterables.flat(futures, (i, future) -> result.compareAndSet(true, future.isCancelled()));

				return result.get();

			}

			@Override
			public boolean isDone() {

				AtomicBoolean result = new AtomicBoolean(true);

				Iterables.flat(futures, (i, future) -> result.compareAndSet(true, future.isDone()));

				return result.get();

			}

			@Override
			public List<E> get() throws InterruptedException, ExecutionException {

				try {
					return get(Long.MAX_VALUE, TimeUnit.DAYS);
				} catch (TimeoutException e) {
					throw Exceptions.runtime(e);
				}

			}

			@Override
			public List<E> get(long time, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {

				return Iterables.flat(futures, (i, future) -> {

					try {
						return future.get(time, unit);
					} catch (Exception e) {
						throw Exceptions.runtime(e);
					}

				});

			}

		};

	}

}
