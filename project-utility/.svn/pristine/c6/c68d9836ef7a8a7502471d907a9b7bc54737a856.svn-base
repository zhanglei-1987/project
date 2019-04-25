package cn.quickly.project.utility.type;

import org.junit.Test;

import cn.quickly.project.utility.mock.MockEnum;

public class TypeConverterFactoryTest {

	TypeConverterFactory fatory = TypeConverterFactory.getFactory();

	protected <T> T cast(Class<T> type, Object value) {

		TypeConverter<T> converter = fatory.geConverter(type);

		return converter.convert(type, value);

	}

	@Test
	public void testEnum() {

		System.out.println(cast(MockEnum.class, "A"));

	}

	@Test
	public void testArray() {

		System.out.println(cast(MockEnum[].class, "A")[0]);

		System.out.println(cast(int[].class, "0")[0]);

	}

	@Test
	public void testBoolean() {

		System.out.println(cast(boolean.class, null));
		System.out.println(cast(Boolean.class, null));
		System.out.println(cast(Boolean.class, "false"));
		System.out.println(cast(Boolean.class, "true"));
		System.out.println(cast(Boolean.class, 0));
		System.out.println(cast(Boolean.class, 1));
		System.out.println(cast(Boolean.class, "off"));
		System.out.println(cast(Boolean.class, "on"));
		System.out.println(cast(Boolean.class, "no"));
		System.out.println(cast(Boolean.class, "yes"));

	}

}
