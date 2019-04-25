package cn.quickly.project.utility.time;

import cn.quickly.project.utility.lang.Exceptions;

public final class TimeCounter {

	private static ThreadLocal<Long> timer = new ThreadLocal<>();

	private TimeCounter() {
		throw new UnsupportedOperationException();
	}

	public static void start() {

		if (timer.get() != null) {
			throw Exceptions.argument("the counter was started.");
		}

		timer.set(Clock.now());
	}

	public static Long restart() {

		try {

			return watch();

		} finally {

			timer.set(Clock.now());

		}

	}

	public static Long watch() {

		Long start = timer.get();

		if (start == null) {
			throw Exceptions.argument("the counter not started.");
		}

		return Clock.now() - start;

	}

	public static Long stop() {

		try {

			return watch();

		} finally {

			timer.set(null);

		}

	}

}
