package cn.quickly.project.utility.xml;

import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.junit.Test;

import cn.quickly.project.utility.concurrent.ThreadPools;
import cn.quickly.project.utility.lang.Loops;
import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.lang.Quiet;
import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.mock.MockXml;

public class JaxbsTest {

	@Test
	public void testMarshaller() throws Exception {

		JAXBContext context = JAXBContext.newInstance(MockXml.class);

		Marshaller marshaller = context.createMarshaller();

		System.out.println(marshaller);

	}

	@Test
	public void testFormat() throws Exception {

		MockXml bean = new MockXml();
		bean.setName("mock");

		System.out.println(Jaxbs.format(bean));

	}

	@Test
	public void testXml() throws Exception {

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(Jaxbs.FRAGMENT, true);
		// properties.put(Jaxbs.ENCODING, "UTF-8");

		Loops.loop(1, 50, (i) -> {

			ThreadPools.execute(() -> {

				MockXml bean = new MockXml();
				bean.setName("mock" + i);
				bean.setDate(new Date());

				try {
					System.out.println(Jaxbs.xml(bean, properties));
				} catch (Exception e) {
					e.printStackTrace();
				}

			});

		});

		Quiet.await(30000);

	}

	@Test
	public void testBean() throws Exception {

		String xml = "<mockXml xmlns=\"namespace_string\"><name>mock</name></mockXml>";

		Printer.println(Jaxbs.bean(xml, MockXml.class, Maps.asMap(Saxs.READ_IGNORE_NAMESPACE, true)));

	}

	@Test
	public void testXmlCompete() throws Exception {

		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(Jaxbs.FRAGMENT, true);
		// properties.put(Jaxbs.ENCODING, "UTF-8");

		Printer.compete(new int[] { 10000, 100000 }, (i) -> {

			MockXml bean = new MockXml();
			bean.setName("mock" + i);
			bean.setDate(new Date());

			try {
				Jaxbs.xml(bean, properties);
			} catch (Exception e) {
			}

		}, (i) -> {

			MockXml bean = new MockXml();
			bean.setName("mock" + i);
			bean.setDate(new Date());

			try {

				JAXBContext context = JAXBContext.newInstance(bean.getClass());

				Marshaller marshaller = context.createMarshaller();

				XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

				if (properties != null) {

					for (Map.Entry<String, ?> entry : properties.entrySet()) {
						marshaller.setProperty(entry.getKey(), entry.getValue());
					}

				}

				String encoding = (String) marshaller.getProperty(Jaxbs.ENCODING);

				Boolean fragment = (Boolean) marshaller.getProperty(Jaxbs.FRAGMENT);

				StringWriter out = new StringWriter();

				XMLStreamWriter writer = outputFactory.createXMLStreamWriter(out);

				if (!fragment) {
					marshaller.setProperty(Jaxbs.FRAGMENT, true);
					writer.writeStartDocument(encoding, null);
				}

				marshaller.marshal(bean, writer);

			} catch (Exception e) {
			}

		});

	}
}
