package cn.quickly.project.utility.text;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.map.DefaultHashMap;

public class Vocabulary {

	private Map<String, Integer> vocabularies;

	private Lock lock;

	public Vocabulary() {
		this.vocabularies = new TreeMap<>();
		this.lock = new ReentrantLock();
	}

	public Vocabulary(Map<String, Integer> terms, Lock lock) {
		this.vocabularies = terms;
		this.lock = lock;
	}

	public void addTerm(String... vocabularies) {

		lock.lock();

		try {

			Iterables.forEach(vocabularies, (i, vocabulary) -> {

				int count = Optional.ofNullable(this.vocabularies.get(vocabulary)).orElse(0);

				this.vocabularies.put(vocabulary, count + 1);

			});

		} finally {

			lock.unlock();
		}
		
	}

	public Map<String, Integer> getTerms() {

		Map<String, Integer> defaultVocabularies = new DefaultHashMap<>(0);
		defaultVocabularies.putAll(vocabularies);

		return defaultVocabularies;

	}

}
