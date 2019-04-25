package cn.quickly.project.utility.security;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;

import org.junit.Before;
import org.junit.Test;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Printer;

public class CertificatesTest {

	String algorithm = "JKS";

	String password = "123456";

	KeyStore keyStore;

	@Before
	public void setup() {

		InputStream in = StreamSeeker.classpath("/epcc.jks");

		keyStore = KeyStores.load(algorithm, in, password);

	}

	@Test
	public void testOne() {

		Printer.println(Certificates.one(keyStore));

	}

	@Test
	public void testLoad() throws Exception {

		InputStream in = StreamSeeker.classpath("/public.cer");

		String data = Streams.getString(in, "UTF-8").replace("\\n", "\n");

		System.out.println(data);

		InputStream buffer = new ByteArrayInputStream(data.getBytes());

		Certificate certificate = Certificates.load(Certificates.ALGORITHM_X509, buffer);

		System.out.println(certificate);

	}

}
