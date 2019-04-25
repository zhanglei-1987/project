package cn.quickly.project.utility.type;

import cn.quickly.project.utility.reflect.Compat;

public abstract class DateTimeConverter<T> implements TypeConverter<T> {

	protected Long getTime(Object value) {

		Long time = null;

		if (value instanceof java.util.Date) {

			time = ((java.util.Date) value).getTime();

		} else {

			time = Compat.cast(value, Long.class);

		}

		return time;

	}

}
