package cn.quickly.project.utility.type;

import java.math.BigDecimal;

public class FloatConverter extends NumberConverter<Float> {

	@Override
	public Float convert(Class<?> type, Object value) {

		BigDecimal decimal = get(type, value);

		if (decimal == null) {

			if (type.isPrimitive()) {

				return (float) 0;

			}

			return null;

		}

		return decimal.floatValue();

	}

}
