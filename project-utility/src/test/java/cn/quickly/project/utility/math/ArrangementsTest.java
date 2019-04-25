package cn.quickly.project.utility.math;

import java.util.Arrays;

import org.junit.Test;

import cn.quickly.project.utility.codec.Hex;
import cn.quickly.project.utility.reflect.Compat;

public class ArrangementsTest {

	@Test
	public void testSelect() {

		Arrangements.select(new String[] { "1", "2", "3", "4" }, 3, (row) -> {

			System.out.println(Arrays.toString(row));

		});

	}

	@Test
	public void testHex() {

		Arrangements.factorial(Compat.cast(Hex.LOWER_HEXS, String[].class), 6, (row) -> {

			System.out.println(Arrays.toString(row));

		});

	}

}
