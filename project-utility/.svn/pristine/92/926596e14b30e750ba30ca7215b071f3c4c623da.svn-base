package cn.quickly.project.utility.text;

import java.util.Date;

import cn.quickly.project.utility.lang.Strings;

public final class Formats {

	private static final FormatFactory FACTORY = FormatFactory.getFactory();

	private Formats() {
		throw new UnsupportedOperationException();
	}

	public static String format(Date date, String pattern) {

		return FACTORY.getDateFormat(pattern).format(date);

	}

	public static String format(Number number, String pattern) {

		return FACTORY.getNumberFormat(pattern).format(number);

	}

	public static String format(String pattern, String... texts) {

		return FACTORY.getMessageFormat(pattern).format(texts);

	}

	public static String format(Object value, String pattern) {

		if (value == null) {
			return null;
		}

		if (!Strings.isEmpty(pattern)) {

			if (value instanceof Date) {

				return Formats.format((Date) value, pattern);

			} else if (value instanceof Number) {

				return Formats.format((Number) value, pattern);

			} else if (value instanceof String) {

				return Formats.format(pattern, (String) value);

			} else if (value instanceof String[]) {

				return Formats.format(pattern, (String[]) value);

			}

		}

		return Strings.valueOf(value);

	}

}
