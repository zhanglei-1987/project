package cn.quickly.project.utility.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.quickly.project.utility.lang.Loops;
import cn.quickly.project.utility.lang.Quiet;

public class ThreadPoolsTest {

	@Test
	public void testPrint() {

		CountDownLatch latch = new CountDownLatch(1);

		ExecutorService executorService = ThreadPools.dotp(1);

		for (int i = 0; i < 10; i++) {

			executorService.execute(new PrintTask(i));

		}

		try {
			latch.await(2, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		executorService.shutdown();

	}

	@Test
	public void testQuiescent() {

		Loops.loop(1, 20, (i) -> {

			ThreadPools.execute(() -> {

				Quiet.await(i * 50);

				System.out.println(i);

			});

		});

		for (; !ThreadPools.isQuiescent();) {

			Quiet.await(2000);

		}

	}

	@Test
	public void testExecutorServiceQuiescent() {

		ExecutorService executor = ThreadPools.dotp(2);

		Loops.loop(1, 20, (i) -> {

			executor.execute(() -> {

				Quiet.await(i * 50);

				System.out.println(i);

			});

		});

		for (; !ThreadPools.isQuiescent(executor);) {

			Quiet.await(2000);

		}

	}

	@Test
	public void testBlocking() {

		ExecutorService executor = ThreadPools.btp(1, 5, 10);

		Loops.loop(1, 100, (i) -> {

			executor.execute(() -> {

				Quiet.await(i * 50);

				System.out.println(i * 100);

			});

			System.out.println(i);

		});

		for (; !ThreadPools.isQuiescent(executor);) {

			Quiet.await(2000);

		}

	}

}
