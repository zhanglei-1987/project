package cn.quickly.project.utility.math;

import org.junit.Test;

import cn.quickly.project.utility.lang.Loops;

public class MathsTest {

	@Test
	public void testCeil() {

		int a = 3, b = 2;

		System.out.println(Maths.ceil(a, b));

	}

	@Test
	public void testLog() {

		Loops.loop(1, 10, (i) -> {

			System.out.println(Maths.log(3, i));

		});

	}

	@Test
	public void testSigmod() {

		System.out.println(Maths.sigmod(12));

	}

	@Test
	public void testCos() {

		double[] a = { 1, -2, 3, 4 };
		double[] b = { 1, 6, -7, 1000 };

		System.out.println(Maths.cos(a, b));

		double[] c = { 3 };

		double[] d = { 4 };

		System.out.println(Maths.cos(c, d));

		// (a^2+b^2-c^2)/2ab
		System.out.println(Math.cos((3 * 3 + 4 * 4 - 5 * 5) / 2 * 3 * 4));

	}

	@Test
	public void testHypot() {

		double[] a = { 1, -2, 3, 4 };
		double[] b = { 1, 6, -7, 10 };

		System.out.println(Maths.hypot(a, b));

		double[] c = { 3, 0 };

		double[] d = { 0, 4 };

		System.out.println(Maths.hypot(c, d));

		System.out.println(Math.hypot(3, 4));

	}

}
