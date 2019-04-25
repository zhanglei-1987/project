package cn.quickly.project.utility.lang;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.function.InterruptBinaryConsumer;
import cn.quickly.project.utility.function.InterruptTernaryConsumer;
import cn.quickly.project.utility.function.InterruptUnaryConsumer;
import cn.quickly.project.utility.function.UnarySupplier;
import cn.quickly.project.utility.io.Streams;

public final class Loops {

	public static <T> void loop(int from, int to, Consumer<Integer> action) {

		Assert.isTrue(from <= to, "the to argument must bigger than and equals to argument from.");

		for (int i = from; i <= to; i++) {

			action.accept(i);

		}

	}

	public static <T> List<T> repeat(int from, int to, UnarySupplier<Integer, T> action) {

		Assert.isTrue(from <= to, "the to argument must bigger than and equals to argument from.");

		List<T> datas = new LinkedList<>();

		for (int i = from; i <= to; i++) {

			try {

				datas.add(action.get(i));

			} catch (Throwable e) {

				throw Exceptions.argument(e);

			}

		}

		return datas;

	}

	public static <E> void forEach(Iterable<? extends E> elements, InterruptUnaryConsumer<Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		int index = 0;

		for (E element : elements) {

			try {

				if (!action.accept(index++, element)) {
					break;
				}

			} catch (Throwable e) {

				throw Exceptions.argument(e);

			}

		}

	}

	public static <E> void forEach(Enumeration<? extends E> elements, InterruptUnaryConsumer<Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		int index = 0;

		while (elements.hasMoreElements()) {

			try {

				if (!action.accept(index++, elements.nextElement())) {
					break;
				}

			} catch (Throwable e) {

				throw Exceptions.argument(e);

			}

		}

	}

	public static void forEach(Throwable e, InterruptUnaryConsumer<Integer, Throwable> action) {

		Objects.requireNonNull(e);

		Objects.requireNonNull(action);

		int index = 0;

		do {

			try {

				if (!action.accept(index++, e)) {
					break;
				}

				e = e.getCause();

			} catch (Throwable ex) {

				throw Exceptions.argument(ex);

			}

		} while (e != null);

	}

	public static void forEach(File file, InterruptUnaryConsumer<Integer, String> action) throws IOException {
		forEach(file, "UTF-8", action);
	}

	public static void forEach(File file, String charset, InterruptUnaryConsumer<Integer, String> action) throws IOException {

		Objects.requireNonNull(file);

		Objects.requireNonNull(action);

		RandomAccessFile accessFile = new RandomAccessFile(file, "r");

		try {

			for (int index = 0;;) {

				String line = Streams.getLine(accessFile, charset);

				if (Strings.isEmpty(line)) {
					return;
				}

				try {

					if (!action.accept(index++, line)) {
						break;
					}

				} catch (Throwable e) {

					throw Exceptions.argument(e);

				}

			}

		} finally {

			accessFile.close();
		}
	}

	public static <E> void forEach(E[] elements, InterruptUnaryConsumer<Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		forEach(Arrays.asList(elements), action);

	}

	public static <E> void forEach(E[][] elements, InterruptBinaryConsumer<Integer, Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		int x = 0, y = 0;

		for (E[] array : elements) {

			for (E value : array) {

				try {

					if (!action.accept(x, y++, value)) {
						break;
					}

				} catch (Throwable e) {

					throw Exceptions.argument(e);

				}

			}

			x++;
			y = 0;
		}

	}

	public static <E> void forEach(E[][][] elements, InterruptTernaryConsumer<Integer, Integer, Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		int x = 0, y = 0, z = 0;

		for (E[][] xs : elements) {

			for (E[] ys : xs) {

				for (E value : ys) {

					try {

						if (!action.accept(x, y, z++, value)) {
							break;
						}

					} catch (Throwable e) {

						throw Exceptions.argument(e);

					}

				}

				y++;

				z = 0;

			}

			x++;

			y = 0;

		}

	}

