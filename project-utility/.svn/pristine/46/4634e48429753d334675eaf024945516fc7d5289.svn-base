package cn.quickly.project.utility.security;

import java.io.File;

import org.junit.Test;

public class ChecksumsTest {

	private File file = new File("src/test/resources/log4j.properties");

	@Test
	public void testCrc32() {

		System.out.println(Checksums.crc32("dsafds".getBytes()));

		System.out.println(Checksums.crc32(file));

	}

	@Test
	public void testAdler32() {

		System.out.println(Checksums.adler32("dsafds".getBytes()));

		System.out.println(Checksums.adler32(file));

	}

}
