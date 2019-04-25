package cn.quickly.project.utility.io;

import org.junit.Test;

public class StreamSeekerTest {

	@Test
	public void testFind() {

		System.out.println(StreamSeeker.find("find:changelog.txt"));

		System.out.println(StreamSeeker.find("find:mock.jks"));

	}

	@Test
	public void testJarPath() {

		System.out.println(StreamSeeker.class.getResource("/").getFile());

		System.out.println(StreamSeeker.classpath("/log4j.properties"));

	}

}
