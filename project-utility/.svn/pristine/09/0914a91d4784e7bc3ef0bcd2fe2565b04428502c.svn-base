package cn.quickly.project.utility.codec;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;

public class HexTest {

	@Test
	public void test() {

		byte[] bytes = { 45, 23, 45, 22, 78, 123, 49 };

		String hex = Hex.lower(bytes);

		System.out.println(hex);

		hex = Hex.upper(bytes);

		System.out.println(hex);

		Printer.dump(Hex.bytes(hex));

	}

	@Test
	public void testDump() throws Throwable {

		byte[] bytes = { 45, 23, 45, 22, 78, 123, 49 };

		System.out.println(Hex.dump(bytes));

		File file = new File("src/test/resources/epcc.jks");

		InputStream in = new FileInputStream(file);

		Hex.dump(in, System.out);
	}

	@Test
	public void testBytes() {
		byte[] macKey = Hex.bytes("0808080808080808");
		System.out.println(Hex.upper(macKey));
	}

}
