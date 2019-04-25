package cn.quickly.project.utility.reflect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.mock.MockDemo;
import cn.quickly.project.utility.mock.MockInstance;

public class MethodsTest {

	@Test
	public void testGetComponentClass() {

		BeanInjector injector = BeanInjector.getInjector(MockDemo.class);

		Iterables.forEach(injector.getDescriptors(), (i, descriptor) -> {

			Method method = descriptor.getWriteMethod();

			if (method != null) {

				System.out.println(method.getName() + " : " + Arrays.toString(Methods.getComponentClasses(method, 0)));

			}

		});

	}

	@Test
	public void testGetSupperMethod() throws Exception {

		Method method = MockInstance.class.getDeclaredMethod("say", String.class);

		System.out.println(Methods.getSuperMethod(method));

	}

}
