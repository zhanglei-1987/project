package cn.quickly.project.utility.type;

import java.math.BigDecimal;

public class BigDecimalConverter extends NumberConverter<BigDecimal> {

	@Override
	public BigDecimal convert(Class<?> type, Object value) {

		return get(type, value);

	}

}
