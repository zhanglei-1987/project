package cn.quickly.project.utility.concurrent;

import org.junit.Test;

import cn.quickly.project.utility.lang.Loops;

public class AtomicCycleLongTest {

	@Test
	public void testIncrementAndGet() {

		AtomicCycleLong cycleLong = new AtomicCycleLong(-5, 5);

		Loops.loop(1, 20, (i) -> {

			System.out.println(cycleLong.incrementAndGet());

		});

	}

	@Test
	public void testDecrementAndGet() {

		AtomicCycleLong cycleLong = new AtomicCycleLong(-5, 5);

		Loops.loop(1, 20, (i) -> {

			System.out.println(cycleLong.decrementAndGet());

		});

	}

	@Test
	public void testGetAndAdd() {

		AtomicCycleLong cycleLong = new AtomicCycleLong(-5, 5);

		Loops.loop(1, 20, (i) -> {

			System.out.println(cycleLong.getAndAdd(i));

		});

	}

	@Test
	public void testAddAndGet() {

		AtomicCycleLong cycleLong = new AtomicCycleLong(-5, 5);

		Loops.loop(1, 20, (i) -> {

			System.out.println(cycleLong.addAndGet(i));

		});

	}

}
