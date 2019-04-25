package cn.quickly.project.utility.codec;

public final class Luhn {

	private Luhn() {
		throw new UnsupportedOperationException();
	}

	public static char code(String text) {

		char[] chs = text.trim().toCharArray();

		int luhnSum = 0;

		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhnSum += k;
		}

		return (luhnSum % 10 == 0) ? '0' : (char) ((10 - luhnSum % 10) + '0');

	}

}
