package cn.quickly.project.utility.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import cn.quickly.project.utility.lang.Loops;
import cn.quickly.project.utility.lang.Printer;

public class ListTest {

	@Test
	public void testAdd() {

		List<Integer> array = new ArrayList<>();

		List<Integer> linked = new LinkedList<>();

		Printer.compete(new int[] { 100000, 1000000, 10000000 }, (i) -> array.add(i), (i) -> linked.add(i));

	}

	@Test
	public void testGet() {

		List<Integer> array = new ArrayList<>();

		List<Integer> linked = new LinkedList<>();

		int count = 10000;

		Loops.loop(1, count, (i) -> {

			array.add(i - 1);

			linked.add(i - 1);

		});

		Printer.compete(new int[] { 100000, 1000000, 10000000 }, (i) -> array.get(i % count), (i) -> linked.get(i % count));

	}

}
