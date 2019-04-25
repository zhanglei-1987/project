package cn.quickly.project.utility.type;

import java.util.HashSet;
import java.util.Set;

import cn.quickly.project.utility.lang.Objects;

public class SetConverter extends CollectionConverter<Set<Object>> {

	@Override
	public Set<Object> convert(Class<?> type, Object value) {

		Set<Object> set = getEmptySet(type);

		add(set, value, type);

		return set;

	}

	@SuppressWarnings("unchecked")
	protected Set<Object> getEmptySet(Class<?> type) {

		if (type.isInterface()) {
			return new HashSet<>();
		}

		return (Set<Object>) Objects.getInstance(type);

	}

}
