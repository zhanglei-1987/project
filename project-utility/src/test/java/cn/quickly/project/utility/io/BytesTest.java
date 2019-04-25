package cn.quickly.project.utility.io;

import java.util.Arrays;

import org.junit.Test;

public class BytesTest {

	@Test
	public void testMerge() {

		byte[] first = { 1, 2, 3, 4 }, second = { 5, 6, 7, 8, 9, 0 };

		System.out.println(Arrays.toString(Bytes.merge(first, second)));

	}

	@Test
	public void testCopy() {

		byte[] original = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };

		System.out.println(Arrays.toString(Bytes.copy(original, 2)));

	}
}
