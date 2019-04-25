package cn.quickly.project.utility.security;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import cn.quickly.project.utility.codec.Hex;

public class HmacTest {

	byte[] key = "c7ed87c12e784e48983e3bcdc6889dad&0183ce137e4d4170b2ac19d3a9fda677".getBytes();

	byte[] data = "123456".getBytes();

	@Test
	public void testMD5() {

		System.out.println(Hex.upper(Hmac.md5(key, data)));
		System.out.println(Hex.upper(Hmac.md5(key, data)));

	}

	@Test
	public void testSHA1() {

		System.out.println(Hex.upper(Hmac.sha1(key, data)));

	}

	@Test
	public void testSHA256() {

		System.out.println(Hex.upper(Hmac.sha256(key, data)));
		System.out.println(Hex.upper(Hmac.sha256(key, data)));

	}

	@Test
	public void testSHA384() {

		System.out.println(Hex.upper(Hmac.sha384(key, data)));

	}

	@Test
	public void testSHA512() {

		System.out.println(Hex.upper(Hmac.sha512(key, data)));

	}

	@Test
	public void testSM3() {

		Security.addProvider(new BouncyCastleProvider());

		System.out.println(Hex.upper(Hmac.hash("SM3", key, data)));

	}

}
