package cn.quickly.project.utility.codec;

import cn.quickly.project.utility.lang.Strings;

public final class Drift {

	private Drift() {
		throw new UnsupportedOperationException();
	}

	public static String encode(String text, int offset) {

		String first = Strings.reverse(text.substring(0, offset));

		String second = Strings.reverse(text.substring(offset));

		return Strings.swap(Strings.reverse(Strings.concat(first, second)));
	}

	public static String decode(String text, int offset) {

		text = Strings.reverse(Strings.swap(text));

		String first = Strings.reverse(text.substring(0, offset));

		String second = Strings.reverse(text.substring(offset));

		return Strings.concat(first, second);
	}

}
