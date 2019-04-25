package cn.quickly.project.utility.lang;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Shutdown {

	private static Deque<Runnable> queue = new ConcurrentLinkedDeque<>();

	static {

		Thread thread = new Thread(() -> {

			for (;;) {

				Runnable runnable = queue.pollLast();

				if (runnable != null) {

					try {
						runnable.run();
					} catch (Exception e) {
					}

				} else {
					break;
				}

			}

		});

		thread.setName("Shutdown Hook Thread");

		Runtime.getRuntime().addShutdownHook(thread);

		queue.offer(() -> Quiet.clear());

	}

	public static void addHook(Runnable runnable) {
		queue.offer(runnable);
	}

	public static void removeHook(Runnable runnable) {
		queue.remove(runnable);
	}

}
