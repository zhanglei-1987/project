package cn.quickly.project.utility.reflect;

import java.lang.reflect.Method;

import org.junit.Test;

import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.mock.MockComponent;
import cn.quickly.project.utility.mock.MockDemo;
import cn.quickly.project.utility.mock.MockInstance;
import cn.quickly.project.utility.mock.MockMethod;
import cn.quickly.project.utility.mock.MockService;

public class AnnotationsTest {

	@Test
	public void testNotGetExtend() {

		System.out.println(Annotations.getExtend(MockDemo.class, Override.class));

	}

	@Test
	public void testGetExtend() {

		System.out.println(MockDemo.class.getAnnotation(MockComponent.class));

		MockService mockService = MockDemo.class.getAnnotation(MockService.class);

		System.out.println(mockService.annotationType().getAnnotation(MockComponent.class));

		System.out.println(mockService.getClass().getAnnotation(MockComponent.class));

		System.out.println(MockDemo.class.getAnnotation(MockService.class));

		System.out.println(Annotations.getExtend(MockDemo.class, MockComponent.class));

		System.out.println(Annotations.getExtend(MockDemo.class, MockComponent.class));

	}

	@Test
	public void testGetMap() {

		MockService mockService = MockDemo.class.getAnnotation(MockService.class);

		System.out.println(Annotations.getMap(mockService));
		System.out.println(Maps.getMap(mockService));

	}

	@Test
	public void testGetDeclared() throws Exception {

		Method method = MockInstance.class.getDeclaredMethod("say", String.class);

		System.out.println(method);

		System.out.println(Annotations.getDeclared(method, MockMethod.class));

	}

}
