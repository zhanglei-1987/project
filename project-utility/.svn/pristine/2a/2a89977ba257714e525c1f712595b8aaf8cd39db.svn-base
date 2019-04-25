package cn.quickly.project.utility.collection;

import org.junit.Test;

import cn.quickly.project.utility.lang.Strings;

public class RoundRobinTest {

	@Test
	public void testSimple() {

		RoundRobin<String> robin = new RoundRobin<>();
		robin.addNode(1, "A");
		robin.addNode(200, "B");
		robin.addNode(300, "C");

		for (int i = 0; i < 10; i++) {

			System.out.println(robin.getNode());

		}

	}

	@Test
	public void testPredicate() {

		RoundRobin<String> robin = new RoundRobin<>((e) -> Strings.equalsIgnoreCase(e, "A", "C"));
		robin.addNode(1, "A");
		robin.addNode(2, "B");
		robin.addNode(3, "C");

		for (int i = 0; i < 10; i++) {

			System.out.println(robin.getNode());

		}

	}

}
