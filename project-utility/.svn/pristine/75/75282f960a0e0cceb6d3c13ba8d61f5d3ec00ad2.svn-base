package cn.quickly.project.utility.type;

import java.math.BigDecimal;

public class LongConverter extends NumberConverter<Long> {

	@Override
	public Long convert(Class<?> type, Object value) {

		BigDecimal decimal = get(type, value);

		if (decimal == null) {

			if (type.isPrimitive()) {

				return 0l;

			}

			return null;

		}

		return decimal.longValue();

	}

}
