package cn.quickly.project.utility.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import cn.quickly.project.utility.concurrent.Locks;
import cn.quickly.project.utility.function.BinarySupplier;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class BeanInjector {

	private static volatile Hashtable<Class<?>, BeanInjector> injectorCache = new Hashtable<Class<?>, BeanInjector>();

	private Class<?> beanClass;

	private Map<String, PropertyDescriptor> descriptors;

	public BeanInjector(Class<?> beanClass) {

		this.beanClass = beanClass;

		try {

			BeanInfo bi = Introspector.getBeanInfo(beanClass);

			PropertyDescriptor[] descriptors = bi.getPropertyDescriptors();

			this.descriptors = new LinkedHashMap<String, PropertyDescriptor>(descriptors.length);

			for (PropertyDescriptor descriptor : descriptors) {

				if (!"class".equals(descriptor.getName())) {
					this.descriptors.put(descriptor.getName(), descriptor);
				}

			}

		} catch (IntrospectionException e) {
			throw Exceptions.argument(e);
		}
	}

	public static BeanInjector getInjector(Class<?> beanClass) {

		return Quiet.tryCatch(() -> Locks.dcl(injectorCache, beanClass, (c) -> new BeanInjector(beanClass)));

	}

	public Class<?> getBeanClass() {
		return beanClass;
	}

	public Collection<PropertyDescriptor> getDescriptors() {
		return descriptors.values();
	}

	public Set<String> getPropertyNames() {
		return descriptors.keySet();
	}

	public Map<String, Object> getProperties(Object bean) {

		Map<String, Object> attributes = new LinkedHashMap<String, Object>();

		if (beanClass.isInstance(bean)) {

			for (PropertyDescriptor descriptor : getDescriptors()) {

				Method method = descriptor.getReadMethod();

				if (method != null) {

					try {
						attributes.put(Methods.getPropertyName(method), Methods.getGetterValue(bean, method));
					} catch (Exception e) {
						throw Exceptions.argument(e);
					}

				}

			}

		}

		return attributes;
	}

	public Object getProperty(Object bean, String name) {

		if (beanClass.isInstance(bean)) {

			PropertyDescriptor descriptor = descriptors.get(name);

			if (descriptor != null) {

				Method method = descriptor.getReadMethod();

				if (method != null) {

					return Methods.getGetterValue(bean, method);

				}
			}

		}

		return null;
	}

	public void setProperties(Object bean, Map<String, Object> attributes) {

		setProperties(bean, (name, method) -> attributes.get(name));

	}

	public void setProperties(Object bean, BinarySupplier<String, Method, Object> supplier) {

		if (supplier != null && beanClass.isInstance(bean)) {

			for (PropertyDescriptor descriptor : getDescriptors()) {

				Method method = descriptor.getWriteMethod();

				if (method != null) {

					Quiet.tryCatch(() -> {

						String name = descriptor.getName();

						Object value = supplier.get(name, method);

						if (value == null || value.equals(supplier)) {
							return;
						}

						Methods.setSetterValue(bean, method, value);

					});

				}

			}

		}

	}

	public void setProperty(Object bean, String name, Object value) {

		if (beanClass.isInstance(bean)) {

			PropertyDescriptor descriptor = descriptors.get(name);

			if (descriptor != null) {

				Method method = descriptor.getWriteMethod();

				if (method != null) {

					Methods.setSetterValue(bean, method, value);

				}
			}
		}
	}

	public Object getInstance(Map<String, Object> attributes) {

		try {

			Object bean = beanClass.newInstance();

			setProperties(bean, attributes);

			return bean;

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public Object getInstance(BinarySupplier<String, Method, Object> supplier) {

		try {

			Object bean = beanClass.newInstance();

			setProperties(bean, supplier);

			return bean;

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static void clear() {
		injectorCache.clear();
	}
}
