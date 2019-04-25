package cn.quickly.project.utility.time;

import java.util.Calendar;
import java.util.Date;

import cn.quickly.project.utility.text.Formats;

public final class Calendars {

	private Calendars() {
		throw new UnsupportedOperationException();
	}

	public static String getOffsetDate(String date, String format, int offsetYear, int offsetMonth, int offsetDay) {

		return Formats.format(getOffsetDate(Dates.date(date, format), offsetYear, offsetMonth, offsetDay), format);

	}

	public static Date getOffsetDate(Date date, int offsetYear, int offsetMonth, int offsetDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, offsetYear);
		calendar.add(Calendar.MONTH, offsetMonth);
		calendar.add(Calendar.DAY_OF_MONTH, offsetDay);
		return calendar.getTime();
	}

	public static boolean isLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();

		int day = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.add(Calendar.DAY_OF_MONTH, 1);

		int nextDay = calendar.get(Calendar.DAY_OF_MONTH);

		return nextDay < day;
	}

	public static boolean isLastDayOfMonth(String text, String format) {
		return isLastDayOfMonth(Dates.date(text, format));
	}

	public static boolean isLastDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);

		calendar.add(Calendar.YEAR, 1);

		int nextYear = calendar.get(Calendar.YEAR);

		return nextYear < year;
	}

	public static boolean isLastDayOfYear(String text, String format) {
		return isLastDayOfYear(Dates.date(text, format));
	}

}
