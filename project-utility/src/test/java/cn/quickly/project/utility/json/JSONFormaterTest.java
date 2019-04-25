package cn.quickly.project.utility.json;

import org.junit.Test;

public class JSONFormaterTest {

	@Test
	public void testCase1() {

		String json = "{\"name\": \"case1\", \"style\":\"\\\"1.[line].line\"}";

		System.out.println(JSONFormater.format(json));

	}

}
