package cn.quickly.project.utility.thirdparty;

import org.junit.Test;

import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.mock.MockDemo;

public class CglibTest {

	@Test
	public void testFill() {

		System.out.println(Cglib.fill(MockDemo.class, Maps.asMap("name", "test")));

	}

	@Test
	public void testFillObject() {

		MockDemo demo = new MockDemo();

		Cglib.fillObject(demo, Maps.asMap("name", "test"));

		System.out.println(demo);

	}

}
