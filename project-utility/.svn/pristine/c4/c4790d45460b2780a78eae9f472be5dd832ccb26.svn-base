package cn.quickly.project.utility.thirdparty;

import java.util.Map;

import org.junit.Test;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.map.Maps;

public class OgnlCaculatorTest {

	@Test
	public void testExecute() {

		OgnlCaculator caculator = new OgnlCaculator();

		Map<String, Object> arguments = Maps.asMap("name", "hello word");

		System.out.println(caculator.execute("name", arguments));

	}

	@Test
	public void testExecutes() {

		OgnlCaculator caculator = new OgnlCaculator();
		caculator.setCache(new OgnlCaculator.Cache() {

			@Override
			public Object set(String key, Object value, int ttl) {
				return null;
			}

			@Override
			public void remote(String key) {

			}

			@Override
			public Object get(String key) {
				return null;
			}

			@Override
			public void clear() {

				System.out.println("fuck");

			}

		});

		Map<String, Object> arguments = Maps.asMap("name", "hello word");

		System.out.println(caculator.execute(Arrays.as("name", "#cache.clear()"), arguments));

	}

}
