package cn.quickly.project.utility.page;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import cn.quickly.project.utility.concurrent.ThreadPools;
import cn.quickly.project.utility.function.BinarySupplier;
import cn.quickly.project.utility.function.UnaryConsumer;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public class Pages {

	public static <T> void forEach(BinarySupplier<Integer, Integer, List<T>> supplier, UnaryConsumer<Integer, T> consumer) throws Exception {

		forEach(1024, supplier, consumer);

	}

	public static <T> void forEach(int size, BinarySupplier<Integer, Integer, List<T>> supplier, UnaryConsumer<Integer, T> consumer) throws Exception {

		Pageable pageable = new Pageable();
		pageable.setSize(size);
		pageable.setPage(1);

		int index = 0;

		for (;;) {

			List<T> rows = supplier.get(pageable.getStartOffset(), pageable.getEndOffset());

			if (rows == null) {
				break;
			}

			for (T element : rows) {

				consumer.accept(index++, element);

			}

			if (rows.size() != size) {
				break;
			}

			pageable.setPage(pageable.getPage() + 1);

		}

	}

	public static <T> void parallelEach(BinarySupplier<Integer, Integer, List<T>> supplier, UnaryConsumer<Integer, T> consumer) throws Exception {

		parallelEach(1024, supplier, consumer);

	}

	public static <T> void parallelEach(int size, BinarySupplier<Integer, Integer, List<T>> supplier, UnaryConsumer<Integer, T> consumer) throws Exception {

		Pageable pageable = new Pageable();
		pageable.setSize(size);
		pageable.setPage(1);

		AtomicInteger index = new AtomicInteger();

		for (;;) {

			List<T> rows = supplier.get(pageable.getStartOffset(), pageable.getEndOffset());

			if (rows == null) {
				break;
			}

			for (T element : rows) {

				ThreadPools.execute(() -> {

					try {

						consumer.accept(index.incrementAndGet(), element);

					} catch (Exception e) {

						throw Exceptions.runtime(e);

					}

				});

			}

			if (rows.size() != size) {
				break;
			}

			while (!ThreadPools.isQuiescent()) {

				Quiet.await(1000);

			}

			pageable.setPage(pageable.getPage() + 1);

		}

	}

	public static <T> void forList(BinarySupplier<Integer, Integer, List<T>> supplier, UnaryConsumer<Integer, List<T>> consumer) throws Exception {

		forList(1024, supplier, consumer);

	}

	public static <T> void forList(int size, BinarySupplier<Integer, Integer, List<T>> supplier, UnaryConsumer<Integer, List<T>> consumer) throws Exception {

		Pageable pageable = new Pageable();
		pageable.setSize(size);
		pageable.setPage(1);

		for (;;) {

			List<T> rows = supplier.get(pageable.getStartOffset(), pageable.getEndOffset());

			if (rows == null) {
				break;
			}

			consumer.accept(pageable.getPage(), rows);

			if (rows.size() != size) {
				break;
			}

			pageable.setPage(pageable.getPage() + 1);

		}

	}

	public static <T> void parallelList(BinarySupplier<Integer, Integer, List<T>> supplier, UnaryConsumer<Integer, List<T>> consumer) throws Exception {

		parallelList(1024, supplier, consumer);

	}

	public static <T> void parallelList(int size, BinarySupplier<Integer, Integer, List<T>> supplier, UnaryConsumer<Integer, List<T>> consumer)
			throws Exception {

		Pageable pageable = new Pageable();
		pageable.setSize(size);
		pageable.setPage(1);

		for (;;) {

			List<T> rows = supplier.get(pageable.getStartOffset(), pageable.getEndOffset());

			if (rows == null) {
				break;
			}

			ThreadPools.execute(() -> {

				try {

					consumer.accept(pageable.getPage(), rows);

				} catch (Exception e) {

					throw Exceptions.runtime(e);

				}

			});

			if (rows.size() != size) {
				break;
			}

			while (!ThreadPools.isQuiescent()) {

				Quiet.await(1000);

			}

			pageable.setPage(pageable.getPage() + 1);

		}

	}

}
