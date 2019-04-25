package cn.quickly.project.utility.text;

import java.util.Collection;
import java.util.Map;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.map.DefaultHashMap;
import cn.quickly.project.utility.math.Maths;

public class Features {

	public static double tf(Map<String, Integer> counts, String word, int total) {

		return (double) counts.get(word) / (double) total;

	}

	public static double idf(int total, int count) {

		return Math.log((double) total / (1 + (double) count));

	}

	public static Map<String, Double> tfidf(Map<String, Integer> counts, Collection<String> words) {

		words.retainAll(counts.keySet());

		int total = counts.values().stream().reduce(0, (sum, i) -> sum + i);

		Map<String, Double> tfidfs = new DefaultHashMap<>(0d);

		tfidfs.putAll(Iterables.map(words, Double.class, (word) -> {

			double tf = tf(counts, word, total);

			double idf = idf(total, counts.get(word));

			return tf * idf;

		}));

		return tfidfs;

	}

	public static Map<String, Double> tfidf(Map<String, Integer> counts, String... words) {

		return tfidf(counts, Arrays.asSet(words));

	}

	public static double angle(Map<String, Double> tfidf, String[] source, String[] target) {

		double[] sd = new double[source.length];

		Iterables.forEach(source, (i, k) -> sd[i] = tfidf.get(k));

		double[] td = new double[source.length];

		Iterables.forEach(target, (i, k) -> td[i] = tfidf.get(k));

		return Maths.cos(sd, td);

	}

	public static double distance(Map<String, Double> tfidf, String[] source, String[] target) {

		double[] sd = new double[source.length];

		Iterables.forEach(source, (i, k) -> sd[i] = tfidf.get(k));

		double[] td = new double[source.length];

		Iterables.forEach(target, (i, k) -> td[i] = tfidf.get(k));

		return Maths.hypot(sd, td);

	}

}
