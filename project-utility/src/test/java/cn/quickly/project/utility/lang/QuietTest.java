package cn.quickly.project.utility.lang;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;

public class QuietTest {

	@Test
	public void testTryClose() throws IOException {

		try (Closeable c = new PrintCloseable()) {

			System.out.println(c);

		}

	}

	class PrintCloseable implements Closeable {

		@Override
		public void close() throws IOException {

			System.out.println("关闭");

		}

	}

}
