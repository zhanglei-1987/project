package cn.quickly.project.utility.function;

@FunctionalInterface
public interface UnaryConsumer<F, V> {

	void accept(F first, V value) throws Exception;

}
