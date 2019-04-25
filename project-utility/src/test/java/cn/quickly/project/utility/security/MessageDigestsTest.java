package cn.quickly.project.utility.security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import cn.quickly.project.utility.codec.Hex;
import cn.quickly.project.utility.lang.Strings;

public class MessageDigestsTest {

	@Test
	public void testMD5() throws Exception {

		byte[] data = Strings.getBytes("dsafds", "UTF-8");

		byte[] hash = MessageDigests.bytes("MD5", data);

		System.out.println(Hex.upper(hash));

	}

	@Test
	public void testSHA1() throws Exception {

		byte[] data = Strings.getBytes("dsafds", "GBK");

		byte[] hash = MessageDigests.bytes("SHA1", data);

		System.out.println(Hex.upper(hash));

	}

	@Test
	public void testSM3() throws Exception {

		byte[] data = Strings.getBytes("dsafds", "GBK");

		byte[] hash = MessageDigests.bytes("SM3", data, new BouncyCastleProvider());

		System.out.println(Hex.upper(hash));

	}

}
