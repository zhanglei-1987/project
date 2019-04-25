package cn.quickly.project.utility.json;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import cn.quickly.project.utility.annotation.Ignore;
import cn.quickly.project.utility.lang.CodeEnum;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.lang.Objects;
import cn.quickly.project.utility.reflect.FieldInjector;
import cn.quickly.project.utility.reflect.Fields;

public final class JSONSerializer {

	static void stringify_number(StringBuilder builder, Number num) {
		builder.append(num);
	}

	static void stringify_text(StringBuilder builder, String text) {
		builder.append(JSON.DOUBLE_QUOTATION_MARK);
		builder.append(text.replace(JSON.DOUBLE_QUOTATION_MARK + "", "\\" + JSON.DOUBLE_QUOTATION_MARK));
		builder.append(JSON.DOUBLE_QUOTATION_MARK);
	}

	static void stringify_date(StringBuilder builder, Date date) {
		builder.append(date.getTime());
	}

	static void stringify_clob(StringBuilder builder, Clob clob) {
		try {
			stringify_text(builder, clob.getSubString(1L, (int) clob.length()));
		} catch (SQLException e) {
			throw Exceptions.argument(e);
		}
	}

	static void stringify_map(StringBuilder builder, Map<?, ?> map) {

		builder.append(JSON.OBJECT_START_SYMBOL);

		int end = map.size() - 1;

		Iterables.forEach(map, (index, key, value) -> {

			if (key != null) {

				stringify_object(builder, key);

				builder.append(JSON.KEY_SYMBOL);

				stringify_object(builder, value);

				if (index < end)
					builder.append(JSON.ELEMENT_SPLIT_SYMBOL);

			}

		});

		builder.append(JSON.OBJECT_END_SYMBOL);
	}

	static void stringify_iterator(StringBuilder builder, Iterator<?> it) {

		builder.append(JSON.ARRAY_START_SYMBOL);

		for (; it.hasNext();) {

			stringify_object(builder, it.next());

			if (it.hasNext())
				builder.append(JSON.ELEMENT_SPLIT_SYMBOL);

		}

		builder.append(JSON.ARRAY_END_SYMBOL);
	}

	static void stringify_enumeration(StringBuilder builder, Enumeration<?> eum) {
		builder.append(JSON.ARRAY_START_SYMBOL);
		for (; eum.hasMoreElements();) {
			stringify_object(builder, eum.nextElement());
			if (eum.hasMoreElements())
				builder.append(JSON.ELEMENT_SPLIT_SYMBOL);
		}
		builder.append(JSON.ARRAY_END_SYMBOL);
	}

	static void stringify_array(StringBuilder builder, Object object) {

		builder.append(JSON.ARRAY_START_SYMBOL);

		int end = Array.getLength(object) - 1;

		Iterables.forArray(object, (i, o) -> {

			stringify_object(builder, o);

			if (i < end)
				builder.append(JSON.ELEMENT_SPLIT_SYMBOL);

		});

		builder.append(JSON.ARRAY_END_SYMBOL);

	}

	static void stringify_bean(StringBuilder builder, Object object) {

		builder.append(JSON.OBJECT_START_SYMBOL);

		FieldInjector injector = FieldInjector.getInjector(object.getClass());

		Collection<Field> fields = injector.getFields();

		int end = fields.size() - 1;

		Iterables.forEach(fields, (index, field) -> {

			try {

				if (!Modifier.isTransient(field.getModifiers()) && field.getDeclaredAnnotation(Ignore.class) == null) {

					stringify_object(builder, Fields.getName(field));

					builder.append(JSON.KEY_SYMBOL);

					stringify_object(builder, Fields.getValue(object, field));

					if (index < end)
						builder.append(JSON.ELEMENT_SPLIT_SYMBOL);

				}

			} catch (Exception e) {
				throw Exceptions.argument(e);
			}

		});

		builder.append(JSON.OBJECT_END_SYMBOL);

	}

	static void stringify_object(StringBuilder builder, Object object) {

		if (Objects.isBasic(object)) {

			if (object instanceof String) {

				stringify_text(builder, (String) object);

			} else if (object instanceof Date) {

				stringify_date(builder, (Date) object);

			} else if (object instanceof Clob) {

				stringify_clob(builder, (Clob) object);

			} else {
				builder.append(object);
			}

		} else if (object instanceof Map<?, ?>) {

			stringify_map(builder, (Map<?, ?>) object);

		} else if (object instanceof Iterator<?>) {

			stringify_iterator(builder, (Iterator<?>) object);

		} else if (object instanceof Iterable<?>) {

			stringify_iterator(builder, ((Iterable<?>) object).iterator());

		} else if (object.getClass().isArray()) {

			stringify_array(builder, object);

		} else if (object instanceof Enumeration<?>) {

			stringify_enumeration(builder, (Enumeration<?>) object);

		} else if (object instanceof CodeEnum) {

			stringify_text(builder, ((CodeEnum) object).code());

		} else if (object instanceof Enum<?>) {

			stringify_text(builder, ((Enum<?>) object).name());

		} else {

			stringify_bean(builder, object);

		}

	}

	public static String stringify(Object object) {
		StringBuilder builder = new StringBuilder();
		stringify_object(builder, object);
		return builder.toString();
	}
}
