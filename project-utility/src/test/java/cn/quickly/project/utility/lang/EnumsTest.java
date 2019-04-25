package cn.quickly.project.utility.lang;

import org.junit.Test;

import cn.quickly.project.utility.mock.MockEnum;

public class EnumsTest {

	@Test
	public void testValueOf() {
		Printer.println(Enums.valueOf(MockEnum.class, "C"));
	}

	@Test
	public void testMapping() {
		Printer.println(Enums.mapping(MockEnum.class, false));
		Printer.println(Enums.mapping(MockEnum.class, true));
	}

}
