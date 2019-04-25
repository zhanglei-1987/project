package cn.quickly.project.utility.type;

import java.net.URL;

import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.net.Urls;

public class URLConverter implements TypeConverter<URL> {

	@Override
	public URL convert(Class<?> type, Object value) {

		String url = Strings.valueOf(value);

		if (Urls.isUrl(url)) {

			return Urls.url(url);

		}

		return null;
	}

}
