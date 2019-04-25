package cn.quickly.project.utility.security;

import java.io.File;

import org.junit.Test;

public class KeyStoresTest {

	@Test
	public void testKey() {

		File file = new File("d:/tmp/春秋航空股份有限公司.jks");
		String password = "72045a0e";

		System.out.println(KeyStores.key(KeyStores.load(file, password), password));

	}
}
