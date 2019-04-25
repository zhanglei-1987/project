package cn.quickly.project.utility.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import cn.quickly.project.utility.reflect.Classes;
import cn.quickly.project.utility.reflect.Reflect;

public class TypeConverterFactory {

	private static final TypeConverterFactory FACTORY = new TypeConverterFactory();

	private Map<Class<?>, TypeConverter<?>> converters = new ConcurrentHashMap<>();

	private EnumConverter enumConverter = new EnumConverter();

	private ArrayConverter arrayConverter = new ArrayConverter();

	private BeanConverter beanConverter = new BeanConverter();

	private TypeConverterFactory() {

		converters.put(boolean.class, new BooleanConverter());
		converters.put(Boolean.class, converters.get(boolean.class));

		converters.put(java.util.Date.class, new UtilDateConverter());
		converters.put(java.sql.Date.class, new SqlDateConverter());
		converters.put(java.sql.Timestamp.class, new TimestampConverter());
		converters.put(java.sql.Time.class, new TimeConverter());

		converters.put(byte.class, new ByteConverter());
		converters.put(Byte.class, converters.get(byte.class));

		converters.put(short.class, new ShortConverter());
		converters.put(Short.class, converters.get(short.class));

		converters.put(int.class, new IntegerConverter());
		converters.put(Integer.class, converters.get(int.class));

		converters.put(long.class, new LongConverter());
		converters.put(Long.class, converters.get(long.class));

		converters.put(float.class, new FloatConverter());
		converters.put(Float.class, converters.get(float.class));

		converters.put(double.class, new DoubleConverter());
		converters.put(Double.class, converters.get(double.class));

		converters.put(BigDecimal.class, new BigDecimalConverter());
		converters.put(BigInteger.class, new BigIntegerConverter());

		converters.put(String.class, new StringConverter());

		converters.put(List.class, new ListConverter());
		converters.put(Set.class, new SetConverter());
		converters.put(Stack.class, new StackConverter());
		converters.put(Queue.class, new QueueConverter());

		converters.put(Map.class, new MapConverter());

		converters.put(URL.class, new URLConverter());

		converters.put(Class.class, new ClassConverter());

	}

	public static TypeConverterFactory getFactory() {
		return FACTORY;
	}

	@SuppressWarnings("unchecked")
	public <T> TypeConverter<T> geConverter(Class<T> type) {

		if (type.isEnum()) {

			return (TypeConverter<T>) enumConverter;

		} else if (type.isArray()) {

			return (TypeConverter<T>) arrayConverter;

		} else if (Classes.isSuperClass(Collection.class, type)) {

			if (Classes.isSuperClass(List.class, type)) {

				type = (Class<T>) List.class;

			} else if (Classes.isSuperClass(Set.class, type)) {

				type = (Class<T>) Set.class;

			} else if (Classes.isSuperClass(Stack.class, type)) {

				type = (Class<T>) Stack.class;

			} else if (Classes.isSuperClass(Queue.class, type)) {

				type = (Class<T>) Queue.class;

			}

		} else if (Classes.isSuperClass(Map.class, type)) {

			type = (Class<T>) Map.class;

		}

		TypeConverter<T> converter = (TypeConverter<T>) converters.get(type);

		if (converter == null && !Reflect.isBaseType(type)) {

			return (TypeConverter<T>) beanConverter;

		}

		return converter;
	}

}
