package cn.quickly.project.utility.type;

import cn.quickly.project.utility.lang.Enums;

public class EnumConverter implements TypeConverter<Enum<?>> {

	@Override
	public Enum<?> convert(Class<?> type, Object value) {

		if (value == null) {
			return null;
		}

		if (value instanceof Enum<?>) {

			return (Enum<?>) Enums.valueOf(type, ((Enum<?>) value).name());

		} else {

			return (Enum<?>) Enums.valueOf(type, value.toString());

		}

	}

}
