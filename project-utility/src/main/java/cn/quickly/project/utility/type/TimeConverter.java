package cn.quickly.project.utility.type;

import java.sql.Time;

public class TimeConverter extends DateTimeConverter<Time> {

	@Override
	public Time convert(Class<?> type, Object value) {

		Long time = getTime(value);

		if (time != null) {

			return new Time(time);

		}

		return null;
	}

}
