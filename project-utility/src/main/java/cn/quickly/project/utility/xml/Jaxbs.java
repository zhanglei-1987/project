package cn.quickly.project.utility.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import cn.quickly.project.utility.concurrent.Locks;
import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.map.Maps;

public final class Jaxbs {

	private static final Map<Class<?>, JAXBContext> contents = new ConcurrentHashMap<>();

	public static final String ENCODING = "jaxb.encoding";

	public static final String FORMATTED_OUTPUT = "jaxb.formatted.output";

	public static final String SCHEMA_LOCATION = "jaxb.schemaLocation";

	public static final String NO_NAMESPACE_SCHEMA_LOCATION = "jaxb.noNamespaceSchemaLocation";

	public static final String FRAGMENT = "jaxb.fragment";

	private Jaxbs() {
		throw new UnsupportedOperationException();
	}

	public static String format(Object bean) throws Exception {
		return xml(bean, Maps.asMap(Marshaller.JAXB_FORMATTED_OUTPUT, true));
	}

	public static String xml(Object bean, Map<String, ?> properties) throws Exception {

		JAXBContext context = Locks.dcl(contents, bean.getClass(), (beanClass) -> JAXBContext.newInstance(beanClass));

		Marshaller marshaller = context.createMarshaller();

		if (properties != null) {

			Iterables.forEach(properties, (i, name, value) -> {

				if (name.startsWith("jaxb.")) {

					marshaller.setProperty(name, value);

				}

			});

		}

		String encoding = (String) marshaller.getProperty(Jaxbs.ENCODING);

		Boolean fragment = (Boolean) marshaller.getProperty(Jaxbs.FRAGMENT);

		try (StringWriter out = new StringWriter()) {

			XMLStreamWriter writer = XMLFactorys.output.createXMLStreamWriter(out);

			if (!fragment) {
				marshaller.setProperty(Jaxbs.FRAGMENT, true);
				writer.writeStartDocument(encoding, null);
			}

			marshaller.marshal(bean, writer);

			writer.flush();
			writer.close();

			return out.toString();

		}

	}

	@SuppressWarnings("unchecked")
	public static <T> T bean(String xml, Class<T> type, Map<String, ?> properties) throws Exception {

		JAXBContext context = Locks.dcl(contents, type, (beanClass) -> JAXBContext.newInstance(beanClass));

		Unmarshaller unmarshaller = context.createUnmarshaller();

		XMLReader xmlReader = Saxs.parser(properties).getXMLReader();

		try (StringReader reader = new StringReader(xml)) {

			if (properties != null) {

				Iterables.forEach(properties, (i, name, value) -> {

					if (name.startsWith("jaxb.")) {

						unmarshaller.setProperty(name, value);

					}

				});

			}

			Source source = new SAXSource(xmlReader, new InputSource(reader));

			return (T) unmarshaller.unmarshal(source);

		}
		
	}

	public static void clear() {
		contents.clear();
	}

}
