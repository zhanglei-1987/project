package cn.quickly.project.utility.reflect;

import javax.jms.Message;

import org.junit.Test;

import cn.quickly.project.utility.json.JSONObject;
import cn.quickly.project.utility.mock.MockEnum;

public class ClassesTest {

	@Test
	public void testJarPath() {
		System.out.println(Classes.getJarPath(ClassesTest.class));
		System.out.println(Classes.getJarPath(Message.class));
	}

	@Test
	public void testDefindeClass() throws Exception {
		byte[] classData = Classes.lookupClass(BeanInjectorTest.class);
		System.out.println(Classes.defineClass("cn.quickly.project.utility.reflect.BeanInjectorTest", classData).newInstance());
	}

	@Test
	public void testSuperClass() {

		System.out.println(Classes.isSuperClass(int.class, Integer.class));
		System.out.println(Classes.isSuperClass(Enum.class, MockEnum.class));

	}

	@Test
	public void testGetInterfaces() {

		System.out.println(Classes.getInterfaces(ClassesTest.class));

		System.out.println(Classes.getInterfaces(JSONObject.class));

	}

}
