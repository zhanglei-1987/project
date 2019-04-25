package cn.quickly.project.utility.net;

import java.io.File;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;
import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.security.KeyStores;

public class SocketFactorysTest {

	@Test
	public void testGetTrustAll() throws Exception {

		BouncyCastleJsseProvider provider = new BouncyCastleJsseProvider(new BouncyCastleProvider());

		URL url = new URL("https://59.151.65.97/preSvr");

		SSLSocketFactory socketFactory = SocketFactorys.trust(provider);

		Printer.println(socketFactory.getDefaultCipherSuites());

		System.out.println("---------------------------------------");

		Printer.println(socketFactory.getSupportedCipherSuites());

		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setSSLSocketFactory(socketFactory);

		connection.connect();

	}

	@Test
	public void testGet() throws Exception {

		BouncyCastleJsseProvider provider = new BouncyCastleJsseProvider(new BouncyCastleProvider());

		String password = "123456";

		KeyStore keyStore = KeyStores.load(new File("src/test/resources/epcc.jks"), password);

		SSLSocketFactory socketFactory = SocketFactorys.trust(keyStore, provider);

		URL url = new URL("https://59.151.65.97/preSvr");

		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setSSLSocketFactory(socketFactory);

		connection.connect();

	}
}
