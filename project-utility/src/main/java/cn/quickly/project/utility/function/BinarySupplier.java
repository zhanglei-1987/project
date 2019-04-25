package cn.quickly.project.utility.function;

@FunctionalInterface
public interface BinarySupplier<F, S, V> {

	V get(F first, S second) throws Exception;

}
