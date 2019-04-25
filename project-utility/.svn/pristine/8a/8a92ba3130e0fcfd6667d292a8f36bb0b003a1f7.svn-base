package cn.quickly.project.utility.io;

import java.io.File;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;

public class FilesTest {

	@Test
	public void testFilter() {

		Printer.println(Files.filter(new File("."), ".*\\.properties", true));

	}

	@Test
	public void testSuffix() {

		Printer.println(Files.suffix(new File("."), "xml", true));
	}

	@Test
	public void testFiltern() {

		Printer.println(Files.filtern(new File("."), ".*\\.xml", true));
	}

	@Test
	public void testSuffixn() {

		Printer.println(Files.suffixn(new File("."), "xml", true));
	}

	@Test
	public void testMove() {

		Files.move(new File("d:/data"), new File("d:/data-move"), true);

	}

	@Test
	public void testCopy() {

		// Files.copy(new File("d:/data-move"), new File("d:/data-copy"), true);

		Files.copy(new File("D:/工资流水"), new File("D:/工资流水/1"), true);

	}

}
