package cn.quickly.project.utility.servlet;

import cn.quickly.project.utility.map.TextManager;

public final class Mimes {

	private static TextManager mimes = TextManager.getManager("find:mime.properties");

	private Mimes() {
		throw new UnsupportedOperationException();
	}

	public static String getContentType(String extension) {
		return getContentType(extension, "application/octet-stream");
	}

	public static String getContentType(String extension, String defautContentType) {
		return mimes.getDefaultText(extension, defautContentType);
	}

}
