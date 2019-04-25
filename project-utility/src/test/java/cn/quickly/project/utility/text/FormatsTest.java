package cn.quickly.project.utility.text;

import java.util.Date;

import org.junit.Test;

public class FormatsTest {

	@Test
	public void testDate() {

		System.out.println(Formats.format(new Date(), "yyyy-MM-dd"));

	}

}
