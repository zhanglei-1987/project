package cn.quickly.project.utility.reflect;

import static java.lang.System.out;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.mock.MockList;
import cn.quickly.project.utility.time.Clock;

public class CompatTest {

	@Test
	public void testBoolean() {

		Printer.println(Compat.get(null, boolean.class));

		Printer.println(Compat.get("1", boolean.class));

		Printer.println(Compat.get("0", boolean.class));

		Printer.println(Compat.get("true", boolean.class));

		Printer.println(Compat.get("false", boolean.class));

		Printer.println(Compat.get("on", boolean.class));

		Printer.println(Compat.get("off", boolean.class));

	}

	@Test
	public void testBigDecimal() {

		Class<?> targetType = BigDecimal.class;
		String text = "1231999999999999999999999999999999999";
		out.println(Compat.get(text, targetType));

		BigDecimal decimal = new BigDecimal(12312233);
		out.println(Compat.get(decimal, Long.class));
	}

	@Test
	public void testTime() {

		System.out.println(Compat.get(Clock.now(), Time.class));

	}

	@Test
	public void testArray() {

		double[] arrayd = { 12d, 3412d };

		System.out.println(Compat.get(arrayd, int[].class));
		System.out.println(Compat.get(arrayd, Integer[].class));

		String[] array = { "12", "3412" };

		System.out.println(Compat.get(array, double[].class));

		List<Object> list = new ArrayList<Object>();
		list.add("1243");
		list.add("6854854");

		System.out.println(Compat.get(list, float[].class));

	}

	@Test
	public void testCollection() {

		System.out.println(Set.class.isAssignableFrom(ArrayList.class));

		List<Object> list = new ArrayList<Object>();
		list.add("1243");
		list.add("6854854");

		System.out.println(Compat.get(list, LinkedList.class).getClass());

		System.out.println(Compat.get(list, Set.class, int.class).getClass());

		System.out.println(Compat.get(list, Queue.class, null).getClass());

	}

	@Test
	public void testUrl() {

		System.out.println(Compat.get("http://www.baidu.com", URL.class).getClass());

	}

	@Test
	public void testDouble() {

		System.out.println(Compat.cast(new Double(8.022961353139232E-7), double.class));

		Double[] sample = { 8.022961353139232E-7, 4.793973793248826E-7 };

		System.out.println(Compat.cast(sample, double[].class));

		System.out.println(Compat.cast(11, Double.class).getClass());

	}

	@Test
	public void testGenericType() {

		List<Double> numbers = Compat.cast(new String[] { "1", "2" }, new GenericType<List<Double>>() {
		});

		System.out.println(numbers);
		System.out.println(numbers.get(0).getClass());

		Map<Double, Double> map = Compat.cast(Maps.asMap("1", "2"), new GenericType<Map<Double, Double>>() {
		});

		System.out.println(map);

		System.out.println(map.get(1d));

	}

	@Test
	public void testList() {

		List<Double> numbers = Compat.list(new String[] { "1", "2" }, Double.class);

		System.out.println(numbers);
		System.out.println(numbers.get(0).getClass());

		MockList urls = Compat.cast(new String[] { "http://s.cn" }, MockList.class);

		System.out.println(urls);
		System.out.println(urls.get(0).getClass());

	}

	@Test
	public void testSet() {

		Set<Double> numbers = Compat.set(new String[] { "1", "2" }, Double.class);

		System.out.println(numbers);
		System.out.println(numbers.iterator().next().getClass());

	}

	@Test
	public void testMap() {

		Map<Double, Double> map = Compat.map(Maps.asMap("1", "2"), Double.class, Double.class);

		System.out.println(map);

		System.out.println(map.get(1d));

	}

	@Test
	public void testClass() {

		System.out.println(Compat.get("cn.quickly.project.utility.map.Maps", Class.class));

	}

}
