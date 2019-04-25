package cn.quickly.project.utility.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Objects;
import cn.quickly.project.utility.reflect.Compat;

@SuppressWarnings("unchecked")
public final class Arrays {

	private Arrays() {
		throw new UnsupportedOperationException();
	}

	public static int indexOf(Object[] objects, Object object, int start, int end) {

		for (int j = start; j < end; j++) {

			if (object == objects[j]) {

				return j;

			} else if (object != null && object.equals(objects[j])) {

				return j;

			}

		}

		return -1;
	}

	public static int indexOf(Object[] objects, Object object) {

		return indexOf(objects, object, 0, objects.length);

	}

	public static <T> T[] reverse(T[] objects) {

		T[] values = clone(objects, objects.length);

		for (int len = objects.length - 1, i = len, end = -1; i > end; i--) {

			values[len - i] = objects[i];

		}

		return values;

	}

	public static <T> T[] insert(T[] array, int offset, T object) {

		int length = array.length;

		if (offset >= 0 && offset < length) {

			T[] na = clone(array, length + 1);

			System.arraycopy(array, 0, na, 0, offset);

			na[offset] = object;

			System.arraycopy(array, offset, na, offset + 1, length + 1);

			return na;

		} else if (offset == length) {

			return add(array, object);

		} else {
			throw new IndexOutOfBoundsException(offset + "");
		}

	}

	public static <T> T[] add(T[] array, T object) {

		int len = array.length;

		T[] na = clone(array, len + 1);

		System.arraycopy(array, 0, na, 0, len);

		na[len] = object;

		return na;

	}

	public static <T> T[] clone(T[] array, int length) {

		return (T[]) create(array.getClass().getComponentType(), length);

	}

	public static <T> T[] create(Class<T> targetClass, int length) {

		return (T[]) Array.newInstance(targetClass, length);

	}

	public static <T> T[] merge(T[]... arrays) {

		List<T> list = new ArrayList<T>();

		for (T[] array : arrays) {

			for (T t : array) {

				list.add(t);

			}

		}

		Class<T> targetClass = (Class<T>) arrays.getClass().getComponentType().getComponentType();

		return list.toArray(create(targetClass, list.size()));

	}

	public static <T> T[] as(T... array) {
		return array;
	}

	public static <T> Stack<T> asStack(T... array) {

		Stack<T> stack = new Stack<T>();

		if (array != null) {

			for (T t : array) {
				stack.add(t);
			}

		}

		return stack;

	}

	public static <T> Set<T> asSet(T... array) {

		Set<T> set = new LinkedHashSet<T>();

		if (array != null) {

			for (T t : array) {

				set.add(t);

			}

		}

		return set;
	}

	public static <T> List<T> asList(T... array) {

		List<T> list = new LinkedList<T>();

		if (array != null) {

			for (T t : array) {

				list.add(t);

			}

		}

		return list;

	}

	public static <T> T[] cast(Object array, Class<T> targetType) {

		int length = Array.getLength(array);

		T[] datas = create(targetType, length);

		for (int i = 0; i < length; i++) {
			datas[i] = (T) Compat.get(Array.get(array, i), targetType);
		}

		return datas;
	}

	public static String toString(Object array) {

		StringBuilder builder = new StringBuilder();

		if (array != null) {

			if (array.getClass().isArray()) {

				builder.append("[");

				for (int i = 0, end = Array.getLength(array) - 1; i <= end; i++) {

					builder.append(Array.get(array, i));

					if (i < end) {
						builder.append(",");
					}

				}

				builder.append("]");

			} else {

				builder.append(array);

			}

		}

		return builder.toString();

	}

	public static <T> Set<T> extract(Object[] objects, Class<T> keyType, String keyName) {

		Set<T> values = new HashSet<T>();

		for (Object object : objects) {

			T value = Objects.extract(object, keyType, keyName);

			if (value != null) {

				values.add(value);

			}

		}

		return values;
	}

	public static <T> T first(T[] array) {

		if (array == null || array.length == 0) {
			return null;
		}

		return array[0];

	}

	public static <T> T[] slice(T[] array, int start, int end) {

		Assert.isTrue(start < end, "start must less than end");

		Assert.isTrue((start <= 0 && end <= 0) || (start >= 0 && end >= 0), "start and end must be same symbol");

		if (start < 0) {
			start = array.length - 1 + start;
		}

		if (end <= 0) {
			end = array.length - 1 + end;
		}

		int length = end - start + 1;

		T[] newArray = clone(array, length);

		System.arraycopy(array, start, newArray, 0, length);

		return newArray;

	}

}
