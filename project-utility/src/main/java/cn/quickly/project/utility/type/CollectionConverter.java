package cn.quickly.project.utility.type;

import java.util.Collection;
import java.util.Enumeration;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.lang.Objects;
import cn.quickly.project.utility.reflect.Classes;
import cn.quickly.project.utility.reflect.Compat;

public abstract class CollectionConverter<T> implements TypeConverter<T> {

	protected void add(Collection<Object> collection, Object value, Class<?> type) {

		if (value == null) {
			return;
		}

		Class<?> compomentClass = Objects.empty((Class<?>) Arrays.first(Classes.getGenericTypes(type)), Object.class);

		if (value.getClass().isArray()) {

			Iterables.forEach((Object[]) value, (i, item) -> {

				collection.add(Compat.get(item, compomentClass));

			});

		} else if (value instanceof Collection<?>) {

			Iterables.forEach((Collection<?>) value, (i, item) -> {

				collection.add(Compat.get(item, compomentClass));

			});

		} else if (value instanceof Enumeration<?>) {

			Iterables.forEach((Enumeration<?>) value, (i, item) -> {

				collection.add(Compat.get(item, compomentClass));

			});

		} else {

			collection.add(Compat.get(value, compomentClass));

		}

	}

}
