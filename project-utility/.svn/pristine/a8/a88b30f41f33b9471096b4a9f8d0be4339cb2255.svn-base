package cn.quickly.project.utility.type;

import java.util.HashMap;
import java.util.Map;

import cn.quickly.project.utility.lang.Objects;
import cn.quickly.project.utility.map.Maps;

public class MapConverter implements TypeConverter<Map<Object, Object>> {

	@Override
	public Map<Object, Object> convert(Class<?> type, Object value) {

		Map<Object, Object> map = getEmptyMap(type);

		map.putAll(Maps.getMap(value));

		return map;

	}

	@SuppressWarnings("unchecked")
	protected Map<Object, Object> getEmptyMap(Class<?> type) {

		if (type.isInterface()) {

			return new HashMap<>();

		}

		return (Map<Object, Object>) Objects.getInstance(type);

	}

}
