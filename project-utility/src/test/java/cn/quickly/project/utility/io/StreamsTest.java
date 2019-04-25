package cn.quickly.project.utility.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import org.junit.Test;

public class StreamsTest {

	@Test
	public void testOpenClose() throws IOException {

		String text = "missingMessage:'[key=value]多条请换行:<br/>jdbc.driver<br/>jdbc.url<br/>jdbc.user<br/>jdbc.password'\"";

		System.out.println(Streams.getString(new StringReader(text), '\"', '\"', true));

	}

	@Test
	public void testEncoding() throws IOException {

		String text = "rflzl@www.SexinSex.net@å·¨æ³¢ç¾å°å¥³æ·«ä¹± çä¹³åå¨å¤§ä¹±äº¤\\å·¨æ³¢ç¾å°å¥³æ·«ä¹± çä¹³åå¨å¤§ä¹±äº¤.jpg";

		System.out.println(new String(text.getBytes("ISO-8859-1"), "UTF-8"));

	}

	@Test
	public void testGetBytesSE() throws Exception {

		InputStream in = StreamSeeker.classpath("/acs.dat");

		System.out.println(new String(Streams.getBytes(in, '{', '}')));

		System.out.println(new String(Streams.getBytes(in, '{', '}')));

	}

}
