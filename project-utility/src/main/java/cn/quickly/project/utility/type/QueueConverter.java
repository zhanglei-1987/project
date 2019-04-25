package cn.quickly.project.utility.type;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import cn.quickly.project.utility.lang.Objects;

public class QueueConverter extends CollectionConverter<Queue<Object>> {

	@Override
	public Queue<Object> convert(Class<?> type, Object value) {

		Queue<Object> queue = getEmptyQueue(type);

		add(queue, value, type);

		return queue;

	}

	@SuppressWarnings("unchecked")
	protected Queue<Object> getEmptyQueue(Class<?> type) {

		if (type.isInterface()) {
			return new LinkedBlockingQueue<>();
		}

		return (Queue<Object>) Objects.getInstance(type);

	}

}
