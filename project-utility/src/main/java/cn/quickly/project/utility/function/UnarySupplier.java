package cn.quickly.project.utility.function;

@FunctionalInterface
public interface UnarySupplier<F, V> {

	V get(F first) throws Exception;

}
