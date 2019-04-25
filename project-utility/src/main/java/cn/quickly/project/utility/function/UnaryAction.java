package cn.quickly.project.utility.function;

@FunctionalInterface
public interface UnaryAction<T> {

	void execute(T data) throws Exception;

}
