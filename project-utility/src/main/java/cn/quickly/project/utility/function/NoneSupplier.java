package cn.quickly.project.utility.function;

@FunctionalInterface
public interface NoneSupplier<V> {

	V get() throws Exception;

}
