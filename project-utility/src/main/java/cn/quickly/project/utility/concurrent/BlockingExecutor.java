package cn.quickly.project.utility.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BlockingExecutor extends ThreadPoolExecutor {

	public BlockingExecutor(int capacity) {

		this(capacity, capacity * 2, capacity * 1000);

	}

	public BlockingExecutor(int capacity, BlockingQueue<Runnable> queue) {

		this(capacity, capacity * 2, queue);

	}

	public BlockingExecutor(int corePoolSize, int maximumPoolSize, int maximumTaskSize) {

		this(corePoolSize, maximumPoolSize, new LinkedBlockingQueue<Runnable>(maximumTaskSize));

	}

	public BlockingExecutor(int corePoolSize, int maximumPoolSize, BlockingQueue<Runnable> queue) {

		super(corePoolSize, maximumPoolSize, 60, TimeUnit.SECONDS, queue, new CallerRunsPolicy());

	}

}
