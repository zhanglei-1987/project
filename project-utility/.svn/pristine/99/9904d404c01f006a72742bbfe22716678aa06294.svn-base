package cn.quickly.project.utility.lang;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.map.Maps;

public class ImmutablesTest {

	@Test
	public void testProperties() {

		Properties properties = new Properties();
		properties.put("name", "mock");

		Properties immutable = Immutables.properties(properties);

		System.out.println(immutable.getProperty("name"));

		immutable.remove("name");

	}

	@Test
	public void testMap() {

		Map<String, String> set = Maps.asMap("name", "mock");

		Map<String, String> immutable = Immutables.map(set);

		System.out.println(immutable.get("name"));

		immutable.remove("name");

	}

	@Test
	public void testSet() {

		Set<String> set = Arrays.asSet("mock");

		Set<String> immutable = Immutables.set(set);

		Iterables.forEach(immutable, (i, item) -> {
			System.out.println(item);
		});

		System.out.println(immutable.remove("mock"));

	}

	@Test
	public void testList() {

		List<String> set = Arrays.asList("mock");

		List<String> immutable = Immutables.list(set);

		Iterables.forEach(immutable, (i, item) -> {
			System.out.println(item);
		});

		System.out.println(immutable.remove("mock"));

	}

}
