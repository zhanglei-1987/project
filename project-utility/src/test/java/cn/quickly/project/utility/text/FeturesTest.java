package cn.quickly.project.utility.text;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FeturesTest {

	@Test
	public void testTfidf() {

		Map<String, Integer> counts = new HashMap<>();
		counts.put("名字", 128);
		counts.put("回家", 18);

		System.out.println(Features.tfidf(counts, counts.keySet()));

		System.out.println(Features.tfidf(counts, "名字", "玩笑"));

	}

	@Test
	public void testSimilarity() {

		Map<String, Integer> counts = new HashMap<>();
		counts.put("名字", 128);
		counts.put("回家", 18);
		counts.put("过年", 18);

		Map<String, Double> tfidf = Features.tfidf(counts, counts.keySet());

		String[] a = { "名字", "过年" };

		String[] b = { "回家", "过年" };

		System.out.println(Features.angle(tfidf, a, b));
		System.out.println(Features.distance(tfidf, a, b));

	}

}
