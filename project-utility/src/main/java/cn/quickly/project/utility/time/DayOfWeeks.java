package cn.quickly.project.utility.time;

import java.time.DayOfWeek;

public final class DayOfWeeks {

	private DayOfWeeks() {
		throw new UnsupportedOperationException();
	}

	public static final DayOfWeek get(String date, String pattern) {

		return LocalDates.parse(date, pattern).getDayOfWeek();

	}

	public static final boolean between(String date, String pattern, DayOfWeek start, DayOfWeek end) {

		return between(get(date, pattern), start, end);

	}

	public static final boolean between(DayOfWeek target, DayOfWeek start, DayOfWeek end) {

		int value = target.getValue();

		return value >= start.getValue() && value <= end.getValue();

	}

}
