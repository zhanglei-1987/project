package cn.quickly.project.utility.net;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;

import org.junit.Test;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.net.KeyManagers;
import cn.quickly.project.utility.security.KeyStores;

public class KeyManagersTest {

	String algorithm = "JKS";

	String password = "123456";

	@Test
	public void testX509() throws Exception {

		InputStream in = StreamSeeker.classpath("/mock.jks");

		KeyStore keyStore = KeyStores.load(algorithm, in, password);

		Printer.println(keyStore);

		KeyManager[] managers = KeyManagers.x509(keyStore, password);

		Printer.println(managers);

	}

}
