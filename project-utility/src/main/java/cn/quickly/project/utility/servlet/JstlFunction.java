package cn.quickly.project.utility.servlet;

import cn.quickly.project.utility.json.JSONParser;
import cn.quickly.project.utility.lang.Confusions;
import cn.quickly.project.utility.lang.Strings;

public class JstlFunction {

	public static String tail(String text, Integer length) {
		if (Strings.isEmpty(text)) {
			return "";
		}
		return text.substring(text.length() - length, text.length());
	}

	public static Object json(String text) {
		try {
			return JSONParser.parse(text);
		} catch (Throwable e) {
			return null;
		}
	}

	public static String hide(String text, int prefix, int suffix, char delimiter) {
		try {
			return Confusions.hide(text, prefix, suffix, delimiter);
		} catch (Throwable e) {
			return "";
		}
	}

}
