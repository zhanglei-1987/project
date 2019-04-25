package cn.quickly.project.utility.security;

import java.io.InputStream;
import java.security.KeyStore;

import org.junit.Before;
import org.junit.Test;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.lang.Printer;

public class X509CertificatesTest {

	String algorithm = "JKS";

	String password = "123456";

	KeyStore keyStore;

	@Before
	public void setup() {

		InputStream in = StreamSeeker.classpath("/epcc.jks");

		keyStore = KeyStores.load(algorithm, in, password);

	}

	@Test
	public void testMapFromKeyStore() {

		Printer.println(X509Certificates.map(keyStore));

	}

}
