package cn.quickly.project.utility.json;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;

import cn.quickly.project.utility.lang.Objects;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.reflect.Compat;
import cn.quickly.project.utility.reflect.GenericType;
import cn.quickly.project.utility.time.Dates;

public class JSONObject extends LinkedHashMap<String, Object> implements JSON {

	private static final long serialVersionUID = 1L;

	public static JSONObject from(Object object) {

		if (object instanceof String) {

			return (JSONObject) JSONParser.parse((String) object);

		} else {

			JSONObject jo = new JSONObject();

			if (!Objects.isBasic(object)) {
				jo.putAll(Maps.getMap(object));
			}

			return jo;

		}
	}

	public static <T> T to(String text, Class<T> type) {

		return Compat.cast(from(text), type);

	}

	public static <T> T to(String text, GenericType<T> type) {

		return Compat.cast(from(text), type);

	}

	public void set(String name, Object value) {
		put(name, value);
	}

	public JSONObject getJSONObject(String name) {

		Object value = get(name);

		if (value instanceof JSONObject) {
			return (JSONObject) value;
		}

		return null;
	}

	public JSONArray getJSONArray(String name) {

		Object value = get(name);

		if (value instanceof JSONArray) {
			return (JSONArray) value;
		}

		return null;

	}

	public <T> T get(String name, Class<T> type) {

		return Compat.cast(get(name), type);

	}

	public Date getDate(String name, String format) throws ParseException {

		if (!Strings.isEmpty(format)) {
			return Dates.date(get(name, String.class), format);
		}

		return get(name, Date.class);

	}

	public String toString() {
		return JSONSerializer.stringify(this);
	}

}
