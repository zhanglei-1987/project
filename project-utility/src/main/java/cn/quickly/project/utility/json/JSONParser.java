package cn.quickly.project.utility.json;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Date;

import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Characters;
import cn.quickly.project.utility.lang.Strings;

public final class JSONParser {

	public static Object parse(String json) throws JSONException {
		if (json != null) {
			try {
				return parse(new PushbackReader(new StringReader(json)));
			} catch (IOException e) {
				throw new JSONException(e);
			}
		}
		return null;
	}

	public static Object parse(Reader reader) throws IOException {
		return parse(new PushbackReader(reader));
	}

	public static Object parse(PushbackReader reader) throws IOException {
		Object json = null;
		for (int c = reader.read(); c != -1; c = reader.read()) {
			if (Characters.isSpaceChar((char) c)) {
				continue;
			} else {
				json = parse_skip_by_char(reader, (char) c);
				break;
			}
		}
		return json;
	}

	static Object parse_skip_by_char(PushbackReader reader, char c) throws IOException {

		Object json = null;

		switch (c) {
		case JSON.OBJECT_START_SYMBOL: {
			json = parse_json_object(reader);
			break;
		}
		case JSON.ARRAY_START_SYMBOL: {
			json = parse_json_array(reader);
			break;
		}
		case JSON.DOUBLE_QUOTATION_MARK: {
			json = parse_string_dq(reader);
			break;
		}
		case JSON.SINGLE_QUOTATION_MARK: {
			json = parse_string_sq(reader);
			break;
		}
		case 'D': {
			reader.unread(c);
			json = parse_date(reader);
			break;
		}
		case 'f': {
			reader.unread(c);
			String bool = Streams.getString(reader, 5);
			if ("false".equals(bool.trim())) {
				json = false;
			}
			break;
		}
		case 't': {
			reader.unread(c);
			String bool = Streams.getString(reader, 4);
			if ("true".equals(bool.trim())) {
				json = true;
			}
			break;
		}
		case 'n': {
			reader.unread(c);
			Streams.getString(reader, 4);
			break;
		}
		default:
			if ((c >= 48 && c <= 57) || c == JSON.NUMBER_BAR_SYMBOL) {
				reader.unread((char) c);
				json = parse_number(reader);
			}
			break;
		}

		return json;
	}

	static Object parse_json_object(PushbackReader reader) throws IOException {

		JSONObject jo = new JSONObject();

		String key = null;

		Object value = null;

		boolean next_is_key = true;

		for (int c = reader.read(); c != -1; c = reader.read()) {

			if (!Characters.isSpaceChar((char) c)) {

				if (next_is_key) {

					if (c == JSON.OBJECT_END_SYMBOL) {

						break;

					} else if (c == JSON.KEY_SYMBOL) {

						next_is_key = false;

						continue;

					} else if (c == JSON.DOUBLE_QUOTATION_MARK) {

						next_is_key = false;

						key = parse_string_dq(reader);

					} else if (c == JSON.SINGLE_QUOTATION_MARK) {

						next_is_key = false;

						key = parse_string_sq(reader);

					} else if (c != JSON.ELEMENT_SPLIT_SYMBOL && !Characters.isSpaceChar((char) c)) {

						reader.unread(c);

						key = Streams.getString(reader, JSON.KEY_SYMBOL, JSON.KEY_SYMBOL, true);

						if (!Strings.isEmpty(key)) {
							key = key.trim();
						}

						next_is_key = false;

						continue;

					}

				} else {

					value = parse(reader);

					jo.put(key, value);

					next_is_key = true;

				}

			}

		}

		return jo;
	}

	static Object parse_json_array(PushbackReader reader) throws IOException {

		JSONArray ja = new JSONArray();

		for (int c = reader.read(); c != -1; c = reader.read()) {

			if (Characters.isSpaceChar((char) c) || c == JSON.ELEMENT_SPLIT_SYMBOL) {
				continue;
			} else if (c == JSON.ARRAY_END_SYMBOL) {
				break;
			} else {
				ja.add(parse_skip_by_char(reader, (char) c));
			}

		}

		return ja;
	}

	static Date parse_date(PushbackReader reader) throws IOException {
		String date = Streams.getString(reader, 5);
		if (date.startsWith(JSON.DATE_PREFIX)) {
			date = Streams.getString(reader, new char[] { JSON.DATE_SUFFIX }, false);
			return new Date(Long.parseLong(date));
		} else {
			reader.unread(JSON.ELEMENT_SPLIT_SYMBOL);
		}
		return null;
	}

	static String parse_string_sq(PushbackReader reader) throws IOException {
		String text = Streams.getString(reader, JSON.SINGLE_QUOTATION_MARK, JSON.SINGLE_QUOTATION_MARK, true);
		if (Strings.isEmpty(text)) {
			text = "";
		}
		return text.replace("\\" + JSON.SINGLE_QUOTATION_MARK, JSON.SINGLE_QUOTATION_MARK + "");
	}

	static String parse_string_dq(PushbackReader reader) throws IOException {
		String text = Streams.getString(reader, JSON.DOUBLE_QUOTATION_MARK, JSON.DOUBLE_QUOTATION_MARK, true);
		if (Strings.isEmpty(text)) {
			text = "";
		}
		return text.replace("\\" + JSON.DOUBLE_QUOTATION_MARK, JSON.DOUBLE_QUOTATION_MARK + "");
	}

	static Object parse_number(PushbackReader reader) throws IOException {

		StringBuilder buffer = new StringBuilder();

		boolean dotable = true, barable = true;

		for (int c = reader.read(); c != -1; c = reader.read()) {

			if (c >= 48 && c <= 57) {

				buffer.append((char) c);

			} else if (c == JSON.NUMBER_DOT_SYMBOL) {

				if (!dotable) {
					throw new JSONException(Strings.concat("unexcept char '.' , nearby ", buffer, "."));
				}

				buffer.append((char) c);

				dotable = false;

			} else if (c == JSON.NUMBER_BAR_SYMBOL) {

				if (!barable) {
					throw new JSONException(Strings.concat("unexcept char '-' , nearby ", buffer, "-"));
				}

				buffer.append((char) c);

				barable = false;

			} else {
				reader.unread(c);
				break;
			}

		}

		String text = buffer.toString();

		Double number = Double.parseDouble(text);

		if (text.indexOf(".") < 0) {
			return number.longValue();
		} else if (number.toString().equals(text)) {
			return number;
		}

		return new BigDecimal(buffer.toString());

	}
}
