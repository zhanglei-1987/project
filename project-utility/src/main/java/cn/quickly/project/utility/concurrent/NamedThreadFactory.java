package cn.quickly.project.utility.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import cn.quickly.project.utility.lang.Strings;

public class NamedThreadFactory implements ThreadFactory {

	private AtomicInteger counter = new AtomicInteger(1);

	private String name;

	public NamedThreadFactory(String name) {
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable r) {

		return new Thread(r, Strings.concat(name, "-thread-", counter.getAndIncrement()));

	}

}
