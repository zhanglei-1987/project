package cn.quickly.project.utility.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import cn.quickly.project.utility.collection.Collections;

public final class Annotations {

	private Annotations() {
		throw new UnsupportedOperationException();
	}

	public static <A extends Annotation> Set<A> getExtends(Annotation[] annotations, Class<A> annotationClass) {

		Set<A> annotationsCache = new LinkedHashSet<>();

		if (annotations != null) {

			for (Annotation annotation : annotations) {

				Class<?> annotationType = annotation.annotationType();

				if (!annotationType.getName().startsWith("java.lang.annotation")) {

					A extendAnnotation = annotationType.getDeclaredAnnotation(annotationClass);

					if (extendAnnotation != null) {

						annotationsCache.add(extendAnnotation);

					}

					annotationsCache.addAll(getExtends(annotationType.getDeclaredAnnotations(), annotationClass));

				}

			}

		}

		return annotationsCache;

	}

	public static <A extends Annotation> A getExtend(Annotation[] annotations, Class<A> annotationClass) {

		if (annotations != null && annotations.length > 0) {

			Set<Annotation> annotationsCache = new LinkedHashSet<>();

			for (Annotation annotation : annotations) {

				Class<?> annotationType = annotation.annotationType();

				if (!annotationType.getName().startsWith("java.lang.annotation")) {

					A extendAnnotation = annotationType.getDeclaredAnnotation(annotationClass);

					if (extendAnnotation != null) {

						return extendAnnotation;

					}

					annotationsCache.addAll(Arrays.asList(annotationType.getDeclaredAnnotations()));

				}

			}

			return getExtend(Collections.toArray(Annotation.class, annotationsCache), annotationClass);

		}

		return null;

	}

	public static <A extends Annotation> A getExtend(Field field, Class<A> annotationClass) {

		A annotation = field.getAnnotation(annotationClass);

		if (annotation == null) {

			annotation = getExtend(field.getDeclaredAnnotations(), annotationClass);

		}

		return annotation;
	}

	public static <A extends Annotation> A getExtend(Method method, Class<A> annotationClass) {

		A annotation = method.getAnnotation(annotationClass);

		if (annotation == null) {

			annotation = getExtend(method.getDeclaredAnnotations(), annotationClass);

		}

		return annotation;
	}

	public static <A extends Annotation> A getExtend(Class<?> type, Class<A> annotationClass) {

		A annotation = type.getAnnotation(annotationClass);

		if (annotation == null) {

			annotation = getExtend(type.getDeclaredAnnotations(), annotationClass);

		}

		return annotation;

	}

	public static <A extends Annotation> Class<?> getClass(A annotation) {

		return annotation.annotationType();

	}

	public static Map<String, Object> getMap(Object annotation) {

		Map<String, Object> data = new HashMap<String, Object>();

		if (annotation != null) {

			Class<?> annotationClass = getClass((Annotation) annotation);

			if (annotation != null && annotationClass.isAnnotation()) {

				AnnotationInjector injector = AnnotationInjector.getInjector(annotationClass);

				return injector.getAttributes(annotation);
			}

		}

		return data;

	}

	public static <A extends Annotation> A getDeclared(Method method, Class<A> annotationClass) {

		A annotation = method.getAnnotation(annotationClass);

		if (annotation == null) {

			Method superMethod = Methods.getSuperMethod(method);

			if (superMethod != null) {

				return superMethod.getAnnotation(annotationClass);

			}

		}

		return annotation;

	}

}
