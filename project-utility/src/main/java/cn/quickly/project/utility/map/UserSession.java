package cn.quickly.project.utility.map;

import java.util.HashMap;
import java.util.Map;

import cn.quickly.project.utility.lang.SupplierThreadLocal;

public final class UserSession {

	private static ThreadLocal<Map<String, Object>> attributes = new SupplierThreadLocal<>(() -> new HashMap<>());

	private UserSession() {
		throw new UnsupportedOperationException();
	}

	public static Map<String, Object> getAttributes() {
		return attributes.get();
	}

	public static Object getAttribute(String name) {
		return getAttributes().get(name);
	}

	public static void setAttribute(String name, Object value) {
		getAttributes().put(name, value);
	}

	public static Object removeAttribute(String name) {
		return getAttributes().remove(name);
	}

	public static synchronized void clear() {
		attributes.remove();
	}

}
