package cn.quickly.project.utility.map;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.text.FormatFactory;

public final class I18nManager {

	private FormatFactory formatFactory = FormatFactory.getFactory();

	private TextManager bundle;

	private TextManager defaultBundle;

	public I18nManager(String i18n) {
		this(i18n, Locale.getDefault());
	}

	public I18nManager(String name, Locale locale) {

		String path = name.replace(".", "/");

		String defaultPath = Strings.concat("find:", path, ".properties");

		if (locale != null) {
			path = Strings.concat("find:", path, "_", locale, ".properties");
		} else {
			path = defaultPath;
		}
		try {
			bundle = TextManager.getManager(path);
		} catch (Exception e) {
		}
		try {
			defaultBundle = TextManager.getManager(defaultPath);
		} catch (Exception ex) {
			if (bundle == null) {
				String message = Strings.concat("Can't find i18n bundle [", name, "].");
				throw new IllegalArgumentException(message);
			}
		}
		if (bundle == null) {
			bundle = defaultBundle;
		}
	}

	public String getMessage(String key, Object... values) {
		String text = bundle.getText(key);
		String defaultText = defaultBundle.getText(key);
		text = Strings.empty(text, defaultText);
		MessageFormat format = formatFactory.getMessageFormat(text);
		return format.format(values);
	}

	public String getDefaultMessage(String key, String def, Object... values) {
		String text = bundle.getText(key);
		String defaultText = defaultBundle.getText(key, def);
		text = Strings.empty(text, defaultText);
		MessageFormat format = formatFactory.getMessageFormat(text);
		return format.format(values);
	}

	public String getText(String key, Object... values) {
		String text = bundle.getText(key, values);
		String defaultText = defaultBundle.getText(key, values);
		return Strings.empty(text, defaultText);
	}

	public String getDefaultText(String key, String def, Object... values) {
		String text = bundle.getText(key, values);
		String defaultText = defaultBundle.getText(key, def, values);
		return Strings.empty(text, defaultText);
	}

	public String getText(String key, Map<String, ?> values) {
		String text = bundle.getText(key, values);
		String defaultText = defaultBundle.getText(key, values);
		return Strings.empty(text, defaultText);
	}

	public String getDefaultText(String key, String def, Map<String, ?> values) {
		String text = bundle.getText(key, values);
		String defaultText = defaultBundle.getText(key, def, values);
		return Strings.empty(text, defaultText);
	}

}
