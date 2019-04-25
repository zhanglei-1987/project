package cn.quickly.project.utility.type;

import java.math.BigDecimal;

public class IntegerConverter extends NumberConverter<Integer> {

	@Override
	public Integer convert(Class<?> type, Object value) {

		BigDecimal decimal = get(type, value);

		if (decimal == null) {

			if (type.isPrimitive()) {

				return 0;

			}

			return null;

		}

		return decimal.intValue();

	}

}
