package cn.quickly.project.utility.type;

import java.sql.Clob;
import java.sql.SQLException;

public class StringConverter implements TypeConverter<String> {

	@Override
	public String convert(Class<?> type, Object value) {

		if (value == null) {

			return null;

		}

		if (value instanceof Clob) {

			Clob clob = (Clob) value;

			try {
				return clob.getSubString(1, (int) clob.length());
			} catch (SQLException e) {
			}

		} else {

			return value + "";

		}

		return null;

	}

}
