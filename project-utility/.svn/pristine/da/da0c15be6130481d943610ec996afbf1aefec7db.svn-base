package cn.quickly.project.utility.time;

import java.time.DayOfWeek;

import org.junit.Test;

public class DayOfWeeksTest {

	@Test
	public void testGet() {

		System.out.println(DayOfWeeks.get("2019-01-04", "yyyy-MM-dd"));

	}

	@Test
	public void testBewtten() {

		DayOfWeek target = DayOfWeeks.get("2019-01-04", "yyyy-MM-dd");

		System.out.println(DayOfWeeks.between(target, DayOfWeek.MONDAY, DayOfWeek.SUNDAY));

		System.out.println(DayOfWeeks.between(target, DayOfWeek.MONDAY, DayOfWeek.FRIDAY));

		System.out.println(DayOfWeeks.between(target, DayOfWeek.MONDAY, DayOfWeek.THURSDAY));

	}

}
