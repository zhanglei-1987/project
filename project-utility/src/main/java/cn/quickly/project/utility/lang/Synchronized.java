package cn.quickly.project.utility.lang;

import java.util.Collection;
import java.util.Map;

import cn.quickly.project.utility.reflect.ProxyFactory;
import cn.quickly.project.utility.reflect.SynchronizedInvocationHandler;

public class Synchronized {

	public static <K, V> Map<K, V> map(Map<K, V> source) {

		ProxyFactory<Map<K, V>> factory = new ProxyFactory<>();
		factory.setProxyInterfaces(Map.class);
		factory.setInvocationHandler(new SynchronizedInvocationHandler(source));

		return factory.getProxy();

	}

	public static <E, C extends Collection<E>> C collection(C source) {

		ProxyFactory<C> factory = new ProxyFactory<>();
		factory.setProxyInterfaces(source.getClass().getInterfaces());
		factory.setInvocationHandler(new SynchronizedInvocationHandler(source));

		return factory.getProxy();

	}

	public static <T extends I, I> I generic(T source, Class<I> interfaceClass) {

		ProxyFactory<I> factory = new ProxyFactory<>();
		factory.setProxyInterfaces(interfaceClass);
		factory.setInvocationHandler(new SynchronizedInvocationHandler(source));

		return factory.getProxy();

	}

}
