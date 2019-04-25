package cn.quickly.project.utility.net;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.map.SupplierHashMap;
import cn.quickly.project.utility.regex.Patterns;

public final class Urls {

	private static final Pattern QUERY_PATTERN = Pattern.compile("([^&=]+)=?([^&]+)?");

	private Urls() {
		throw new UnsupportedOperationException();
	}

	public static String encode(String url, String charset, int count) {

		String text = url;

		for (int i = 0; i < count; i++) {

			text = encode(text, charset);

		}

		return text;

	}

	public static String encode(String url, String charset) {

		if (!Strings.isEmpty(url)) {

			try {

				return URLEncoder.encode(url, charset);

			} catch (UnsupportedEncodingException e) {
				throw Exceptions.argument(e);
			}

		}

		return url;

	}

	public static String decode(String url, String charset, int count) {

		String text = url;

		for (int i = 0; i < count; i++) {

			text = decode(text, charset);

		}

		return text;

	}

	public static String decode(String url, String charset) {

		if (!Strings.isEmpty(url)) {

			try {

				return URLDecoder.decode(url, charset);

			} catch (UnsupportedEncodingException e) {
				throw Exceptions.argument(e);
			}

		}

		return url;

	}

	public static String query(Map<String, ?> params, String charset) {

		return query(null, params, charset);

	}

	public static String query(String url, Map<String, ?> params, String charset) {

		StringBuilder builder = new StringBuilder();

		if (!Strings.isEmpty(url)) {

			builder.append(url);

			int offset = url.indexOf("?");

			if (offset > -1) {

				if (offset < url.length() - 1) {
					builder.append('&');
				}

			} else {
				builder.append('?');
			}

		}

		for (Iterator<String> it = params.keySet().iterator(); it.hasNext();) {

			String name = it.next();

			Object value = params.get(name);

			if (value != null) {

				if (!Strings.isEmpty(charset)) {

					value = Urls.encode(value + "", charset);

				}

			} else {
				value = "";
			}

			builder.append(name);

			if (!Strings.isEmpty(Strings.valueOf(value))) {

				builder.append("=").append(value);

			}

			if (it.hasNext()) {
				builder.append("&");
			}

		}

		return builder.toString();
	}

	public static Map<String, List<String>> query(String url, String charset) {

		int offset = url.indexOf("?");

		if (offset > -1) {

			url = url.substring(offset + 1);

		}

		Map<String, List<String>> params = new SupplierHashMap<>((name) -> new ArrayList<String>(), true);

		Matcher matcher = QUERY_PATTERN.matcher(url);

		while (matcher.find()) {

			List<String> values = params.get(matcher.group(1));

			values.add(decode(matcher.group(2), charset));

		}

		return params;
	}

	public static String marshaled(String url) {

		if (!Strings.isEmpty(url)) {

			url = url.replaceAll("[\\s]+", "");

			url = url.replace("/./", "/");

			url = url.replace("\\/", "/");

			int offset = 7;

			while ((offset = url.indexOf("//", offset)) > 0) {
				url = url.substring(0, offset).concat(url.substring(offset + 1));
			}

			while ((offset = url.indexOf("/../", offset)) > 0) {

				String pathSegment = url.substring(0, offset);

				int slashIndex = pathSegment.lastIndexOf('/');

				if (slashIndex < 7) {
					url = pathSegment.concat(url.substring(offset + 3));
				} else {
					url = url.substring(0, slashIndex).concat(url.substring(offset + 3));
				}

			}

			if (url.endsWith("/")) {

				url = url.substring(0, url.length() - 1);

			}

		}

		return url;
	}

	public static boolean isUrl(String url) {

		if (!Strings.isEmpty(url)) {

			return Patterns.matches(url, "^(\\w+)://[^\\s]+");

		}

		return false;

	}

	public static boolean isProtocol(String uri, String protocol) {

		if (!Strings.isEmpty(uri)) {

			return Patterns.matches(uri, Strings.concat("^(", protocol, ")://[^\\s]+"));

		}

		return false;

	}

	public static boolean isHttp(String url) {

		return isProtocol(url, "http");

	}

	public static boolean isHttps(String url) {

		return isProtocol(url, "https");

	}

	public static boolean isFtp(String url) {

		return isProtocol(url, "ftp");

	}

	public static boolean isThunder(String url) {

		return isProtocol(url, "thunder");

	}

	public static boolean isQvod(String url) {

		return isProtocol(url, "qvod");

	}

	public static boolean isEd2k(String url) {

		if (!Strings.isEmpty(url)) {

			return Patterns.matches(url, "^(ed2k://\\|file\\|)[^\\s]+\\|\\d+\\|[\\w]{32}\\|([^\\s]*)?/");

		}

		return false;

	}

	public static boolean isMagnet(String url) {

		if (!Strings.isEmpty(url)) {

			return Patterns.matches(url, "^(magnet:\\?)[^\\s]+");

		}

		return false;

	}

	public static boolean isMail(String mail) {

		if (!Strings.isEmpty(mail)) {

			return Patterns.matches(mail, "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

		}

		return false;
	}

	public static URL url(String url) {

		try {
			return new URL(url);
		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}
}
