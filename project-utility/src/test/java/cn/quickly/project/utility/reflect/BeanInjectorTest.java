package cn.quickly.project.utility.reflect;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.quickly.project.utility.mock.MockDemo;
import cn.quickly.project.utility.reflect.BeanInjector;

public class BeanInjectorTest {

	@Test
	public void testInject() {

		Map<String, Object> instance = new HashMap<String, Object>();
		instance.put("name", 21312);
		instance.put("blocks", new String[] { "21312", "86787686" });

		Map<String, Object> inners = new HashMap<String, Object>();
		inners.put("name", "zhanglei");
		inners.put("blocks", new String[] { "21312", "86787686" });

		instance.put("inners", new Object[] { inners });

		BeanInjector injector = BeanInjector.getInjector(MockDemo.class);
		System.out.println(injector.getInstance(instance));
	}

}
