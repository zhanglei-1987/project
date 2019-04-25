package cn.quickly.project.utility.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class PrintTask implements Runnable {

	private Object data;

	public PrintTask(Object data) {
		this.data = data;
	}

	public void run() {

		CountDownLatch latch = new CountDownLatch(1);

		try {
			latch.await(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(data);

	}

}
