package cn.quickly.project.utility.map;

import java.util.Map;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;

public class BundlesTest {

	@Test
	public void testGet() {

		System.out.println(Bundles.get("log4j.properties"));

	}

	@Test
	public void testMatch() {

		Map<String, String> source = Maps.asMap("mock.name", "zhanglei");
		source.put("test.name", "test name");

		Printer.println(Bundles.match(Bundles.from(source), "*.name", true));

	}

}
