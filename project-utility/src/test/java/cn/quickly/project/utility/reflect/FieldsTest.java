package cn.quickly.project.utility.reflect;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.mock.MockDemo;

public class FieldsTest {

	@Test
	public void testGetComponentTypes() throws Exception {

		Printer.println(Fields.getComponentClasses(MockDemo.class.getDeclaredField("mockList")));

		Printer.println(Fields.getComponentClasses(MockDemo.class.getDeclaredField("inners")));

	}

}
