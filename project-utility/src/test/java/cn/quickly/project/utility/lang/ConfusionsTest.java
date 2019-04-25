package cn.quickly.project.utility.lang;

import org.junit.Test;

import cn.quickly.project.utility.lang.Confusions;

public class ConfusionsTest {

	@Test
	public void testHide() {

		System.out.println(Confusions.hide("123456789", 5, 2, '*'));
		System.out.println(Confusions.hide("11111", 5, 2, '*'));
		System.out.println(Confusions.hide("2", 1, 2, '*'));
		System.out.println(Confusions.hide("1234567892321321", 5, 2, '*'));

	}

}
