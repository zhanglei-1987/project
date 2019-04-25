package cn.quickly.project.utility.net;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.TrustManager;

import org.junit.Test;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.security.KeyStores;

public class TrustManagersTest {

	String algorithm = "JKS";

	String password = "123456";

	@Test
	public void testPKIX() throws Exception {

		InputStream in = StreamSeeker.classpath("/epcc.jks");

		KeyStore keyStore = KeyStores.load(algorithm, in, password);

		Printer.println(keyStore.aliases());

		TrustManager[] managers = TrustManagers.pkix(keyStore);

		Printer.println(managers);

	}

}
