package cn.quickly.project.utility.lang;

import java.util.Date;

import org.junit.Test;

import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.mock.MockDemo;
import cn.quickly.project.utility.time.Clock;

public class BeansTest {

	@Test
	public void testCopy() {

		MockDemo demo = new MockDemo();
		demo.setName("Beans.copy");
		demo.setTransactionTime(new Date());

		System.out.println(Maps.getMap(demo));

		System.out.println(Beans.copy(demo, MockDemo.class));

		System.out.println(Beans.copy(Maps.asMap("transactionTime", new java.sql.Date(Clock.now())), MockDemo.class));

	}

}
