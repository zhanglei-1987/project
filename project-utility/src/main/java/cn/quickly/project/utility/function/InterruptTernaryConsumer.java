package cn.quickly.project.utility.function;

@FunctionalInterface
public interface InterruptTernaryConsumer<F, S, T, V> {

	boolean accept(F first, S second, T third, V value) throws Exception;

}
