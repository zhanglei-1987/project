package cn.quickly.project.utility.thirdparty;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.collection.Collections;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.reflect.Compat;
import cn.quickly.project.utility.regex.Patterns;
import cn.quickly.project.utility.text.Formats;
import ognl.MemberAccess;
import ognl.NoSuchPropertyException;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import ognl.TypeConverter;

public class Ognls {

	static {

		Ognl.addDefaultContext(null, OgnlMemberAccess.DEFAULT, null, OgnlTypeConverter.DEFAULT, new HashMap<>());

	}

	public static Object getValue(String expression, Object root) {

		if (root == null) {
			return null;
		}

		try {

			return Ognl.getValue(expression, Ognls.context(null), root);

		} catch (NoSuchPropertyException e) {
			return null;
		} catch (OgnlException e) {

			if (e.getMessage().startsWith("source is null for getProperty(null,")) {
				return null;
			}

			throw Exceptions.argument(e);
		}

	}

	public static <T> T getValue(String expression, Object root, Class<T> type) {

		if (root == null) {
			return null;
		}

		try {

			return Compat.cast(Ognl.getValue(expression, Ognls.context(null), root, type), type);

		} catch (NoSuchPropertyException e) {
			return null;
		} catch (OgnlException e) {

			if (e.getMessage().startsWith("source is null for getProperty(null,")) {
				return null;
			}

			throw Exceptions.argument(e);
		}

	}

	public static void execute(String expression, Object root) {

		getValue(expression, root);

	}

	public static String tokened(String text, Map<?, ?> bundle) {

		if (bundle != null) {

			String token = "\\$\\{([^:]*?)(\\:([^\\}]*?))?\\}";

			Matcher matcher = Patterns.matcher(text, token);

			while (matcher.find()) {

				String key = Strings.valueOf(matcher.group(1)).trim();

				String format = Strings.valueOf(matcher.group(3)).trim();

				Object value = bundle.get(key);

				try {
					value = Ognl.getValue(key, Ognls.context(null), bundle);
				} catch (Throwable e) {
				}

				if (value instanceof Date && !Strings.isEmpty(format)) {
					value = Formats.format((Date) value, format);
				}

				if (value == null) {
					text = Strings.replace(text, matcher.group(), "");
				} else {
					text = Strings.replace(text, matcher.group(), value + "");
				}
			}
		}
		return text;
	}

	public static <T extends Collection<V>, V> T filter(T c, String expression) {

		T n = Collections.clone(c);

		for (V item : c) {

			try {
				if (getValue(expression, Maps.asMap("item", item), Boolean.class)) {
					n.remove(item);
				}
			} catch (Throwable e) {
				n.remove(item);
			}

		}

		return n;

	}

	public static <T, V> Set<T> map(Collection<V> c, Class<T> type, String expression) {

		Set<T> values = new LinkedHashSet<T>();

		for (V item : c) {

			try {

				values.add(getValue(expression, Maps.asMap("item", item), type));

			} catch (Throwable e) {
			}

		}

		return values;

	}

	public static <K, V> Map<K, V> flatMap(Collection<V> c, Class<K> keyType, String expression) {

		Map<K, V> map = new LinkedHashMap<K, V>();

		for (V item : c) {

			try {

				map.put(getValue(expression, Maps.asMap("item", item), keyType), item);

			} catch (Throwable e) {
			}

		}

		return map;

	}

	public static void forEach(Collection<?> c, String... expressions) {

		int index = 0;

		for (Object item : c) {

			try {

				for (String expression : expressions) {
					Ognl.getValue(expression, Maps.multi(Arrays.as("item", "index"), Arrays.as(item, index)));
				}

			} catch (Throwable e) {
			}
			index++;
		}

	}

	public static void forEach(ZipInputStream zip, String... expressions) {

		int index = 0;

		for (;;) {

			try {

				ZipEntry entry = zip.getNextEntry();

				if (entry == null) {
					break;
				}

				for (String expression : expressions) {
					Ognl.getValue(expression, Maps.multi(Arrays.as("zip", "entry", "index"), Arrays.as(zip, entry, index)));
				}

			} catch (Throwable e) {
			}

			index++;

		}

	}

	public static void set(Object bean, String name, Object value) {

		String expression = Strings.concat("bean.set", name.substring(0, 1).toUpperCase(), name.substring(1), "(value)");

		Map<String, Object> context = new HashMap<>();
		context.put("bean", bean);
		context.put("value", value);

		execute(expression, context);

	}

	public static OgnlContext context(Object arguments) {

		if (arguments instanceof OgnlContext) {

			return (OgnlContext) arguments;

		} else {

			return new OgnlContext(OgnlMemberAccess.DEFAULT, null, OgnlTypeConverter.DEFAULT, Maps.getMap(arguments));

		}

	}

	@SuppressWarnings("rawtypes")
	static class OgnlMemberAccess implements MemberAccess {

		static MemberAccess DEFAULT = new OgnlMemberAccess();

		@Override
		public Object setup(Map context, Object target, Member member, String propertyName) {

			if (member instanceof AccessibleObject) {

				((AccessibleObject) member).setAccessible(true);

			}

			return true;
		}

		@Override
		public void restore(Map context, Object target, Member member, String propertyName, Object state) {

			if (state != null && state instanceof Boolean && member instanceof AccessibleObject) {
				((AccessibleObject) member).setAccessible((Boolean) state);
			}

		}

		@Override
		public boolean isAccessible(Map context, Object target, Member member, String propertyName) {
			return true;
		}

	}

	static class OgnlTypeConverter implements TypeConverter {

		static TypeConverter DEFAULT = new OgnlTypeConverter();

		@SuppressWarnings("rawtypes")
		public Object convertValue(Map context, Object target, Member member, String propertyName, Object value, Class toType) {

			return Compat.get(value, toType);

		}

	}

}
