package cn.quickly.project.utility.net;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStore.SecretKeyEntry;
import java.security.cert.Certificate;

import javax.crypto.SecretKey;

import org.junit.Before;
import org.junit.Test;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.security.KeyStores;
import cn.quickly.project.utility.security.Symmetry;

public class KeyStoresTest {

	String algorithm = "JKS";

	String password = "123456";

	KeyStore keyStore;

	@Before
	public void setup() {

		InputStream in = StreamSeeker.classpath("/mock.jks");

		keyStore = KeyStores.load(algorithm, in, password);

	}

	@Test
	public void testLoad() throws Exception {

		Printer.println(keyStore);

		Printer.println(keyStore.aliases());

	}

	@Test
	public void testKey() throws Exception {

		Key key = keyStore.getKey("fft", password.toCharArray());

		Printer.println(key.getClass());

		Printer.println(key);

	}

	@Test
	public void testCertificate() throws Exception {

		Certificate certificate = keyStore.getCertificate("fft");

		Printer.println(certificate.getClass());

		Printer.println(certificate);

	}

	@Test
	public void testEntry() throws Exception {

		PrivateKeyEntry entry = (PrivateKeyEntry) keyStore.getEntry("fft", new PasswordProtection(password.toCharArray()));

		Printer.println(entry.getClass());

		Printer.println(entry.getPrivateKey());

		Printer.println(entry.getCertificate());

	}

	@Test
	public void testStore() throws Exception {

		SecretKey secretKey = Symmetry.getRandomKey("AES", 256);

		SecretKeyEntry entry = new SecretKeyEntry(secretKey);

		keyStore.setEntry("aes", entry, new PasswordProtection(password.toCharArray()));

		FileOutputStream out = new FileOutputStream("src/test/resources/mock-aes.jks");

		keyStore.store(out, password.toCharArray());

		out.flush();
		out.close();

	}

}
