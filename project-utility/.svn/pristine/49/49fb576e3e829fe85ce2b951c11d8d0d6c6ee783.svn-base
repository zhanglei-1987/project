package cn.quickly.project.utility.thirdparty;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.lang.Loops;
import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.map.ComplexMap;

public class BerkeleysTest {

	@Test
	public void testMap() {

		Map<String, Double> map = Berkeleys.map(String.class, Double.class);
		map.put("demo1", 1d);
		map.put("demo2", 2d);

		System.out.println(map.get("demo2"));

		Iterables.forEach(map, (i, k, v) -> {

			System.out.println(k + "=" + v);

		});

	}

	@Test
	public void testSerialMap() {

		Map<String, Double[]> map = Berkeleys.map(String.class, Double[].class);
		map.put("demo1", Arrays.as(1d));
		map.put("demo2", Arrays.as(2d));

		System.out.println(map.get("demo2"));

		Iterables.forEach(map, (i, k, v) -> {

			System.out.println(k + "=" + v);

		});

	}

	@Test
	public void testSet() {

		Set<Double> set = Berkeleys.set(Double.class);
		set.add(1d);
		set.add(2d);

		Iterables.forEach(set, (i, v) -> {

			System.out.println(i + "=" + v);

		});

	}

	@Test
	public void testComplexSetMap() {

		Set<String> keySet = Berkeleys.set(String.class);

		Map<String, Set<Double>> map = new ComplexMap<>(keySet, (key) -> {

			return Berkeleys.set(Double.class);

		});

		map.get("demo").add(1d);
		map.get("demo").add(2d);

		Iterables.forEach(map, (i, k, v) -> {

			System.out.println(k + "=" + v);

		});

	}

	@Test
	public void testComplexMapMap() {

		Set<String> keySet = Berkeleys.set(String.class);

		Map<String, Map<String, Double>> map = new ComplexMap<>(keySet, (key) -> {

			return Berkeleys.map(String.class, Double.class);

		});

		map.get("demo").put("first", 1d);
		map.get("demo").put("second", 2d);

		Iterables.forEach(map, (i, k, v) -> {

			System.out.println(k + "=" + v);

		});

	}

	@Test
	public void testStore() {

		Map<String, String> map = Berkeleys.map(new File("d:/tmp/store"), "store", String.class, String.class);

		Loops.loop(1, 1000000, (i) -> {

			map.put("key" + i, "value" + i);

		});

		Loops.loop(1, 1000000, (i) -> {

			map.remove("key" + i);

		});

	}

	@Test
	public void testCompetePut() {

		Map<String, String> berkeleymap = Berkeleys.map(String.class, String.class);

		Map<String, String> hashmap = new HashMap<>();

		int size = 1000000;

		Printer.compete(new int[] { 10000, 100000, 10000000 }, (i) -> {

			berkeleymap.put("key" + i % size, "value" + i % size);

		}, (i) -> {

			hashmap.put("key" + i % size, "value" + i % size);

		});

	}

	@Test
	public void testCompeteGet() {

		Map<String, String> berkeleymap = Berkeleys.map(String.class, String.class);

		Map<String, String> hashmap = new HashMap<>();

		int size = 1000000;

		Loops.loop(1, size, (i) -> {

			berkeleymap.put("key" + i, "value" + i);

			hashmap.put("key" + i, "value" + i);

		});

		Printer.compete(new int[] { 10000, 100000, 10000000 }, (i) -> {

			berkeleymap.get("key" + i % size);

		}, (i) -> {

			hashmap.get("key" + i % size);

		});

	}

}
