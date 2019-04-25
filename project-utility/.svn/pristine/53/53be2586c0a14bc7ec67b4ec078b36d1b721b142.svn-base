package cn.quickly.project.utility.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GenericTypeTest {

	@Test
	public void testClass() {

		GenericType<List<String>> type = new GenericType<List<String>>() {
		};

		System.out.println(type.getClass().getEnclosingClass());

		System.out.println(type.getClass().getComponentType());

		System.out.println(type.getClass().getDeclaringClass());

		ParameterizedType pt = (ParameterizedType) Classes.getGenericTypes(type.getClass())[0];

		System.out.println(pt.getOwnerType());
		System.out.println(pt.getRawType());
		System.out.println(pt.getActualTypeArguments()[0]);

	}

	@Test
	public void testList() {

		ArrayList<String> list = new ArrayList<>();

		Type type = Classes.getGenericTypes(list.getClass())[0];

		TypeVariable<?> variable = (TypeVariable<?>) type;

		System.out.println(variable.getBounds()[0]);

		ParameterizedType pt = (ParameterizedType) type;

		System.out.println(pt.getOwnerType());
		System.out.println(pt.getRawType());
		System.out.println(pt.getActualTypeArguments()[0]);

	}

}
