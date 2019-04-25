package cn.quickly.project.utility.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import cn.quickly.project.utility.lang.ClassLoaders;

public final class ProxyFactory<T> {

	private ClassLoader classLoader;

	private InvocationHandler invocationHandler;

	private Class<?>[] proxyInterfaces;

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public InvocationHandler getInvocationHandler() {
		return invocationHandler;
	}

	public void setInvocationHandler(InvocationHandler invocationHandler) {
		this.invocationHandler = invocationHandler;
	}

	public Class<?>[] getProxyInterfaces() {
		return proxyInterfaces;
	}

	public void setProxyInterfaces(Class<?>... proxyInterfaces) {
		this.proxyInterfaces = proxyInterfaces;
	}

	@SuppressWarnings("unchecked")
	public T getProxy() {

		if (classLoader == null) {
			classLoader = ClassLoaders.system();
		}

		return (T) Proxy.newProxyInstance(classLoader, proxyInterfaces, invocationHandler);

	}

}
