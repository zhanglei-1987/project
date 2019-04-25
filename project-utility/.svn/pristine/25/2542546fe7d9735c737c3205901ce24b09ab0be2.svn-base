package cn.quickly.project.utility.servlet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import cn.quickly.project.utility.reflect.Compat;

public class FormInputs extends LinkedHashMap<String, List<Serializable>> {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_ITEM_DELIMITER = "&";

	private static final String DEFAULT_VALUE_DELIMITER = ",";

	private static final String DEFAULT_PAIR_DELIMITER = "=";

	public FormInputs() {

	}

	public FormInputs(String query) {
		this(query, DEFAULT_ITEM_DELIMITER);
	}

	public FormInputs(String query, String itemDelimiter) {
		this(query, itemDelimiter, DEFAULT_PAIR_DELIMITER, DEFAULT_VALUE_DELIMITER);
	}

	public FormInputs(String query, String itemDelimiter, String pairDelimiter, String valueDelimiter) {

		StringTokenizer itemTokenizer = new StringTokenizer(query, itemDelimiter);

		while (itemTokenizer.hasMoreTokens()) {

			String item = (String) itemTokenizer.nextElement();

			int offset = item.indexOf(pairDelimiter);

			if (offset > -1) {

				String key = item.substring(0, offset);

				String values = item.substring(offset + 1);

				for (String value : values.split(valueDelimiter)) {

					add(key, value);

				}

			} else {

				add(item, "");

			}

		}

	}

	@Override
	public List<Serializable> get(Object key) {

		List<Serializable> inputs = super.get(key);

		if (inputs == null) {

			inputs = new LinkedList<>();

			put((String) key, inputs);

		}

		return inputs;
	}

	public <T> T get(String key, Class<T> type) {

		List<Serializable> values = get(key);

		if (values != null && !values.isEmpty()) {
			return Compat.cast(values.get(0), type);
		}

		return null;

	}

	public void add(String name, Object value) {
		get(name).add(value == null ? "" : value + "");
	}

	public void addAll(Map<String, ?> inputs) {

		for (Map.Entry<String, ?> entry : inputs.entrySet()) {
			add(entry.getKey(), entry.getValue());
		}

	}

	public String getParameter(String name) {

		List<Serializable> inputs = get(name);

		if (inputs.size() > 0) {
			return inputs.get(0) + "";
		}

		return null;

	}

	public Map<String, Object> getParameters() {

		Map<String, Object> parameters = new HashMap<String, Object>();

		for (String name : keySet()) {

			parameters.put(name, getParameter(name));

		}

		return parameters;

	}

	public String getQueryString() {
		return getQueryString(DEFAULT_ITEM_DELIMITER);
	}

	public String getQueryString(String itemDelimiter) {
		return getQueryString(itemDelimiter, DEFAULT_PAIR_DELIMITER, DEFAULT_VALUE_DELIMITER);
	}

	public String getQueryString(String itemDelimiter, String pairDelimiter, String valueDelimiter) {
		StringBuilder builder = new StringBuilder();

		Iterator<Map.Entry<String, List<Serializable>>> it = entrySet().iterator();

		while (it.hasNext()) {

			Map.Entry<String, List<Serializable>> entry = it.next();

			builder.append(entry.getKey()).append(pairDelimiter);

			Iterator<Serializable> vit = entry.getValue().iterator();

			while (vit.hasNext()) {

				builder.append(vit.next());

				if (vit.hasNext()) {
					builder.append(valueDelimiter);
				}

			}

			if (it.hasNext()) {
				builder.append(itemDelimiter);
			}

		}

		return builder.toString();
	}

	public String getBlockString() {
		return getBlockString("|");
	}

	public String getBlockString(String itemDelimiter) {
		return getBlockString(itemDelimiter, DEFAULT_VALUE_DELIMITER);
	}

	public String getBlockString(String itemDelimiter, String valueDelimiter) {

		StringBuilder builder = new StringBuilder();

		Iterator<Map.Entry<String, List<Serializable>>> it = entrySet().iterator();

		while (it.hasNext()) {

			Iterator<Serializable> vit = it.next().getValue().iterator();

			while (vit.hasNext()) {

				builder.append(vit.next());

				if (vit.hasNext()) {
					builder.append(valueDelimiter);
				}

			}

			if (it.hasNext()) {
				builder.append(itemDelimiter);
			}
		}

		return builder.toString();
	}

}
