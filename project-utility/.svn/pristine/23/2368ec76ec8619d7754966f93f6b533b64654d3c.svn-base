package cn.quickly.project.utility.time;

import java.sql.Timestamp;

public final class Timestamps {

	private Timestamps() {
		throw new UnsupportedOperationException();
	}

	public static Timestamp timestamp(int year, int month, int day, int hour, int minute, int second, int millisecond) {

		return new Timestamp(Clock.millis(year, month, day, hour, minute, second, millisecond));

	}

	public static Timestamp timestamp(String text, String format) {

		return new Timestamp(Dates.date(text, format).getTime());

	}

}
