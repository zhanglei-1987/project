package cn.quickly.project.utility.xml;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import cn.quickly.project.utility.concurrent.Locks;
import cn.quickly.project.utility.function.UnaryAction;
import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.lang.Objects;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.reflect.Compat;

public class Saxs {

	private static final Map<String, SAXParser> parsers = new ConcurrentHashMap<>();

	public static final String READ_IGNORE_NAMESPACE = "sax.read.ignore.namespace";

	public static final String READ_VALIDATING = "sax.read.validating";

	public static DocumentBuilder builder(UnaryAction<DocumentBuilderFactory> actor) throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		actor.execute(factory);

		return factory.newDocumentBuilder();

	}

	public static SAXParser parser(final Map<String, ?> data) throws Exception {

		final Map<String, ?> properties = Maps.filter(Objects.empty(data, new HashMap<>()), (i, key, value) -> !Strings.isEmpty(key) && key.startsWith("sax."));

		return Locks.dcl(parsers, String.valueOf(properties), (s) -> {

			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

			if (properties != null) {

				Boolean ignoreNamespace = (Boolean) properties.remove(READ_IGNORE_NAMESPACE);
				if (ignoreNamespace != null) {
					saxParserFactory.setNamespaceAware(!ignoreNamespace);
				}

				Boolean validating = (Boolean) properties.remove(READ_VALIDATING);
				if (validating != null) {
					saxParserFactory.setValidating(validating);
				}

				Iterables.forEach(properties, (i, name, value) -> {

					if (name.startsWith("sax.")) {

						saxParserFactory.setFeature(name, Compat.cast(value, Boolean.class));

					}

				});

			}

			return saxParserFactory.newSAXParser();

		});
	}

	public static void clear() {

		parsers.clear();

	}

}
