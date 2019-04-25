package cn.quickly.project.utility.time;

import java.time.LocalDate;
import java.util.Date;

public class LocalDates {

	private LocalDates() {
		throw new UnsupportedOperationException();
	}

	public static LocalDate parse(String date, String pattern) {

		return LocalDate.parse(date, DateTimeFormatters.of(pattern));

	}

	public static LocalDate of(Date date) {

		return LocalDate.ofEpochDay(date.getTime() / (24 * 3600000));

	}

	public static String format(LocalDate localDate, String pattern) {

		return localDate.format(DateTimeFormatters.of(pattern));

	}

}
