package cn.quickly.project.utility.lang;

import cn.quickly.project.utility.function.UnaryAction;
import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.reflect.BeanInjector;
import cn.quickly.project.utility.reflect.Compat;

public final class Beans {

	private Beans() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 属性复制
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 */
	public static <T> T copy(Object source, T target) {

		Assert.isNotNull(source, "the source object required.");
		Assert.isNotNull(target, "the target object required.");

		BeanInjector injector = BeanInjector.getInjector(target.getClass());

		injector.setProperties(target, Maps.getMap(source));

		return target;

	}

	/**
	 * 属性复制
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 */
	public static <T> T copy(Object source, Class<T> targetClass) {

		return copy(source, targetClass, (bean) -> {
		});

	}

	/**
	 * 属性复制
	 * 
	 * 
	 * @param source
	 * @param targetClass
	 * @param action
	 * @return
	 */
	public static <T> T copy(Object source, Class<T> targetClass, UnaryAction<T> action) {

		Assert.isNotNull(source, "the source object required.");
		Assert.isNotNull(targetClass, "the target class required.");

		T bean = Compat.cast(source, targetClass);

		try {
			action.execute(bean);
		} catch (Exception e) {
			throw Exceptions.runtime(e);
		}

		return bean;

	}

	/**
	 * 对象克隆
	 * 
	 * @param source
	 *            源对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T clone(T source) {

		BeanInjector injector = BeanInjector.getInjector(source.getClass());
		return (T) injector.getProperties(Maps.getMap(source));

	}

}
