package cn.quickly.project.utility.time;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.quickly.project.utility.concurrent.Locks;
import cn.quickly.project.utility.lang.Quiet;

public final class DateTimeFormatters {

	public static final Map<String, DateTimeFormatter> formatters = new ConcurrentHashMap<>();

	private DateTimeFormatters() {
		throw new UnsupportedOperationException();
	}

	public static DateTimeFormatter of(String pattern) {

		return Quiet.tryCatch(() -> Locks.dcl(formatters, pattern, (p) -> {

			return DateTimeFormatter.ofPattern(pattern);

		}));

	}

	public static void clear() {

		formatters.clear();

	}

}
