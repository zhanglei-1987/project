package cn.quickly.project.utility.reflect;

import java.util.Map;

import org.junit.Test;

import cn.quickly.project.utility.lang.CodeEnum;

public class ClassScannerTest {

	@Test
	public void testContextScan() {

		ClassScanner scanner = new ClassScanner();
		scanner.setPackageNames(new String[] { "" });
		scanner.setClassMatcher(new GenericClassMatcher(Map.class));

		System.out.println(scanner.scan());
	}

	@Test
	public void testJarScan() {

		// String url =
		// "http://localhost:8081/nexus/content/groups/public/commons-logging/commons-logging/1.2/commons-logging-1.2.jar";

		String url = "D:/quickly-repository/repository/ivy-cache/commons-logging/commons-logging/jars/commons-logging-1.2.jar";

		ClassScanner scanner = new ClassScanner();
		scanner.setClassMatcher(new GenericClassMatcher(Object.class));
		scanner.setJarPaths(url);
		scanner.setScanJarOnly(true);

		System.out.println(scanner.scan());
	}

	@Test
	public void testScan() throws Exception {

		ClassScanner scanner = new ClassScanner();
		scanner.setClassMatcher(new GenericClassMatcher(CodeEnum.class));
		scanner.setPackageNames("cn.quickly.project", "com.shfft");

		for (Class<?> type : scanner.scan()) {

			System.out.println(type);

		}

	}

}
