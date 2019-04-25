package cn.quickly.project.utility.reflect;

public class GenericClassMatcher implements ClassMatcher {

	private Class<?> targetClass;

	public GenericClassMatcher(Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	public boolean isMatch(Class<?> type) {
		return Classes.isSuperClass(targetClass, type);
	}

}
