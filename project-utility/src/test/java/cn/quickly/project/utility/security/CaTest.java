package cn.quickly.project.utility.security;

import java.io.File;
import java.security.KeyStore;
import java.security.KeyStore.PasswordProtection;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import org.junit.Test;

import cn.quickly.project.utility.codec.Base64;
import cn.quickly.project.utility.collection.Enumerations;

public class CaTest {

	private String algorithm = Signatures.ALGORITHM_SHA256_WITH_RSA;

	private byte[] data = "123456".getBytes();

	@Test
	public void scan() throws Exception {

		KeyStore keyStore = KeyStores.load("PKCS12", new File("d:/ca/测试1.p12"), "123456");

		for (String alias : Enumerations.asSet(keyStore.aliases())) {

			if (keyStore.isCertificateEntry(alias)) {

				System.out.println(keyStore.getCertificate(alias).getClass());

			} else {

				System.out.println(keyStore.getKey(alias, "123456".toCharArray()).getClass());

			}

			System.out.println(keyStore.getEntry(alias, new PasswordProtection("123456".toCharArray())));

		}

	}

	@Test
	public void testSign() throws Exception {

		KeyStore keyStore = KeyStores.load("PKCS12", new File("d:/ca/测试1.p12"), "123456");

		PrivateKey privateKey = (PrivateKey) keyStore.getKey("测试1", "123456".toCharArray());

		byte[] sign = Signatures.sign(algorithm, data, privateKey);

		System.out.println(Base64.encodeBuffer(sign));

	}

	@Test
	public void testVerify() throws Exception {

		String sign = "ap7DsyDXPL1m6h3px8iUVNhz0rlCblZ9ukuW9+GQEhtWgFpn6iFqzo2YZUt6fbDCrvjz+pqmoGcSitZijImEYJF6A4q77pTpZ0w6K8EMU4BUGM9P/koP3GJZTDabWGW3cm/EjskTnOe1ag5qYwk92bz+IqBuYI8r1HhOMwOJteOdyu6mGn7dGJRNxc9h5/h8tn+iX2ibfl1l/dIGYgVC3HFzc4JsnhiyJQA9pqhn57TdnNTtyj8AVYe3Cjc8PbEW69r6wHkzvmF/XqSPml+KzPAn03zIm98/9Kz8pjyVOvCIK5hGDXojbv1SRZ0nR3/UBl2L8Qc23KCZxXK7ehWgYg==";

		// Certificate certificate = Certificates.load("X.509", "d:/ca/测试.cer");

		Certificate certificate = Certificates.load("X.509", "file://d:/ca/UserCert.cer");

		boolean result = Signatures.verify(algorithm, data, Base64.decodeBuffer(sign), certificate);

		System.out.println(result);
	}
}
