package cn.quickly.project.utility.security;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import cn.quickly.project.utility.codec.Base64;
import cn.quickly.project.utility.lang.Assert;

public class SignaturesTest {

	protected void testSignAndVerify(KeyPair keyPair, String algorithm) throws Exception {

		PublicKey publicKey = keyPair.getPublic();

		PrivateKey privateKey = keyPair.getPrivate();

		byte[] data = "123456".getBytes();

		byte[] sign = Signatures.sign(algorithm, data, privateKey);

		System.out.println(Base64.encodeBuffer(sign));

		boolean result = Signatures.verify(algorithm, data, sign, publicKey);

		Assert.isTrue(result);

	}

	@Test
	public void testMD5withRSA() throws Exception {
		testSignAndVerify(Asymmetry.getKeyPair("RSA", 1024), Signatures.ALGORITHM_MD5_WITH_RSA);
	}

	@Test
	public void testSHA1withRSA() throws Exception {
		testSignAndVerify(Asymmetry.getKeyPair("RSA", 1024), Signatures.ALGORITHM_SHA1_WITH_RSA);
	}

	@Test
	public void testSM3withSM2() throws Exception {

		BouncyCastleProvider provider = new BouncyCastleProvider();

		Security.addProvider(provider);

		testSignAndVerify(Asymmetry.getKeyPair("SM2", 256, provider), "SM3withSM2");

	}

}
