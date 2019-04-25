package cn.quickly.project.utility.security;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import cn.quickly.project.utility.codec.Hex;

public class MacsTest {

	byte[] data = "123456".getBytes();

	@Test
	public void testAes() {

		Security.addProvider(new BouncyCastleProvider());

		byte[] key = Macs.getRandomKey("AES-GMAC", 128).getEncoded();

		System.out.println(Hex.upper(Macs.mac("AES-GMAC", key, data)));

	}

	@Test
	public void testSM4() {

		Security.addProvider(new BouncyCastleProvider());

		byte[] key = Macs.getRandomKey("SM4-CMAC", 128).getEncoded();

		System.out.println(Hex.upper(Macs.mac("SM4-CMAC", key, data)));

	}

}
