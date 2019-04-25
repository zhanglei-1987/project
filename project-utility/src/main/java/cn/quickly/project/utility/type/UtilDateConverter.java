package cn.quickly.project.utility.type;

import java.util.Date;

public class UtilDateConverter extends DateTimeConverter<Date> {

	@Override
	public Date convert(Class<?> type, Object value) {

		Long time = getTime(value);

		if (time != null) {

			return new Date(time);

		}

		return null;

	}

}
