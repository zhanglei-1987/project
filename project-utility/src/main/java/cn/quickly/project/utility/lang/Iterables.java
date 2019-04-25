package cn.quickly.project.utility.lang;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.function.BinaryConsumer;
import cn.quickly.project.utility.function.BinarySupplier;
import cn.quickly.project.utility.function.TernaryConsumer;
import cn.quickly.project.utility.function.UnaryConsumer;
import cn.quickly.project.utility.function.UnarySupplier;

public class Iterables {

	public static <E> void forEach(Iterable<? extends E> elements, UnaryConsumer<Integer, ? super E> action) {

		Loops.forEach(elements, (i, element) -> {

			action.accept(i, element);

			return true;

		});

	}

	public static <E> void forEach(Enumeration<? extends E> elements, UnaryConsumer<Integer, ? super E> action) {

		Loops.forEach(elements, (i, element) -> {

			action.accept(i, element);

			return true;

		});

	}

	public static void forEach(Throwable e, UnaryConsumer<Integer, Throwable> action) {

		Loops.forEach(e, (i, element) -> {

			action.accept(i, element);

			return true;

		});

	}

	public static void forEach(File file, UnaryConsumer<Integer, String> action) throws IOException {
		forEach(file, "UTF-8", action);
	}

	public static void forEach(File file, String charset, UnaryConsumer<Integer, String> action) throws IOException {

		Loops.forEach(file, charset, (i, line) -> {

			action.accept(i, line);

			return true;

		});

	}

	public static <E> void forEach(E[] elements, UnaryConsumer<Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		forEach(Arrays.asList(elements), action);

	}

	public static <E> void forEach(E[][] elements, BinaryConsumer<Integer, Integer, ? super E> action) {

		Loops.forEach(elements, (x, y, value) -> {

			action.accept(x, y, value);

			return true;

		});

	}

	public static <E> void forEach(E[][][] elements, TernaryConsumer<Integer, Integer, Integer, ? super E> action) {

		Loops.forEach(elements, (x, y, z, value) -> {

			action.accept(x, y, z, value);

			return true;

		});

	}

	public static <I, K, V> void forEach(Map<K, V> map, BinaryConsumer<Integer, K, V> action) {

		Loops.forEach(map, (i, key, value) -> {

			action.accept(i, key, value);

			return true;

		});

	}

	public static void forEach(BufferedImage image, BinaryConsumer<Integer, Integer, Integer> action) {

		Loops.forEach(image, (x, y, rgb) -> {

			action.accept(x, y, rgb);

			return true;

		});

	}

	public static void forArray(Object elements, UnaryConsumer<Integer, Object> action) {

		Loops.forArray(elements, (i, value) -> {

			action.accept(i, value);

			return true;

		});

	}

	public static <E> void parallelEach(Iterable<? extends E> elements, int threads, UnaryConsumer<Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		ExecutorService executor = Executors.newFixedThreadPool(threads);

		final AtomicInteger index = new AtomicInteger(0);

		for (E element : elements) {

			executor.execute(() -> {

				try {

					action.accept(index.getAndIncrement(), element);

				} catch (Throwable e) {

					throw Exceptions.argument(e);

				}

			});

		}

		executor.shutdown();

	}

	public static <E> void forRange(E[] elements, int from, int to, UnaryConsumer<Integer, ? super E> action) {

		Loops.forRange(elements, from, to, (i, value) -> {

			action.accept(i, value);

			return true;

		});

	}

	public static <E> void forRange(E[][] elements, int fromX, int toX, int fromY, int toY, BinaryConsumer<Integer, Integer, ? super E> action) {

		Loops.forRange(elements, fromX, toX, fromY, toY, (x, y, value) -> {

			action.accept(x, y, value);

			return true;

		});

	}

	public static <E> void forRange(E[][][] elements, int fromX, int toX, int fromY, int toY, int fromZ, int toZ,
			TernaryConsumer<Integer, Integer, Integer, ? super E> action) {

		Loops.forRange(elements, fromX, toX, fromY, toY, fromZ, toZ, (x, y, z, value) -> {

			action.accept(x, y, z, value);

			return true;

		});

	}

	public static <E, R> List<R> flat(E[] elements, BinarySupplier<Integer, E, R> action) {
		return flat(Arrays.asList(elements), action);
	}

	public static <E, R> List<R> flat(Iterable<? extends E> elements, BinarySupplier<Integer, E, R> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		List<R> datas = new LinkedList<>();

		int index = 0;

		for (E element : elements) {

			try {

				datas.add(action.get(index++, element));

			} catch (Throwable e) {

				throw Exceptions.argument(e);

			}

		}

		return datas;

	}

	public static <E, R> List<R> flat(Enumeration<? extends E> elements, BinarySupplier<Integer, E, R> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		List<R> datas = new LinkedList<>();

		int index = 0;

		while (elements.hasMoreElements()) {

			try {

				datas.add(action.get(index++, elements.nextElement()));

			} catch (Throwable e) {

				throw Exceptions.argument(e);

			}

		}

		return datas;

	}

	public static <K, V> Map<K, V> map(Iterable<? extends K> elements, Class<V> valueType, UnarySupplier<K, V> fn) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(fn);

		Map<K, V> map = new LinkedHashMap<>();

		Iterables.forEach(elements, (i, k) -> {

			map.put(k, fn.get(k));

		});

		return map;

	}

	public static <K, V> Map<K, V> map(K[] elements, Class<V> valueType, UnarySupplier<K, V> fn) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(fn);

		return map(Arrays.asList(elements), valueType, fn);

	}

}
