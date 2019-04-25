package cn.quickly.project.utility.security;

import java.security.Security;

import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import cn.quickly.project.utility.codec.Base64;
import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Strings;

public class SymmetryTest {

	String text = Strings.repeat("123456", 5000);

	void testAlgorithm(String algorithm, String model, int keySize) {

		SecretKey secretKey = Symmetry.getRandomKey(algorithm, keySize);

		System.out.println(Base64.encodeBuffer(secretKey.getEncoded()));

		algorithm = algorithm + model;

		byte[] data = text.getBytes();

		String body = Base64.encodeBuffer(Symmetry.encrypt(algorithm, data, secretKey));

		System.out.println(body);

		data = Base64.decodeBuffer(body);

		body = new String(Symmetry.decrypt(algorithm, data, secretKey));

		System.out.println(body);

		Assert.isTrue(body.equals(text));

	}

	@Test
	public void testAES() {
		testAlgorithm("AES", "/ECB/PKCS5Padding", 256);
	}

	@Test
	public void testDES() {
		testAlgorithm("DES", "/ECB/PKCS5Padding", 56);
	}

	@Test
	public void testDESede() {
		testAlgorithm("DESede", "/ECB/PKCS5Padding", 168);
	}

	@Test
	public void testRC4() {
		testAlgorithm("RC4", "/ECB/PKCS5Padding", 56);
	}

	@Test
	public void testBlowfish() {
		testAlgorithm("Blowfish", "/ECB/PKCS5Padding", 56);
	}

	@Test
	public void testSM4() {

		Security.addProvider(new BouncyCastleProvider());

		testAlgorithm("SM4", "/ECB/PKCS5Padding", 128);
	}

}
