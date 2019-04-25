package cn.quickly.project.utility.function;

@FunctionalInterface
public interface TernaryConsumer<F, S, T, V> {

	void accept(F first, S second, T third, V value) throws Exception;

}
