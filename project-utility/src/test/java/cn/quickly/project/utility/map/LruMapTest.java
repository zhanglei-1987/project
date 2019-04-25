package cn.quickly.project.utility.map;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;

public class LruMapTest {

	@Test
	public void testCapacity() {

		LruMap<String, String> map = new LruMap<>(5);

		for (int i = 0; i < 6; i++) {

			map.put("key" + i, "value" + i);

		}

		System.out.println(map.get("key2"));

		for (int i = 6; i < 10; i++) {

			map.put("key" + i, "value" + i);

		}

		Printer.println(map);

	}

	@Test
	public void testConcurrent() throws Exception {

		Executor executor = Executors.newFixedThreadPool(5);

		final CountDownLatch latch = new CountDownLatch(3);

		final LruMap<String, String> map = new LruMap<>(5);

		executor.execute(new Runnable() {

			public void run() {

				for (int i = 0; i < 6; i++) {

					map.put("key" + i, "value" + i);

				}

				latch.countDown();

			}

		});

		executor.execute(new Runnable() {

			public void run() {

				System.out.println(map.get("key2"));

				latch.countDown();

			}

		});

		executor.execute(new Runnable() {

			public void run() {

				for (int i = 6; i < 10; i++) {

					map.put("key" + i, "value" + i);

				}

				latch.countDown();

			}

		});

		latch.await();

		Printer.println(map);

	}
}
