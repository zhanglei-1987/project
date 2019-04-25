package cn.quickly.project.utility.codec;

import org.junit.Test;

public class DriftTest {

	@Test
	public void testEncode() {

		System.out.println(Drift.encode("123456", 2));

	}

	@Test
	public void testDecode() {

		System.out.println(Drift.decode("436521", 2));

	}

}
