package cn.quickly.project.utility.type;

import java.math.BigDecimal;

public class DoubleConverter extends NumberConverter<Double> {

	@Override
	public Double convert(Class<?> type, Object value) {

		BigDecimal decimal = get(type, value);

		if (decimal == null) {

			if (type.isPrimitive()) {

				return 0d;

			}

			return null;

		}

		return decimal.doubleValue();

	}

}
