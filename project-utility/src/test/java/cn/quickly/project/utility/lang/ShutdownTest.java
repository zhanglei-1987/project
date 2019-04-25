package cn.quickly.project.utility.lang;

import org.junit.Test;

public class ShutdownTest {

	@Test
	public void testAddHook() {

		Loops.loop(1, 1000, (i) -> {

			Shutdown.addHook(() -> {

				System.out.println(i);

			});

		});

	}

}
