package cn.quickly.project.utility.time;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

public final class Times {

	private Times() {
		throw new UnsupportedOperationException();
	}

	public static Time time(String text, String format) throws ParseException {

		Date date = Dates.date(text, format);

		if (date == null) {
			return null;
		}

		return new Time(date.getTime());

	}

	public static Time time(int year, int month, int day, int hour, int minute, int second, int millisecond) {
		return new Time(Clock.millis(year, month, day, hour, minute, second, millisecond));
	}

}
