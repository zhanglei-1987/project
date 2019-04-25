package cn.quickly.project.utility.security;

import java.security.Security;

import org.junit.Test;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Printer;

public class SecurityTest {

	@Test
	public void testProviders() {

		Printer.println(Arrays.extract(Security.getProviders(), String.class, "Provider.id name"));

		Printer.println(Security.getProvider("SUN"));

	}

}
