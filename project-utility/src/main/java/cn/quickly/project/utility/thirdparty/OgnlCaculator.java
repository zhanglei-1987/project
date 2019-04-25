package cn.quickly.project.utility.thirdparty;

import java.util.Map;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Iterables;
import ognl.Ognl;
import ognl.OgnlContext;

public class OgnlCaculator {

	protected Store store;

	protected Cache cache;

	public void setStore(Store store) {
		this.store = store;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Object execute(String script, Map<String, Object> arguments) {

		return execute(Arrays.as(script), arguments)[0];

	}

	public Object[] execute(String[] scripts, Map<String, Object> arguments) {

		Object[] results = new Object[scripts.length];

		OgnlContext ognlContext = createOgnlContext(arguments);

		Iterables.forEach(scripts, (i, script) -> {

			results[i] = Ognl.getValue(script, ognlContext, ognlContext.getRoot());

		});

		return results;

	}

	protected OgnlContext createOgnlContext(Map<String, Object> arguments) {

		OgnlContext ognlContext = Ognls.context(arguments);

		if (store != null) {

			ognlContext.put("store", store);

		}

		if (cache != null) {

			ognlContext.put("cache", cache);

		}

		return ognlContext;

	}

	public static interface Store {

		Object get(String key);

		Object set(String key, Object value);

		void plus(String key, Object value);

		void subtract(String key, Object value);

		void multiply(String key, Object value);

		void divide(String key, Object value);

	}

	public static interface Cache {

		Object get(String key);

		Object set(String key, Object value, int ttl);

		void remote(String key);

		void clear();

	}

}
