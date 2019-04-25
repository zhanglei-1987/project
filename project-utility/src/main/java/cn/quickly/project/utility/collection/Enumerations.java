package cn.quickly.project.utility.collection;

import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class Enumerations {

	private Enumerations() {
		throw new UnsupportedOperationException();
	}

	public static <T> Set<T> asSet(Enumeration<T> e) {

		Set<T> set = new LinkedHashSet<T>();

		while (e.hasMoreElements()) {
			set.add(e.nextElement());
		}

		return set;

	}

	public static <T> List<T> asList(Enumeration<T> e) {

		List<T> list = new LinkedList<T>();

		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}

		return list;

	}

}
