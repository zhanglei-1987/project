package cn.quickly.project.utility.lang;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

import cn.quickly.project.utility.reflect.Classes;

public class ClassLoadersTest {

	@Test
	public void testLocalJar() throws Exception {

		String url = "file:/D:/quickly-repository/repository/ivy-cache/commons-logging/commons-logging/jars/commons-logging-1.2.jar";

		URLClassLoader loader = ClassLoaders.url(url);

		System.out.println(Classes.getClass("org.apache.commons.logging.Log", loader));

		loader.close();

		System.out.println(new File(url).toURI().toURL());

		loader = new URLClassLoader(new URL[] { new URL(url) });
		System.out.println(loader.loadClass("org.apache.commons.logging.Log"));

		loader.close();

	}

}
