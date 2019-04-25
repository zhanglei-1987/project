package cn.quickly.project.utility.thirdparty;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.map.SupplierHashMap;
import cn.quickly.project.utility.mock.MockDemo;
import cn.quickly.project.utility.text.Formats;

public class OgnlsTest {

	@Test
	public void testTokenedSupplier() {

		Map<String, Object> data = new SupplierHashMap<>((key) -> {

			Calendar calendar = Calendar.getInstance();
			System.out.println(Formats.format(calendar.getTime(), "yyyy-MM-dd"));

			return Formats.format(calendar.getTime(), "yyyy-MM-dd");

		}, false);

		System.out.println(Ognls.tokened("name-${now}", data));

	}

	@Test
	public void testTokened() {

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("n", BigDecimal.valueOf(10));

		Properties properties = new Properties();
		properties.setProperty("name", "zhanglei");
		properties.put("test", "-2--2");
		properties.put("date", new Date());

		data.put("p", properties);

		String text = "\"'${p.name}'\"|${ p.name: }|${p.test.replaceAll('-','')} | ${p.date:yyyy-MM-dd HH:mm:ss} | ${ n.longValue() } | ${ n/3 }";

		System.out.println(Ognls.tokened(text, data));
	}

	@Test
	public void testFilter() {

		Printer.println(Ognls.filter(Arrays.asList(1, 2, 3, 4, 5, 6), "item > 4"));

	}

	@Test
	public void testMap() {

		Properties properties = new Properties();
		properties.setProperty("name", "zhanglei");
		properties.put("test", "-2--2");
		properties.put("date", new Date());

		Printer.println(Ognls.map(Arrays.asList(properties), String.class, "item.test + '-fuck'"));

	}

	@Test
	public void testFlatMap() {

		Properties properties = new Properties();
		properties.setProperty("name", "zhanglei");
		properties.put("test", "-2--2");
		properties.put("date", new Date());

		Printer.println(Ognls.flatMap(Arrays.asList(properties), String.class, "item.test + '-fuck'"));

	}

	@Test
	public void testForEach() {

		Properties properties = new Properties();
		properties.setProperty("name", "zhanglei");

		Ognls.forEach(Arrays.asList(properties), "item.put('age',30)", "item.put('age2',30)");

		Printer.println(properties);

	}

	@Test
	public void testSet() {

		MockDemo demo = new MockDemo();

		Ognls.set(demo, "name", "fuck");

		System.out.println(demo);

	}

	@Test
	public void testMulti() {

		Map<String, Object> data = new HashMap<>();

		Ognls.execute("data.put('a',1), data.put('b',1)", Maps.asMap("data", data));

		System.out.println(data);

	}

	@Test
	public void testGetValue() {

		System.out.println(Ognls.getValue("name", Maps.asMap("name", "fuck"), String.class));

		System.out.println(Ognls.getValue("name.name", null, String.class));

		System.out.println(Ognls.getValue("name.name.name", Maps.asMap("name", null), String.class));

	}

}
