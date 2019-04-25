package cn.quickly.project.utility.json;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cn.quickly.project.utility.reflect.Compat;
import cn.quickly.project.utility.reflect.GenericType;
import cn.quickly.project.utility.time.Dates;

public class JSONArray extends LinkedList<Object> implements JSON {

	private static final long serialVersionUID = 1L;

	public static JSONArray from(Object... objects) {
		return from(Arrays.asList(objects));
	}

	public static JSONArray from(Collection<?> collection) {
		JSONArray array = new JSONArray();
		array.addAll(collection);
		return array;
	}

	public static JSONArray from(String text) throws JSONException {
		return (JSONArray) JSONParser.parse(text);
	}

	public static <T> List<T> to(String text, Class<T> type) {

		List<T> items = new ArrayList<T>();

		JSONArray array = from(text);

		for (int i = 0, size = array.size(); i < size; i++) {

			items.add(Compat.cast(array.getJSONObject(i), type));

		}

		return items;

	}

	public static <T> List<T> to(String text, GenericType<T> type) {

		List<T> items = new ArrayList<T>();

		JSONArray array = from(text);

		for (int i = 0, size = array.size(); i < size; i++) {

			items.add(Compat.cast(array.getJSONObject(i), type));

		}

		return items;

	}

	public JSONObject getJSONObject(int index) {
		Object value = get(index);
		if (value instanceof JSONObject) {
			return (JSONObject) value;
		}
		return null;
	}

	public JSONArray getJSONArray(int index) {
		Object value = get(index);
		if (value instanceof JSONArray) {
			return (JSONArray) value;
		}
		return null;
	}

	public <T> T get(int index, Class<T> type) {

		return Compat.cast(get(index), type);

	}

	public Date getDate(int index, String format) throws ParseException {
		if (format != null) {
			return Dates.date(get(index, String.class), format);
		}
		return get(index, Date.class);
	}

	public String toString() {
		return JSONSerializer.stringify(this);
	}

}
