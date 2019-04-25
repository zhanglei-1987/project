package cn.quickly.project.utility.time;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import cn.quickly.project.utility.reflect.Classes;

public final class Clock {

	private Clock() {
		throw new UnsupportedOperationException();
	}

	public static long now() {
		return System.currentTimeMillis();
	}

	@SuppressWarnings("unchecked")
	public static <T extends Date> T now(Class<T> targetClass) {

		if (Classes.isSuperClass(java.sql.Date.class, targetClass)) {

			return (T) new java.sql.Date(now());

		} else if (Classes.isSuperClass(Timestamp.class, targetClass)) {

			return (T) new Timestamp(now());

		} else if (Classes.isSuperClass(Time.class, targetClass)) {

			return (T) new Time(now());

		}

		return (T) new Date(now());
	}

	public static long millis(int year, int month, int day, int hour, int minute, int second, int millisecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millisecond);
		return calendar.getTimeInMillis();
	}

}
