package cn.quickly.project.utility.time;

import org.junit.Test;

import cn.quickly.project.utility.lang.Quiet;
import cn.quickly.project.utility.time.TimeCounter;

public class TimeCounterTest {

	@Test
	public void testWatch() {

		TimeCounter.start();

		Quiet.await(1000);

		System.out.println(TimeCounter.watch());

		Quiet.await(1000);

		System.out.println(TimeCounter.stop());

	}

	@Test
	public void testRestart() {

		TimeCounter.start();

		Quiet.await(1000);

		System.out.println(TimeCounter.restart());

		Quiet.await(1000);

		System.out.println(TimeCounter.stop());

	}

}
