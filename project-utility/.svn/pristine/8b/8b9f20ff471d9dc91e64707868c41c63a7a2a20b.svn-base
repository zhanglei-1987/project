package cn.quickly.project.utility.concurrent;

public final class Threads {

	private Threads() {

		throw new UnsupportedOperationException();

	}

	public static void daemon(Runnable runnable) {

		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.start();

	}

	public static void daemon(String name, Runnable runnable) {

		Thread thread = new Thread(runnable, name);
		thread.setDaemon(true);
		thread.start();

	}

}
