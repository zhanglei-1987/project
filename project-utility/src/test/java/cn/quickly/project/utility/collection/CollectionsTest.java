package cn.quickly.project.utility.collection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.quickly.project.utility.collection.Collections;

public class CollectionsTest {

	@Test
	public void testToArray() {

		List<String> trees = new ArrayList<>();
		trees.add("A");

		System.out.println(Collections.toArray(String.class, trees));

	}
}
