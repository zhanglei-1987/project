package cn.quickly.project.utility.collection;

import org.junit.Test;

import cn.quickly.project.utility.concurrent.ThreadPools;
import cn.quickly.project.utility.lang.Quiet;
import cn.quickly.project.utility.lang.Randoms;

public class LeastUseTest {

	@Test
	public void test() {

		LeastUse<String> leastUse = new LeastUse<>();

		leastUse.addNode("A");
		leastUse.addNode("B");
		leastUse.addNode("C");

		for (int i = 0; i < 10; i++) {

			ThreadPools.execute(() -> {

				leastUse.use((c, e) -> {

					System.out.println(e + ":" + c);

					Quiet.await(Randoms.nextInt(100, 500));

				});

			});

		}

		Quiet.await(5000);

		leastUse.use((c, e) -> {

			System.out.println(e + ":" + c);

		});

	}

}
