package cn.quickly.project.utility.security;

import java.security.KeyPair;
import java.security.PrivateKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import cn.quickly.project.utility.codec.Base64;

public class AsymmetryTest {

	byte[] data = "123".getBytes();

	@Test
	public void testSM2() throws Exception {

		BouncyCastleProvider provider = new BouncyCastleProvider();

		KeyPair keyPair = Asymmetry.getKeyPair("SM2", 256, provider);

		PrivateKey privateKey = keyPair.getPrivate();

		System.out.println(Base64.encodeBuffer(Asymmetry.encrypt("SM2", data, privateKey, 137, provider)));

	}

}
