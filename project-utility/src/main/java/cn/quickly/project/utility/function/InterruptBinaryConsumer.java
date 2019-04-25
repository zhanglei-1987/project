package cn.quickly.project.utility.function;

@FunctionalInterface
public interface InterruptBinaryConsumer<F, S, V> {

	boolean accept(F first, S second, V value) throws Exception;

}