	public static <I, K, V> void forEach(Map<K, V> map, InterruptBinaryConsumer<Integer, K, V> action) {

		Objects.requireNonNull(map);

		Objects.requireNonNull(action);

		int i = 0;

		for (Map.Entry<K, V> entry : map.entrySet()) {

			try {

				if (!action.accept(i++, entry.getKey(), entry.getValue())) {
					break;
				}

			} catch (Throwable e) {

				throw Exceptions.argument(e);

			}

		}

	}

	public static void forEach(BufferedImage image, InterruptBinaryConsumer<Integer, Integer, Integer> action) {

		for (int x = 0, w = image.getWidth(); x < w; x++) {

			for (int y = 0, h = image.getHeight(); y < h; y++) {

				try {

					if (!action.accept(x, y, image.getRGB(x, y))) {
						break;
					}

				} catch (Throwable e) {

					throw Exceptions.argument(e);

				}

			}

		}

	}

	public static void forArray(Object elements, InterruptUnaryConsumer<Integer, Object> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		Assert.isTrue(elements.getClass().isArray(), "the elements must be array.");

		for (int i = 0, length = Array.getLength(elements); i < length; i++) {

			try {

				if (!action.accept(i, Array.get(elements, i))) {
					break;
				}

			} catch (Throwable e) {

				throw Exceptions.argument(e);

			}

		}

	}

	public static <E> void forRange(E[] elements, int from, int to, InterruptUnaryConsumer<Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		Assert.isTrue(from > 0, "the from argument must bigger than zero.");

		Assert.isTrue(to > elements.length - 1, "the to argument must less than elements length.");

		for (int i = from; i < to; i++) {

			try {

				if (!action.accept(i, elements[i])) {
					break;
				}

			} catch (Throwable e) {

				throw Exceptions.argument(e);

			}

		}

	}

	public static <E> void forRange(E[][] elements, int fromX, int toX, int fromY, int toY, InterruptBinaryConsumer<Integer, Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		Assert.isTrue(fromX > 0, "the fromX argument must bigger than zero.");

		Assert.isTrue(fromY > 0, "the fromY argument must bigger than zero.");

		Assert.isTrue(toX > elements.length - 1, "the toX argument must less than elements length.");

		for (int x = fromX; x < toX; x++) {

			if (toY > elements[x].length - 1) {

				throw Exceptions.argument("the toY argument must less than elements[" + x + "] length.");

			}

			for (int y = fromY; y < toY; y++) {

				try {

					if (!action.accept(x, y, elements[x][y])) {
						break;
					}

				} catch (Throwable e) {

					throw Exceptions.argument(e);

				}

			}

		}

	}

	public static <E> void forRange(E[][][] elements, int fromX, int toX, int fromY, int toY, int fromZ, int toZ,
			InterruptTernaryConsumer<Integer, Integer, Integer, ? super E> action) {

		Objects.requireNonNull(elements);

		Objects.requireNonNull(action);

		Assert.isTrue(fromX > 0, "the fromX argument must bigger than zero.");

		Assert.isTrue(fromY > 0, "the fromY argument must bigger than zero.");

		Assert.isTrue(fromZ > 0, "the fromZ argument must bigger than zero.");

		Assert.isTrue(toX > elements.length - 1, "the toX argument must less than elements length.");

		for (int x = fromX; x < toX; x++) {

			if (toY > elements[x].length - 1) {

				throw Exceptions.argument("the toY argument must less than elements[" + x + "] length.");

			}

			for (int y = fromY; y < toY; y++) {

				if (toZ > elements[x][y].length - 1) {

					throw Exceptions.argument("the toZ argument must less than elements[" + x + "][" + y + "] length.");

				}

				for (int z = fromZ; z < toZ; z++) {

					try {

						if (!action.accept(x, y, z, elements[x][y][z])) {
							break;
						}

					} catch (Throwable e) {

						throw Exceptions.argument(e);

					}

				}

			}

		}

	}

}
