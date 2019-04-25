package cn.quickly.project.utility.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.quickly.project.utility.concurrent.Locks;
import cn.quickly.project.utility.function.BinarySupplier;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class FieldInjector {

	private static volatile Hashtable<Class<?>, FieldInjector> injectorCache = new Hashtable<Class<?>, FieldInjector>();

	private Class<?> beanClass;

	private Map<String, Field> fields;

	public FieldInjector(Class<?> beanClass) {

		this.beanClass = beanClass;

		List<Field> fields = Fields.getDeclaredFields(beanClass);

		this.fields = new LinkedHashMap<String, Field>(fields.size());

		for (Field field : fields) {

			int modifiers = field.getModifiers();

			if (!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers)) {

				field.setAccessible(true);

				this.fields.put(field.getName(), field);
			}

		}

	}

	public static FieldInjector getInjector(Class<?> beanClass) {

		return Quiet.tryCatch(() -> Locks.dcl(injectorCache, beanClass, (c) -> new FieldInjector(beanClass)));

	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public Collection<Field> getFields() {
		return this.fields.values();
	}

	public Field getField(String name) {
		return this.fields.get(name);
	}

	public Set<String> getFieldNames() {
		return this.fields.keySet();
	}

	public Map<String, Object> getAttributes(Object bean) {

		Map<String, Object> attributes = new LinkedHashMap<String, Object>();

		if (beanClass.isInstance(bean)) {

			for (Field field : getFields()) {

				try {
					attributes.put(field.getName(), Fields.getValue(bean, field));
				} catch (Exception e) {
					throw Exceptions.argument(e);
				}

			}

		}

		return attributes;
	}

	public Object getAttribute(Object bean, String name) {

		if (beanClass.isInstance(bean)) {

			Field field = this.fields.get(name);

			if (field != null) {

				return Fields.getValue(bean, field);

			}

		}

		return null;
	}

	public void setAttributes(Object bean, Map<String, Object> attributes) {

		setAttributes(bean, (name, field) -> attributes.get(name));

	}

	public void setAttributes(Object bean, BinarySupplier<String, Field, Object> supplier) {

		if (supplier != null && beanClass.isInstance(bean)) {

			for (Field field : getFields()) {

				Quiet.tryCatch(() -> {

					String name = field.getName();

					Object value = supplier.get(name, field);

					if (value == null || value.equals(field.get(bean))) {
						return;
					}

					Fields.setValue(bean, field, value);

				});

			}

		}

	}

	public void setAttribute(Object bean, String name, Object value) {

		if (beanClass.isInstance(bean)) {

			Field field = this.fields.get(name);

			if (field != null) {

				Fields.setValue(bean, field, value);

			}

		}

	}

	public Object getInstance(Map<String, Object> attributes) {

		try {

			Object bean = beanClass.newInstance();

			setAttributes(bean, attributes);

			return bean;

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public Object getInstance(BinarySupplier<String, Field, Object> supplier) {

		try {

			Object bean = beanClass.newInstance();

			setAttributes(bean, supplier);

			return bean;

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static void clear() {
		injectorCache.clear();
	}
}
