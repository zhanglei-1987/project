package cn.quickly.project.utility.reflect;

import java.util.Collection;
import java.util.Map;

public final class Reflect {

	private Reflect() {
		throw new UnsupportedOperationException();
	}

	public static boolean isCompatible(Class<?> sourceClass, Class<?> targetClass) {

		if (sourceClass.isAssignableFrom(targetClass)) {

			return true;

		} else {

			return Classes.getUnwrapperType(sourceClass) == Classes.getUnwrapperType(targetClass);

		}

	}

	public static boolean isBaseType(Class<?> type) {

		if (type == null || type.isPrimitive()) {
			return true;
		}

		if (type.isInterface() || type.isArray() || Classes.isSuperClass(Collection.class, type) || Classes.isSuperClass(Map.class, type)) {
			return false;
		}

		String className = type.getName();

		if (className.startsWith("java.") || className.startsWith("javax.")) {
			return true;
		}

		return false;

	}

	public static boolean isNumeric(Class<?> type) {

		if (Number.class.isAssignableFrom(type)) {
			return true;
		}

		if (type.isPrimitive() && type != char.class && type != boolean.class) {
			return true;
		}

		return false;

	}

	public static boolean isBoolean(Class<?> type) {

		if (type == boolean.class || type == Boolean.class) {
			return true;
		}

		return false;

	}

	public static boolean isExist(String className) {
		try {
			Class.forName(className);
		} catch (Throwable e) {
			return false;
		}
		return true;
	}

	public static boolean isArrayType(Object bean, Class<?> base) {
		return isArrayType(bean.getClass(), base);
	}

	public static boolean isArrayType(Class<?> type, Class<?> base) {
		if (base == null) {
			return type.isArray();
		} else
			return type.isArray() && Classes.isSuperClass(base, type.getComponentType());
	}

	public static boolean isCglibSupport() {
		return isExist("net.sf.cglib.proxy.Enhancer");
	}

	public static boolean isOgnlSupport() {
		return isExist("ognl.Ognl");
	}

}
