package cn.quickly.project.utility.type;

import java.sql.Date;

public class SqlDateConverter extends DateTimeConverter<Date> {

	@Override
	public Date convert(Class<?> type, Object value) {

		Long time = getTime(value);

		if (time != null) {

			return new Date(time);

		}

		return null;
	}

}
