package cn.quickly.project.utility.map;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Loops;
import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.mock.MockDemo;

public class MapsTest {

	@Test
	public void testGetMap() {

		Properties properties = new Properties();
		properties.setProperty("name", "test");

		System.out.println(Maps.getMap(properties));

		System.out.println(Maps.getMap(new MockDemo()));

	}

	@Test
	public void testGetMapUseJdk() {

		Properties properties = new Properties();
		properties.setProperty("name", "test");

		System.out.println(Maps.getMapUseMethod(properties));

		System.out.println(Maps.getMapUseMethod(new MockDemo()));

	}

	@Test
	public void testAsMap() {

		System.out.println(Maps.asMap("name", "test"));

	}

	@Test
	public void testFlat() {

		Object value1 = Maps.asMap("name", "zhanglei");

		Object value2 = Maps.multi(Arrays.as("name", "age"), Arrays.as("zhanglei1", "30"));

		Object[] values = Arrays.as(value1, value2);

		Printer.println(Maps.flat(values, String.class, "name"));

		Printer.println(Maps.flat(values, String.class, "name", Integer.class, "age"));

	}

	@Test
	public void testMatch() {

		Map<String, String> source = Maps.asMap("mock.name", "zhanglei");
		source.put("test.name", "test name");

		Printer.println(Maps.match(source, "*.name", true));

	}

	@Test
	public void testTop() {

		Map<String, Double> source = Maps.asMap("k1", 1d);

		Loops.loop(2, 50, (i) -> source.put("k" + i, (double) i));

		System.out.println(Maps.top(source, 5));

		System.out.println(new Double(1).compareTo(2d));

	}

}
