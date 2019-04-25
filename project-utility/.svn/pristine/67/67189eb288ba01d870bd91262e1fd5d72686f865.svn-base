package cn.quickly.project.utility.type;

import java.math.BigDecimal;

public class ByteConverter extends NumberConverter<Byte> {

	@Override
	public Byte convert(Class<?> type, Object value) {

		BigDecimal decimal = get(type, value);

		if (decimal == null) {

			if (type.isPrimitive()) {

				return (byte) -1;

			}
			
			return null;

		}

		return decimal.byteValue();

	}

}
