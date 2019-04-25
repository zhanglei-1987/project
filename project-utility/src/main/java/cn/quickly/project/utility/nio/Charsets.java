package cn.quickly.project.utility.nio;

import java.nio.charset.Charset;

public final class Charsets {

	public final static Charset UTF_8 = Charset.forName("UTF-8");

	private Charsets() {
		throw new UnsupportedOperationException();
	}

}
