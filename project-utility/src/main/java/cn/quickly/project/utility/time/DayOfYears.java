package cn.quickly.project.utility.time;

import java.time.LocalDate;

public final class DayOfYears {

	private DayOfYears() {
		throw new UnsupportedOperationException();
	}

	public static boolean isFirstDay(String text, String pattern) {

		return isFirstDay(LocalDates.parse(text, pattern));

	}

	public static boolean isFirstDay(LocalDate localDate) {

		return localDate.getDayOfYear() == 1 && localDate.getDayOfMonth() == 1;

	}

	public static boolean isLastDay(String text, String pattern) {

		return isLastDay(LocalDates.parse(text, pattern));

	}

	public static boolean isLastDay(LocalDate localDate) {

		return localDate.getMonthValue() == 12 && localDate.getDayOfMonth() == localDate.getMonth().maxLength();

	}

}
