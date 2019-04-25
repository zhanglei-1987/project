package cn.quickly.project.utility.lang;

import java.util.Collection;
import java.util.Map;

public final class Assert {

	private Assert() {
		throw new UnsupportedOperationException();
	}

	public static void isEquals(Object expected, Object actual) {
		isEquals(expected, actual, "the actual object is " + actual + " not equals expected " + expected);
	}

	public static void isEquals(Object expected, Object actual, String message) {
		if ((expected == null) && (actual == null))
			return;
		if ((expected != null) && (expected.equals(actual)))
			return;
		if ((expected != null) && (expected == actual))
			return;
		throw Exceptions.argument(message);
	}

	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}

	public static void isTrue(boolean expression, String message) {
		if (!(expression))
			throw Exceptions.argument(message);
	}

	public static void isNull(Object value) {
		isNull(value, "[Assertion failed] - the object argument must be null");
	}

	public static void isNull(Object value, String message) {
		if (value != null)
			throw Exceptions.argument(message);
	}

	public static void isNotNull(Object value) {
		isNotNull(value, "[Assertion failed] - this argument is required; it must not be null");
	}

	public static void isNotNull(Object value, String message) {
		if (value == null)
			throw Exceptions.argument(message);
	}

	public static void isNotEmpty(String value) {
		isNotEmpty(value, "[Assertion failed] - this text must not be empty; it must contain at least one entry");
	}

	public static void isNotEmpty(String value, String message) {
		if (Strings.isEmpty(value)) {
			throw Exceptions.argument(message);
		}
	}

	public static void isNotEmpty(Map<?, ?> map) {
		isNotEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}

	public static void isNotEmpty(Map<?, ?> map, String message) {
		if (map.size() == 0) {
			throw Exceptions.argument(message);
		}
	}

	public static void isNotEmpty(Collection<?> collection) {
		isNotEmpty(collection, "[Assertion failed] - this collection must not be empty; it must contain at least one entry");
	}

	public static void isNotEmpty(Collection<?> collection, String message) {
		if (collection.size() == 0) {
			throw Exceptions.argument(message);
		}
	}

	public static void isNotEmpty(Object[] array) {
		isNotEmpty(array, "[Assertion failed] - this array must not be empty; it must contain at least one entry");
	}

	public static void isNotEmpty(Object[] array, String message) {
		if (array == null || array.length == 0) {
			throw Exceptions.argument(message);
		}
	}

	public static void isInstanceOf(Class<?> clazz, Object obj) {
		isInstanceOf(clazz, obj, "");
	}

	public static void isInstanceOf(Class<?> type, Object obj, String message) {
		isNotNull(type, "Type to check against must not be null");
		if (!(type.isInstance(obj)))
			throw Exceptions.argument(message + "Object of class [null] must be an instance of " + type);
	}

	public static void isAssignable(Class<?> superType, Class<?> subType) {
		isAssignable(superType, subType, "");
	}

	public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
		isNotNull(superType, "Type to check against must not be null");
		if ((subType == null) || (!(superType.isAssignableFrom(subType))))
			throw Exceptions.argument(message + subType + " is not assignable to " + superType);
	}

	public static void isInterface(Class<?> type, String message) {
		isNotNull(type, "Type to check against must not be null");
		if (!(type.isInterface()))
			throw Exceptions.argument(message + " Object of class [" + type + "] must be an interface");
	}

	public static void isInterface(Class<?> type) {
		isInterface(type, null);
	}

}
