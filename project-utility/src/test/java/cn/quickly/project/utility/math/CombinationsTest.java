package cn.quickly.project.utility.math;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

import cn.quickly.project.utility.collection.Collections;
import cn.quickly.project.utility.lang.Loops;

public class CombinationsTest {

	@Test
	public void testSelect() {

		String[] datas = Collections.toArray(String.class, Loops.repeat(1, 33, (i) -> i + ""));

		System.out.println(Maths.factorial(BigInteger.valueOf(33)));

		System.out.println(Maths.combination(4, 3));

		System.out.println(Maths.combination(33, 6) * 16);

		Combinations.select(datas, 6, (row) -> {

			System.out.println(Arrays.toString(row));

		});

	}

}
