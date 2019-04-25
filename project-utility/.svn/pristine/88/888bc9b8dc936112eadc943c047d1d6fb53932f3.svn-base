package cn.quickly.project.utility.lang;

import java.lang.annotation.Annotation;

import org.junit.Test;

import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.mock.MockDemo;
import cn.quickly.project.utility.mock.MockService;

public class ObjectsTest {

	@Test
	public void testGetInstance() {

		System.out.println(Objects.getInstance(MockDemo.class, "hello"));

	}

	@Test
	public void testIsAnnotation() {

		MockService mockService = MockDemo.class.getAnnotation(MockService.class);

		System.out.println(Objects.isAnnotation(mockService));
		System.out.println(Objects.isAnnotation("mock"));

		System.out.println(mockService instanceof Annotation);

	}

	@Test
	public void testLike() {

		Printer.println(Objects.like(Maps.asMap("name", "zhang"), Maps.asMap("name", "zhang")));

		Printer.println(Objects.like(Maps.asMap("name", "zhang"), Maps.asMap("age", "16")));

	}

}
