package cn.quickly.project.utility.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Patterns {

	private static final Map<String, Pattern> patternCache = new ConcurrentHashMap<String, Pattern>();

	private static final Lock lock = new ReentrantLock();

	private Patterns() {
		throw new UnsupportedOperationException();
	}

	public static boolean matches(String text, String regex) {

		return matcher(text, regex).matches();

	}

	public static Matcher matcher(String text, String regex) {

		return matcher(text, regex, Pattern.CASE_INSENSITIVE);

	}

	public static Matcher matcher(String text, String regex, int flags) {

		String key = regex + "-" + flags;

		Pattern pattern = patternCache.get(key);

		if (pattern == null) {

			lock.lock();

			try {

				pattern = patternCache.get(key);

				if (pattern == null) {

					pattern = Pattern.compile(regex, flags);

					patternCache.put(key, pattern);

				}

			} finally {
				lock.unlock();
			}

		}

		return pattern.matcher(text);
	}

	public static String[] texts(String text, String regex, int part) {

		List<String> list = new ArrayList<String>();

		Matcher matcher = matcher(text, regex);

		while (matcher.find()) {
			list.add(matcher.group(part));
		}

		return list.toArray(new String[list.size()]);

	}

	public static String text(String text, String regex, int part) {

		Matcher matcher = matcher(text, regex);

		if (matcher.find()) {
			return matcher.group(part);
		}

		return null;
	}

	public static String text(String text, String regex, int part, int index) {

		String[] texts = texts(text, regex, part);

		if (texts.length > 0 && index < texts.length) {
			return texts[index];
		}

		return null;

	}

}
