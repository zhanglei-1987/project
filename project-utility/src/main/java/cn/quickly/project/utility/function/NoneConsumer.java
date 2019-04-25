package cn.quickly.project.utility.function;

@FunctionalInterface
public interface NoneConsumer<T> {

	void accept(T value) throws Exception;

}
