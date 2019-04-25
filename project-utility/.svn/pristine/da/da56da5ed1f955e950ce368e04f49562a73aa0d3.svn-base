package cn.quickly.project.utility.collection;

import java.util.ArrayList;
import java.util.List;

public final class Lists {

	private Lists() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * 将一个List按照pagesize分割成多个List
	 * 
	 * @param list
	 * @param pageSize
	 * @return
	 */
	public static <E> List<List<E>> page(List<E> list, int pageSize) {

		List<List<E>> lists = new ArrayList<List<E>>();

		List<E> page = new ArrayList<E>();

		for (int i = 0, size = list.size(); i < size; i++) {

			page.add(list.get(i));

			if (i % pageSize == 0) {

				lists.add(page);

				page = new ArrayList<E>();

			}

		}

		if (!page.isEmpty()) {
			lists.add(page);
		}

		return lists;
	}

	public static <E> List<E> clone(List<E> list) {
		return list.subList(0, list.size());
	}
}
