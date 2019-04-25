package cn.quickly.project.utility.time;

import org.junit.Test;

public class DayOfMonthsTest {

	@Test
	public void testIsFirstDay() {

		System.out.println(DayOfMonths.isFirstDay("2019-01-01", "yyyy-MM-dd"));

		System.out.println(DayOfMonths.isFirstDay("2019-01-31", "yyyy-MM-dd"));

	}

	@Test
	public void testIsLastDay() {

		System.out.println(DayOfMonths.isLastDay("2019-01-01", "yyyy-MM-dd"));

		System.out.println(DayOfMonths.isLastDay("2019-01-31", "yyyy-MM-dd"));

	}

}
