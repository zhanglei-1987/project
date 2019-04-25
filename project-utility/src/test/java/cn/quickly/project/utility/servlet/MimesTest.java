package cn.quickly.project.utility.servlet;

import org.junit.Test;

import cn.quickly.project.utility.servlet.Mimes;

public class MimesTest {

	@Test
	public void testGetContentType() {

		System.out.println(Mimes.getContentType("txt"));
		System.out.println(Mimes.getContentType("xml"));
		System.out.println(Mimes.getContentType("html"));
		System.out.println(Mimes.getContentType("zip"));
		System.out.println(Mimes.getContentType("json"));
		
	}

}
