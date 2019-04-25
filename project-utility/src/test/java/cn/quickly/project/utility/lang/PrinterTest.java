package cn.quickly.project.utility.lang;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;

public class PrinterTest {

	@Test
	public void testPrintln() {

		Map<String, Object> top = new HashMap<String, Object>();
		top.put("level0", "0");

		Map<String, Object> level1Map = new HashMap<String, Object>();
		level1Map.put("level1", "1");

		top.put("level1Map", level1Map);

		Printer.println(top);

	}

	@Test
	public void testCompete() {

		Printer.compete(new int[] { 1, 10, 100 }, (i) -> {

			Quiet.await(50);

		}, (i) -> {

			Quiet.await(10);

		}, (i) -> {

			Quiet.await(5);

		});

	}
}
