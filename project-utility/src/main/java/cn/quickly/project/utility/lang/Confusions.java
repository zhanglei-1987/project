package cn.quickly.project.utility.lang;

public final class Confusions {

	private Confusions() {
		throw new UnsupportedOperationException();
	}

	public static String idCard(String cardNumber) {
		return hide(cardNumber, 1, 1, '*');
	}

	public static String bankCard(String cardNumber) {
		return hide(cardNumber, 6, 4, '*');
	}

	public static String mobile(String mobile) {

		return hide(mobile, 3, 4, '*');

	}

	public static String hide(String text, int prefix, int suffix, char delimiter) {

		StringBuilder builder = new StringBuilder(text.length());

		char[] chars = text.toCharArray();

		for (int i = 0, start = prefix, end = chars.length - suffix - 1; i < chars.length; i++) {

			if (i >= start && i <= end) {
				builder.append(delimiter);
			} else {
				builder.append(chars[i]);
			}

		}
		return builder.toString();
	}

}
