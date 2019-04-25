package cn.quickly.project.utility.reflect;

import cn.quickly.project.utility.reflect.PointInvoker;

public class PointInvokerTest {

	public static void main(String[] args) {
		PointInvoker pi = new PointInvoker();
		pi.setTarget(new PointInvokerTest());
		pi.setMethod("test");
		pi.setArguments(new Object[] { null, 60 });
		pi.setForceInvoke(true);
		pi.invoke();
	}

	public void test() {
		System.out.println("--------------");
	}

	 void test(int a, Integer b) {
		System.out.println(a + "|" + b);
	}
}
