package cn.quickly.project.utility.reflect;

import java.lang.annotation.Annotation;

public class AnnotationClassMatcher implements ClassMatcher {

	private Class<? extends Annotation> annotationClass;

	public AnnotationClassMatcher(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	public boolean isMatch(Class<?> type) {
		return Annotations.getExtend(type, annotationClass) != null;
	}

}
