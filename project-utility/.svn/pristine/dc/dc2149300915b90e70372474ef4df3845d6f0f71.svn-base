package cn.quickly.project.utility.type;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerConverter extends NumberConverter<BigInteger> {

	@Override
	public BigInteger convert(Class<?> type, Object value) {

		BigDecimal decimal = get(type, value);

		if (decimal == null) {

			return null;

		}

		return decimal.toBigInteger();

	}

}
