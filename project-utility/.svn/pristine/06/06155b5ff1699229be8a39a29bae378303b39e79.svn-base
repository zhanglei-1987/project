package cn.quickly.project.utility.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Strings;

public final class TextManager {

	private volatile static Hashtable<String, TextManager> managerCache = new Hashtable<String, TextManager>();

	private Properties texts = new Properties();

	public TextManager(String file) {
		try {
			InputStream in = StreamSeeker.find(file);
			Assert.isNotNull(in, "can't load file " + file);
			if (file.endsWith(".xml")) {
				texts.loadFromXML(in);
			} else {
				texts.load(in);
			}
			for (Object key : texts.keySet()) {
				String name = (String) key;
				String value = texts.getProperty(name);
				if (!Strings.isEmpty(value)) {
					value = value.trim();
				}
				texts.setProperty(name, value);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized static TextManager getManager(String file) {
		TextManager manager = managerCache.get(file);
		if (manager == null) {
			manager = new TextManager(file);
			managerCache.put(file, manager);
		}
		return manager;
	}

	public String getText(String key, Object... values) {
		Assert.isNotNull(key, "the key is required.");
		if (!texts.containsKey(key)) {
			throw new IllegalArgumentException(Strings.format("can't find text associated with key '{0}'.", key));
		}
		String text = texts.getProperty(key);
		text = Strings.tokened(text, texts);
		return Strings.format(text, values);
	}

	public String getDefaultText(String key, String def, Object... values) {
		Assert.isNotNull(key, "the key is required.");
		if (!texts.containsKey(key)) {
			throw new IllegalArgumentException(Strings.format("can't find text associated with key '{0}'.", key));
		}
		String text = texts.getProperty(key, def);
		text = Strings.tokened(text, texts);
		return Strings.format(text, values);
	}

	public String getText(String key, Map<String, ?> values) {
		Assert.isNotNull(key, "the key is required.");
		if (!texts.containsKey(key)) {
			throw new IllegalArgumentException(Strings.format("can't find text associated with key '{0}'.", key));
		}
		String text = texts.getProperty(key);
		text = Strings.tokened(text, texts);
		return Strings.format(text, values);
	}

	public String getDefaultText(String key, String def, Map<String, ?> values) {
		Assert.isNotNull(key, "the key is required.");
		if (!texts.containsKey(key)) {
			throw new IllegalArgumentException(Strings.format("can't find text associated with key '{0}'.", key));
		}
		String text = texts.getProperty(key, def);
		text = Strings.tokened(text, texts);
		return Strings.format(text, values);
	}

	public static void clear() {
		synchronized (TextManager.class) {
			managerCache.clear();
		}
	}
}
