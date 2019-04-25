package cn.quickly.project.utility.lang;

import java.util.function.Supplier;

public class SupplierThreadLocal<T> extends ThreadLocal<T> {

	private Supplier<T> supplier;

	public SupplierThreadLocal(Supplier<T> supplier) {
		this.supplier = supplier;
	}

	@Override
	protected T initialValue() {
		return supplier.get();
	}

}
