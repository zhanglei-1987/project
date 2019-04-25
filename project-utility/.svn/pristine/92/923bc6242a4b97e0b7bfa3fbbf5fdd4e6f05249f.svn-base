package cn.quickly.project.utility.lang;

import org.junit.Test;

public class IterablesTest {

	@Test
	public void testD1ArrayForEach() {

		Iterables.forEach(new Double[] { 1d, 2d, 3d, 4d }, (i, value) -> {
			System.out.println(i + ":" + value);
		});

		
	}

	@Test
	public void testThrowableForEach() {

		Throwable t = new Throwable(Exceptions.argument("second"));

		Iterables.forEach(t, (i, e) -> System.out.println(i + "===>" + e));

	}

}
