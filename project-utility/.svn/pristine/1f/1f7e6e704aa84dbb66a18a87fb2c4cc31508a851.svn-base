package cn.quickly.project.utility.reflect;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.function.BinarySupplier;
import cn.quickly.project.utility.lang.Quiet;

public class Caller {

	private Caller() {
		throw new UnsupportedOperationException();
	}

	public static int count(Class<?> targetClass) {

		int count = 0;

		Throwable t = new Throwable();

		for (StackTraceElement element : t.getStackTrace()) {

			try {
				if (Classes.isSuperClass(targetClass, Classes.getClass(element.getClassName()))) {
					count++;
				}
			} catch (Exception e) {
			}

		}

		return count;

	}

	public static <V> V search(Class<?> targetClass, BinarySupplier<Integer, StackTraceElement[], V> supplier) {

		return Quiet.tryCatch(() -> {

			Throwable t = new Throwable();

			StackTraceElement[] elements = Arrays.reverse(t.getStackTrace());

			for (int i = 0, length = elements.length; i < length; i++) {

				try {
					
					StackTraceElement element = elements[i];

					Class<?> subClass = Classes.getClass(element.getClassName());

					if (Classes.isSuperClass(targetClass, subClass)) {

						return supplier.get(i, elements);

					}

				} catch (Exception e) {
				}

			}

			return null;

		});

	}

	public static Class<?> target(Class<?> targetClass) {

		return search(targetClass, (i, elements) -> {

			return Classes.getClass(elements[i].getClassName());

		});

	}

	public static Class<?> before(Class<?> targetClass) {

		return search(targetClass, (i, elements) -> {

			return Classes.getClass(elements[i - 1].getClassName());

		});

	}

	public static Class<?> after(Class<?> targetClass) {

		return search(targetClass, (i, elements) -> {

			return Classes.getClass(elements[i + 1].getClassName());

		});

	}

}
