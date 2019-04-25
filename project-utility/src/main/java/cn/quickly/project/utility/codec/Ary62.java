package cn.quickly.project.utility.codec;

public final class Ary62 {

	public static final char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	private Ary62() {
		throw new UnsupportedOperationException();
	}

	public static String encode(long number) {
		char[] buf = new char[65];
		int charPos = 64;
		boolean negative = (number < 0);

		if (!negative) {
			number = -number;
		}

		int radix = 62;

		while (number <= -radix) {
			buf[charPos--] = alphabet[(int) (-(number % radix))];
			number = number / radix;
		}
		buf[charPos] = alphabet[(int) (-number)];

		if (negative) {
			buf[--charPos] = '-';
		}

		return new String(buf, charPos, (65 - charPos));
	}

	public static long decode(String s) {
		long result = 0;
		boolean negative = false;
		int i = 0, max = s.length();
		long limit;
		long multmin;
		int digit;
		int radix = 62;

		if (max > 0) {
			if (s.charAt(0) == '-') {
				negative = true;
				limit = Long.MIN_VALUE;
				i++;
			} else {
				limit = -Long.MAX_VALUE;
			}

			multmin = limit / radix;

			if (i < max) {
				digit = digit(s.charAt(i++));
				if (digit < 0) {
					throw new NumberFormatException("For input string: \"" + s + "\"");
				} else {
					result = -digit;
				}
			}
			while (i < max) {
				// Accumulating negatively avoids surprises near MAX_VALUE
				digit = digit(s.charAt(i++));
				if (digit < 0) {
					throw new NumberFormatException("For input string: \"" + s + "\"");
				}
				if (result < multmin) {
					throw new NumberFormatException("For input string: \"" + s + "\"");
				}
				result *= radix;
				if (result < limit + digit) {
					throw new NumberFormatException("For input string: \"" + s + "\"");
				}
				result -= digit;
			}
		} else {
			throw new NumberFormatException("For input string: \"" + s + "\"");
		}
		if (negative) {
			if (i > 1) {
				return result;
			} else { /* Only got "-" */
				throw new NumberFormatException("For input string: \"" + s + "\"");
			}
		} else {
			return -result;
		}
	}

	private static int digit(char c) {
		for (int i = 0, length = alphabet.length; i < length; i++) {
			if (alphabet[i] == c) {
				return i;
			}
		}
		return 0;
	}

}
