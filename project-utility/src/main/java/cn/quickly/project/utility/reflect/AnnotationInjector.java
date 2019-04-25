package cn.quickly.project.utility.reflect;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class AnnotationInjector {

	private static Hashtable<Class<?>, AnnotationInjector> injectorCache = new Hashtable<Class<?>, AnnotationInjector>();

	private Class<?> annotationClass;

	private Map<String, Method> methods;

	public AnnotationInjector(Class<?> annotationClass) {

		this.annotationClass = annotationClass;

		List<Method> originMethods = Methods.getDeclaredMethods(annotationClass);

		this.methods = new LinkedHashMap<String, Method>(originMethods.size());

		for (Method method : originMethods) {
			this.methods.put(method.getName(), method);
		}

	}

	public static AnnotationInjector getInjector(Class<?> annotationClass) {
		AnnotationInjector injector = injectorCache.get(annotationClass);
		if (injector == null) {
			synchronized (AnnotationInjector.class) {
				injector = injectorCache.get(annotationClass);
				if (injector == null) {
					injector = new AnnotationInjector(annotationClass);
					injectorCache.put(annotationClass, injector);
				}
			}
		}
		return injector;
	}

	public Class<?> getAnnotationClass() {
		return annotationClass;
	}

	public Collection<Method> getMethods() {
		return this.methods.values();
	}

	public Set<String> getMethodNames() {
		return this.methods.keySet();
	}

	public Map<String, Object> getAttributes(Object bean) {
		Map<String, Object> attributes = new LinkedHashMap<String, Object>(methods.size());
		if (annotationClass.isInstance(bean)) {
			for (Method method : getMethods()) {
				try {
					attributes.put(method.getName(), method.invoke(bean));
				} catch (Exception e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
		return attributes;
	}

	public Object getAttribute(Object bean, String name) {
		if (annotationClass.isInstance(bean)) {
			Method method = this.methods.get(name);
			if (method != null) {
				try {
					return method.invoke(bean);
				} catch (Exception e) {
					throw new IllegalArgumentException(e);
				}
			}
		}
		return null;
	}

	public static void clear() {
		injectorCache.clear();
	}
}
