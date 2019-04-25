package cn.quickly.project.utility.type;

import cn.quickly.project.utility.lang.Strings;

public class BooleanConverter implements TypeConverter<Boolean> {

	@Override
	public Boolean convert(Class<?> type, Object value) {

		if (value != null) {
			
			String text = Strings.valueOf(value);

			if (Strings.equalsIgnoreCase(text, "1", "true", "on", "yes")) {

				return Boolean.TRUE;

			} else if (Strings.equalsIgnoreCase(text, "0", "false", "off", "no")) {

				return Boolean.FALSE;

			}

		}

		if (boolean.class == type) {

			return Boolean.FALSE;

		}

		return null;
	}

}
