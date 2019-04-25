package cn.quickly.project.utility.lang;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import cn.quickly.project.utility.math.Maths;
import cn.quickly.project.utility.regex.Patterns;
import cn.quickly.project.utility.text.Formats;

public final class Strings {

	private Strings() {
		throw new UnsupportedOperationException();
	}

	public static String escape(String s) {
		if (s == null)
			return null;
		int length = s.length();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

	public static String html(String s) {
		if (s == null)
			return null;
		int length = s.length();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case ' ':
				sb.append("&nbsp;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '\"':
				sb.append("&quot;");
				break;
			case '\'':
				sb.append("&apos;");
				break;
			case '￠':
				sb.append("&cent;");
				break;
			case '£':
				sb.append("&pound;");
				break;
			case '¥':
				sb.append("&yen;");
				break;
			case '€':
				sb.append("&euro;");
				break;
			case '§':
				sb.append("&sect;");
				break;
			case '©':
				sb.append("&copy;");
				break;
			case '®':
				sb.append("&reg;");
				break;
			case '™':
				sb.append("&trade;");
				break;
			case '×':
				sb.append("&times;");
				break;
			case '÷':
				sb.append("&divide;");
				break;
			default:
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	public static String replace(String source, String from, String to) {
		if (source == null) {
			return null;
		}
		int i = 0;
		if ((i = source.indexOf(from, i)) >= 0) {
			char[] src = source.toCharArray();
			char[] cto = to.toCharArray();
			int len = from.length();
			StringBuilder buffer = new StringBuilder(src.length);
			buffer.append(src, 0, i).append(cto);
			i += len;
			int j = i;
			while ((i = source.indexOf(from, i)) > 0) {
				buffer.append(src, j, i - j).append(cto);
				i += len;
				j = i;
			}
			buffer.append(src, j, src.length - j);
			return buffer.toString();
		}
		return source;
	}

	public static int count(String source, char c) {
		int count = 0;
		if (!Strings.isEmpty(source)) {
			BitSet set = new BitSet();
			char[] chars = source.toCharArray();
			for (int i = 0, len = chars.length; i < len; i++) {
				if (chars[i] == c) {
					set.set(chars[i]);
				}
				if (set.get(chars[i])) {
					count++;
				}
			}
		}
		return count;
	}

	public static String concat(Object... textes) {
		StringBuffer buffer = new StringBuffer();
		for (Object text : textes) {
			buffer.append(Strings.valueOf(text));
		}
		return buffer.toString();
	}

	public static boolean isEmpty(String text) {
		if (text == null || text.trim().length() == 0)
			return true;
		return false;
	}

	public static String empty(String text, String def) {
		if (isEmpty(text)) {
			return def;
		}
		return text;
	}

	public static boolean isNumber(String text) {
		return !isEmpty(text) && text.trim().matches("(^\\-)?\\d+(\\.\\d+)?");
	}

	public static String toggle(String text, String value, String toggle) {
		if (isEmpty(text) || text.equalsIgnoreCase(value)) {
			return toggle;
		}
		return value;
	}

	public static String join(Collection<?> values, String in) {
		return join(values.toArray(), in);
	}

	public static String join(Object[] values, String in) {
		in = in == null ? "" : in;
		if (values != null && values.length > 0) {
			StringBuffer buffer = new StringBuffer();
			for (Object value : values) {
				buffer.append(String.valueOf(value));
				buffer.append(in);
			}
			buffer.setLength(buffer.length() - in.length());
			return buffer.toString();
		}
		return "";
	}

	public static String format(String text, Object... values) {
		if (text == null || values == null || values.length == 0) {
			return text;
		}
		int length = text.length();
		StringBuilder builder = new StringBuilder((int) (length * 1.5));
		StringBuilder cache = new StringBuilder();
		boolean is_segment = false;
		for (int i = 0; i < length; i++) {
			char c = text.charAt(i);
			if (c == '{') {
				if (is_segment) {
					builder.append('{').append(cache);
					cache.setLength(0);
				}
				is_segment = true;
			} else {
				if (is_segment) {
					if (c == '}') {
						String index = cache.toString().trim();
						try {
							int in = Integer.parseInt(index);
							if (in >= values.length || in < 0) {
								builder.append('{').append(cache).append('}');
							} else {
								builder.append(values[in]);
							}
						} catch (Throwable e) {
							builder.append('{').append(cache).append('}');
						}
						cache.setLength(0);
						is_segment = false;
					} else {
						cache.append(c);
					}
				} else {
					builder.append(c);
				}
			}
		}
		return builder.toString();
	}

	public static String format(String text, Map<?, ?> map) {
		if (text == null || map == null || map.isEmpty()) {
			return text;
		}
		int length = text.length();
		StringBuilder builder = new StringBuilder((int) (length * 1.5));
		StringBuilder cache = new StringBuilder();
		boolean is_segment = false;
		for (int i = 0; i < length; i++) {
			char c = text.charAt(i);
			if (c == '{') {
				if (is_segment) {
					builder.append('{').append(cache);
					cache.setLength(0);
				}
				is_segment = true;
			} else {
				if (is_segment) {
					if (c == '}') {
						String key = cache.toString().trim();
						Object value = map.get(key);
						if (value != null) {
							builder.append(value);
						}
						cache.setLength(0);
						is_segment = false;
					} else {
						cache.append(c);
					}
				} else {
					builder.append(c);
				}
			}
		}
		return builder.toString();
	}

	public static String prefix(String text, String delimiter) {

		return prefix(text, delimiter, 0);

	}

	public static String prefix(String text, String delimiter, int from) {

		if (isEmpty(text)) {
			return "";
		}

		int offset = text.indexOf(delimiter, from);

		if (offset > -1) {
			return text.substring(0, offset);
		}

		return text;
	}

	public static String suffix(String text, String delimiter) {

		if (isEmpty(text)) {
			return "";
		}

		int offset = text.lastIndexOf(delimiter);

		if (offset > -1) {

			return text.substring(offset + delimiter.length());

		}

		return text;
	}

	public static String suffix(String text, String delimiter, int from) {

		if (isEmpty(text)) {
			return "";
		}

		int offset = text.lastIndexOf(delimiter, from);

		if (offset > -1) {

			return text.substring(offset + delimiter.length());

		}

		return text;
	}

	public static boolean equals(String source, String... targets) {

		for (String target : targets) {

			if (target != null && target.equals(source)) {
				return true;
			}

		}

		return false;
	}

	public static boolean equalsIgnoreCase(String source, String... targets) {

		for (String target : targets) {

			if (target != null && target.equalsIgnoreCase(source)) {
				return true;
			}

		}

		return false;
	}

	public static String tokened(String text, Map<?, ?> bundle) {

		if (bundle != null) {

			String token = "\\$\\{([^:]*?)(\\:([^\\}]*?))?\\}";

			Matcher matcher = Patterns.matcher(text, token);

			while (matcher.find()) {

				String key = Strings.valueOf(matcher.group(1)).trim();

				String format = Strings.valueOf(matcher.group(3)).trim();

				Object value = bundle.get(key);

				if (value != null && !isEmpty(format)) {
					if (value instanceof Date) {
						value = Formats.format((Date) value, format);
					} else if (value instanceof Number) {
						value = Formats.format((Number) value, format);
					}
				}

				if (value == null) {
					text = replace(text, matcher.group(), "");
				} else {
					text = replace(text, matcher.group(), value + "");
				}
			}
		}
		return text;
	}

	public static String print(Throwable cause) {
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		cause.printStackTrace(out);
		Quiet.close(out);
		return writer.toString();
	}

	public static String[] split(String text, char d) {
		List<String> list = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		for (char c : text.toCharArray()) {
			if (c != d) {
				builder.append(c);
			} else {
				list.add(builder.toString());
				builder.setLength(0);
			}
		}
		if (builder.length() > 0) {
			list.add(builder.toString());
		}
		return list.toArray(new String[list.size()]);
	}

	public static String repeat(String c, int count) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < count; i++) {
			builder.append(c);
		}
		return builder.toString();
	}

	public static String lpad(Object value, char padder, int length) {
		String text = value == null ? "" : value + "";
		return concat(repeat(padder + "", length - text.length()), text);

	}

	public static String rpad(Object value, char padder, int length) {
		String text = value == null ? "" : value + "";
		return concat(text, repeat(padder + "", length - text.length()));

	}

	public static String valueOf(Object data) {
		return (data == null) ? "" : data.toString();
	}

	public static String substring(String text, int start, int end) {
		return isEmpty(text) ? "" : text.substring((int) Maths.loop(start, 0, text.length() - 1), end < 0 ? text.length() + end : end);
	}

	public static String substring(String text, String start, String end) {

		if (isEmpty(text)) {
			return "";
		}

		int startOffset = text.indexOf(start);

		int endOffset = text.indexOf(end);

		if (startOffset < endOffset) {
			return text.substring(startOffset == -1 ? 0 : startOffset + start.length(), endOffset == -1 ? text.length() : endOffset);
		}

		return text;
	}

	public static byte[] getBytes(String text, String charset) {
		try {
			return text.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw Exceptions.argument(e);
		}
	}

	public static String reverse(String text) {

		StringBuilder builder = new StringBuilder();

		for (int i = text.length() - 1; i >= 0; i--) {
			builder.append(text.charAt(i));
		}

		return builder.toString();

	}

	public static String swap(String text) {

		StringBuilder builder = new StringBuilder();

		for (int i = 0, end = text.length() - 1; i < end; i += 2) {

			builder.append(text.charAt(i + 1));
			builder.append(text.charAt(i));

		}

		if (text.length() > builder.length()) {
			builder.append(text.charAt(text.length() - 1));
		}

		return builder.toString();

	}

	public static boolean endsWith(String source, String... suffixs) {

		if (Strings.isEmpty(source)) {
			return false;
		}

		for (String suffix : suffixs) {

			if (source.endsWith(suffix)) {

				return true;

			}

		}

		return false;
	}

	public static boolean startsWith(String source, String... prefixs) {

		if (Strings.isEmpty(source)) {
			return false;
		}

		for (String prefix : prefixs) {

			if (source.startsWith(prefix)) {

				return true;

			}

		}

		return false;
	}

}
