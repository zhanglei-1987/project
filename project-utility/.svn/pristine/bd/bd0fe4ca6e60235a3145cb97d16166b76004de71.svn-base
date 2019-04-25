package cn.quickly.project.utility.time;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Test;

public class LocalDatesTest {

	@Test
	public void testParse() {

		LocalDate localDate = LocalDates.parse("2019-01-04", "yyyy-MM-dd");

		System.out.println(localDate);

		System.out.println(localDate.getDayOfWeek());

		System.out.println(localDate.getDayOfMonth());

		System.out.println(localDate.getMonth().minLength());

		System.out.println(localDate.getMonth().maxLength());

		System.out.println(LocalDate.ofEpochDay(Clock.now() / (24 * 3600000)));

	}

	@Test
	public void testOf() {

		System.out.println(LocalDates.of(new Date()));

	}

	@Test
	public void testFormat() {

		System.out.println(LocalDates.format(LocalDate.now(), "yyyy-MM-dd"));

	}

}
