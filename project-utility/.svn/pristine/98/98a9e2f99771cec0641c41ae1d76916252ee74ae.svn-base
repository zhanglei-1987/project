package cn.quickly.project.utility.io;

import org.junit.Test;

import cn.quickly.project.utility.lang.Iterables;

public class DirectoryTest {

	@Test
	public void testPrintSystem() {

		Iterables.forEach(System.getenv(), (i, key, value) -> {

			System.out.println(key + " : " + value);

		});

		Iterables.forEach(System.getProperties(), (i, key, value) -> {

			System.out.println(key + " : " + value);

		});

	}

	@Test
	public void testTemporary() {

		System.out.println(Directory.temporary());

	}

	@Test
	public void testUser() {

		System.out.println(Directory.user());

	}

	@Test
	public void testList() {

		Iterables.forEach(Directory.list("e:/", true), (i, file) -> {

			System.out.println(file);

		});

	}

}
