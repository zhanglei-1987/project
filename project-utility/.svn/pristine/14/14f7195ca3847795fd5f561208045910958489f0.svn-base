package cn.quickly.project.utility.type;

import java.util.Map;

import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.reflect.BeanInjector;

public class BeanConverter implements TypeConverter<Object> {

	@Override
	public Object convert(Class<?> type, Object value) {

		Map<String, Object> data = Maps.getMap(value);

		if (data.isEmpty()) {

			return null;

		}

		BeanInjector injector = BeanInjector.getInjector(type);

		return injector.getInstance(data);

	}

}
