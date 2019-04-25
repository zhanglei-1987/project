package cn.quickly.project.utility.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;

import cn.quickly.project.utility.function.BinarySupplier;
import cn.quickly.project.utility.lang.Exceptions;

public class LeastUse<E> {

	protected ReentrantLock lock = new ReentrantLock();

	protected NodeComparator comparator = new NodeComparator();

	protected List<Node> nodes = Collections.synchronizedList(new LinkedList<>());

	public void addNode(E element) {

		nodes.add(new Node(element));

	}

	public <V> V use(BinarySupplier<Integer, E, V> action) {

		Node first = getFirst();

		int count = first.counter.incrementAndGet();

		try {

			return action.get(count, first.element);

		} catch (Exception e) {

			throw Exceptions.runtime(e);

		} finally {

			first.counter.decrementAndGet();

		}

	}

	public void use(BiConsumer<Integer, E> action) {

		Node first = getFirst();

		int count = first.counter.incrementAndGet();

		try {

			action.accept(count, first.element);

		} finally {

			first.counter.decrementAndGet();

		}

	}

	protected Node getFirst() {

		lock.lock();

		try {

			Collections.sort(nodes, comparator);

			return nodes.get(0);

		} finally {

			lock.unlock();

		}

	}

	public List<E> getNodes() {
		List<E> elements = new ArrayList<E>();
		for (Node node : nodes) {
			elements.add(node.element);
		}
		return elements;
	}

	public void clear() {

		lock.lock();

		try {

			nodes.clear();

		} finally {

			lock.unlock();

		}

		lock.unlock();

	}

	private class Node {

		AtomicInteger counter = new AtomicInteger();

		E element;

		public Node(E element) {
			this.element = element;
		}

	}

	private class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o1.counter.get() > o2.counter.get() ? 1 : -1;
		}

	}

}
