package cn.quickly.project.utility.type;

import java.math.BigDecimal;

public class ShortConverter extends NumberConverter<Short> {

	@Override
	public Short convert(Class<?> type, Object value) {

		BigDecimal decimal = get(type, value);

		if (decimal == null) {

			if (type.isPrimitive()) {

				return (short) 0;

			}

			return null;

		}

		return decimal.shortValue();
	}

}
