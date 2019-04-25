package cn.quickly.project.utility.type;

import java.sql.Timestamp;

public class TimestampConverter extends DateTimeConverter<Timestamp> {

	@Override
	public Timestamp convert(Class<?> type, Object value) {

		Long time = getTime(value);

		if (time != null) {

			return new Timestamp(time);

		}

		return null;
	}

}
