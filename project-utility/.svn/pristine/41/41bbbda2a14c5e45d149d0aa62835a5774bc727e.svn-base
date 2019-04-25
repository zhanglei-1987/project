package cn.quickly.project.utility.concurrent;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.function.Supplier;

import cn.quickly.project.utility.lang.Loops;

public final class ThreadPools {

	private ThreadPools() {

		throw new UnsupportedOperationException();

	}

	public static ThreadPoolExecutor named(String name, Supplier<ThreadPoolExecutor> supplier) {

		ThreadPoolExecutor executor = supplier.get();
		executor.setThreadFactory(new NamedThreadFactory(name));

		return executor;

	}

	public static ThreadPoolExecutor dotp(int capacity) {
		return new DiscardOldestExecutor(capacity);
	}

	public static ThreadPoolExecutor dotp(int corePoolSize, int maximumPoolSize, int maximumTaskSize) {

		return new DiscardOldestExecutor(corePoolSize, maximumPoolSize, maximumTaskSize);

	}

	public static ThreadPoolExecutor dtp(int capacity) {
		return new DiscardExecutor(capacity);
	}

	public static ThreadPoolExecutor dtp(int corePoolSize, int maximumPoolSize, int maximumTaskSize) {

		return new DiscardExecutor(corePoolSize, maximumPoolSize, maximumTaskSize);

	}

	public static ThreadPoolExecutor btp(int capacity) {
		return new BlockingExecutor(capacity);
	}

	public static ThreadPoolExecutor btp(int capacity, BlockingQueue<Runnable> queue) {
		return new BlockingExecutor(capacity, queue);
	}

	public static ThreadPoolExecutor btp(int corePoolSize, int maximumPoolSize, int maximumTaskSize) {

		return new BlockingExecutor(corePoolSize, maximumPoolSize, maximumTaskSize);

	}

	public static ScheduledThreadPoolExecutor dostp(int capacity) {

		return new ScheduledThreadPoolExecutor(capacity, new DiscardOldestPolicy());

	}

	public static ScheduledThreadPoolExecutor dstp(int capacity) {

		return new ScheduledThreadPoolExecutor(capacity, new DiscardPolicy());

	}

	public static ScheduledThreadPoolExecutor bstp(int capacity) {

		return new ScheduledThreadPoolExecutor(capacity, new CallerRunsPolicy());

	}

	public static ForkJoinPool fjp(int parallelism) {

		return new ForkJoinPool(parallelism);

	}

	public static void execute(Runnable runnable) {

		ForkJoinPool.commonPool().execute(runnable);

	}

	public static <V> Future<V> submit(Callable<V> callable) {

		return ForkJoinPool.commonPool().submit(callable);

	}

	public static <T> Future<T> submit(Runnable callable, T result) {

		return ForkJoinPool.commonPool().submit(callable, result);

	}

	public static Future<?> submit(Runnable task) {

		return ForkJoinPool.commonPool().submit(task);

	}

	public static Future<?> submit(Runnable... tasks) {

		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

		return Futures.complex(Loops.repeat(0, tasks.length - 1, (i) -> {

			return forkJoinPool.submit(tasks[i]);

		}));

	}

	@SafeVarargs
	public static <V> Future<List<V>> submit(Callable<V>... callables) {

		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

		return Futures.complex(Loops.repeat(0, callables.length - 1, (i) -> {

			return forkJoinPool.submit(callables[i]);

		}));

	}

	public static boolean isQuiescent() {

		return ForkJoinPool.commonPool().isQuiescent();

	}

	public static boolean isQuiescent(ExecutorService executorService) {

		if (executorService instanceof ThreadPoolExecutor) {

			ThreadPoolExecutor executor = (ThreadPoolExecutor) executorService;

			return executor.getActiveCount() == 0 && executor.getCompletedTaskCount() == executor.getTaskCount();

		} else if (executorService instanceof ForkJoinPool) {

			return ((ForkJoinPool) executorService).isQuiescent();

		}

		return executorService.isTerminated();

	}

}
