package cn.quickly.project.utility.type;

import java.math.BigDecimal;

import cn.quickly.project.utility.lang.Strings;

public abstract class NumberConverter<T extends Number> implements TypeConverter<T> {

	protected BigDecimal get(Class<?> type, Object value) {

		if (value == null) {
			
			return null;
		
		}

		String text = Strings.valueOf(value);

		if (value instanceof Number || value.getClass().isPrimitive()) {

			return new BigDecimal(text);

		}

		if (!Strings.isNumber(text)) {

			return null;

		}

		return new BigDecimal(text);
	}

}
