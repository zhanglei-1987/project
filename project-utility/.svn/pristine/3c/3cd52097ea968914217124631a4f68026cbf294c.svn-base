package cn.quickly.project.utility.type;

import java.util.ArrayList;
import java.util.List;

import cn.quickly.project.utility.lang.Objects;

public class ListConverter extends CollectionConverter<List<Object>> {

	@Override
	public List<Object> convert(Class<?> type, Object value) {

		List<Object> list = getEmptyList(type);

		add(list, value, type);

		return list;

	}

	@SuppressWarnings("unchecked")
	protected List<Object> getEmptyList(Class<?> type) {

		if (type.isInterface()) {
			return new ArrayList<>();
		}

		return (List<Object>) Objects.getInstance(type);

	}

}
