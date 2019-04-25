package cn.quickly.project.utility.type;

import java.lang.reflect.Array;
import java.util.Collection;

import cn.quickly.project.utility.reflect.Compat;

public class ArrayConverter implements TypeConverter<Object> {

	@Override
	public Object convert(Class<?> type, Object value) {

		if (value instanceof Collection<?>) {
			value = ((Collection<?>) value).toArray();
		}

		if (!value.getClass().isArray()) {

			value = new Object[] { value };

		}

		Class<?> targetType = type.getComponentType();

		int length = Array.getLength(value);

		Object targetArray = Array.newInstance(targetType, length);

		for (int i = 0; i < length; i++) {

			Object source = Array.get(value, i);

			if (source != null) {

				Array.set(targetArray, i, Compat.get(source, targetType));

			}

		}

		return targetArray;

	}

}
