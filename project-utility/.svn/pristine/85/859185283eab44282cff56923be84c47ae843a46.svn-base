package cn.quickly.project.utility.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DiscardExecutor extends ThreadPoolExecutor {

	public DiscardExecutor(int capacity) {
		this(capacity, capacity * 2, capacity * 200);
	}

	public DiscardExecutor(int corePoolSize, int maximumPoolSize, int maximumTaskSize) {
		super(corePoolSize, maximumPoolSize, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(maximumTaskSize), new DiscardPolicy());
	}

}
