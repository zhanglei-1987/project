package cn.quickly.project.utility.time;

import java.util.Date;

import org.junit.Test;

import cn.quickly.project.utility.text.Formats;

public class ClockTest {

	@Test
	public void testNow() {

		System.out.println(Clock.now());

	}

	@Test
	public void testMillis() {

		String format = "yyyy-MM-dd HH:mm:ss.SSS";

		System.out.println(Formats.format(new Date(Clock.millis(2019, 4, 20, 0, 0, 0, 0)), format));
		System.out.println(Formats.format(new Date(Clock.millis(2019, 4, 20, 14, 10, 10, 888)), format));

	}

}
