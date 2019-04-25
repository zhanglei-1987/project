package cn.quickly.project.utility.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import cn.quickly.project.utility.lang.Exceptions;

public class RoundRobin<E> {

	protected List<Node> nodes = Collections.synchronizedList(new LinkedList<Node>());

	protected Queue<E> queue = new ConcurrentLinkedQueue<E>();

	protected ReentrantLock lock = new ReentrantLock();

	protected boolean dirty = false;

	protected Predicate<E> predicate;

	public RoundRobin() {

		this((e) -> true);

	}

	public RoundRobin(Predicate<E> predicate) {

		this.predicate = predicate;

	}

	public void addNode(int weight, E node) {

		lock.lock();

		try {

			nodes.add(new Node(weight, node));

			dirty = true;

		} finally {

			lock.unlock();

		}

	}

	public E getNode() {

		E node = queue.poll();

		if (node == null) {
			fillQueue();
			node = queue.poll();
		}

		if (!dirty && node != null) {
			queue.offer(node);
		}

		return node;
	}

	public List<E> getNodes() {
		List<E> elements = new ArrayList<E>();
		for (Node node : nodes) {
			elements.add(node.element);
		}
		return elements;
	}

	public int size() {
		return nodes.size();
	}

	public void clear() {

		lock.lock();

		try {

			nodes.clear();

			queue.clear();

			dirty = true;

		} finally {

			lock.unlock();

		}

	}

	protected void fillQueue() {

		lock.lock();

		try {

			List<Node> avaibleNodes = nodes.stream().filter((node) -> predicate.test(node.element)).collect(Collectors.toList());

			int total = 0, size = avaibleNodes.size();

			if (size > 0) {

				Integer[] weights = new Integer[size];

				for (int i = 0; i < size; i++) {

					Node node = avaibleNodes.get(i);

					weights[i] = node.weight;

					total += node.weight;

				}

				Arithmetic arithmetic = new Arithmetic(weights);

				for (int i = 0; i < total; i++) {
					queue.offer(avaibleNodes.get(arithmetic.next()).element);
				}

			} else {
				throw Exceptions.argument("there are not found node in RoundRobin.");
			}

			dirty = false;

		} finally {

			lock.unlock();

		}

	}

	private class Arithmetic {

		private Integer[] server;// 机器序号：权重
		private int cw = 0;
		private int number = -1;// 当前SERVER的序号,开始是-1
		private int max;// 最大权重
		private int gcd;// 最大公约数

		public Arithmetic(Integer[] weights) {

			server = weights;

			max = max(server);
			gcd = gcd(server);
		}

		/**
		 * 求最大公约数
		 * 
		 * @param array
		 * @return
		 */
		private int gcd(Integer[] weights) {

			int min = weights[0];

			for (int i = 0; i < weights.length; i++) {
				if (weights[i] < min) {
					min = weights[i];
				}
			}
			while (min >= 1) {
				boolean isCommon = true;
				for (int i = 0; i < weights.length; i++) {
					if (weights[i] % min != 0) {
						isCommon = false;
						break;
					}
				}
				if (isCommon) {
					break;
				}
				min--;
			}
			return min;
		}

		/**
		 * 求最大值，权重
		 * 
		 * @return
		 */

		private int max(Integer[] weights) {
			int max = 0;
			for (int weight : weights) {
				max = Math.max(max, weight);
			}
			return max;
		}

		/**
		 * 获取请求的SERVER序号
		 * 
		 * @return
		 */
		public Integer next() {

			while (true) {
				number = (number + 1) % server.length;
				if (number == 0) {
					cw = cw - gcd;// cw比较因子，从最大权重开始，以最大公约数为步长递减
					if (cw <= 0) {
						cw = max;
						if (cw == 0)
							return null;
					}
				}
				if (server[number] >= cw)
					return number;
			}

		}

	}

	private class Node {

		int weight;

		E element;

		public Node(int weight, E element) {
			this.weight = weight;
			this.element = element;
		}

	}

}
