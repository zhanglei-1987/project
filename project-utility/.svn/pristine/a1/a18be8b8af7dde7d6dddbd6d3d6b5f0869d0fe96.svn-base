package cn.quickly.project.utility.type;

import java.util.Stack;

import cn.quickly.project.utility.lang.Objects;

public class StackConverter extends CollectionConverter<Stack<Object>> {

	@Override
	public Stack<Object> convert(Class<?> type, Object value) {

		Stack<Object> stack = getEmptyStack(type);

		add(stack, value, type);

		return stack;

	}

	@SuppressWarnings("unchecked")
	protected Stack<Object> getEmptyStack(Class<?> type) {

		if (type.isInterface()) {
			return new Stack<>();
		}

		return (Stack<Object>) Objects.getInstance(type);

	}

}
