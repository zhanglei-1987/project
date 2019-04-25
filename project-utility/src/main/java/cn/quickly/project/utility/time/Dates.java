package cn.quickly.project.utility.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;

import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.text.FormatFactory;

public class Dates {

	public static java.util.Date date(int year, int month, int day) {

		return new java.util.Date(Clock.millis(year, month, day, 0, 0, 0, 0));

	}

	public static java.util.Date date(String text, String format) {

		try {

			if (Strings.isEmpty(text)) {
				return null;
			}

			FormatFactory formatFactory = FormatFactory.getFactory();

			DateFormat dateFormat = formatFactory.getDateFormat(format);

			return dateFormat.parse(text);

		} catch (ParseException e) {
			throw Exceptions.argument(e);
		}

	}

	public static java.util.Date date(LocalDate localDate) {

		return date(localDate, ZoneId.systemDefault());

	}

	public static java.util.Date date(LocalDate localDate, ZoneId zoneId) {

		return java.util.Date.from(localDate.atStartOfDay().atZone(zoneId).toInstant());

	}

	public static java.sql.Date sqldate(int year, int month, int day) {

		return new java.sql.Date(Clock.millis(year, month, day, 0, 0, 0, 0));

	}

	public static java.sql.Date sqldate(String text, String format) {

		java.util.Date date = date(text, format);

		if (date == null) {
			return null;
		}

		return new java.sql.Date(date.getTime());

	}

	public static java.sql.Date sqldate(LocalDate localDate) {

		return sqldate(localDate, ZoneId.systemDefault());

	}

	public static java.sql.Date sqldate(LocalDate localDate, ZoneId zoneId) {

		return new java.sql.Date(localDate.atStartOfDay().atZone(zoneId).toInstant().toEpochMilli());

	}

}
