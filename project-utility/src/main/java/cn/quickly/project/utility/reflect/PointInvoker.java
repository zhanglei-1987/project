package cn.quickly.project.utility.reflect;

import java.lang.reflect.Method;

import cn.quickly.project.utility.lang.Exceptions;

public final class PointInvoker {

	private Object target;

	private String method;

	private Object[] arguments;

	private boolean forceInvoke;

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

	public boolean isForceInvoke() {
		return forceInvoke;
	}

	public void setForceInvoke(boolean forceInvoke) {
		this.forceInvoke = forceInvoke;
	}

	public Object invoke() {

		Method targetMethod = Methods.getSuperMethod(target.getClass(), true, method, Classes.getClasses(arguments));

		if (targetMethod != null) {

			try {

				if (!targetMethod.isAccessible() && forceInvoke) {

					targetMethod.setAccessible(forceInvoke);

				}

				return targetMethod.invoke(target, arguments);

			} catch (Exception e) {
			}

		} else {

			throw Exceptions.argument("can't find target invoke point for the arguments.");

		}

		return null;
	}
}
