package cn.quickly.project.utility.lang;

public class Characters {

	private Characters() {
		throw new UnsupportedOperationException();
	}

	public static boolean isChinese(char c) {

		if ((c >= '\u4e00' && c <= '\u9fa5') || (c >= '\uf900' && c <= '\ufa2d')) {
			return true;
		}

		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}

		return false;

	}

	public static boolean isSpaceChar(char c) {
		return c == 65279 || c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == '\b' || c == '\f';
	}

}
