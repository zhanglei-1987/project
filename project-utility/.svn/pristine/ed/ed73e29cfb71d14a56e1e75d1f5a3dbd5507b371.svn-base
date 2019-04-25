package cn.quickly.project.utility.security;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Before;
import org.junit.Test;

import cn.quickly.project.utility.codec.Base64;
import cn.quickly.project.utility.codec.Hex;
import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.lang.Strings;

public class RsaTest {

	private PublicKey publicKey;

	private PrivateKey privateKey;

	@Before
	public void setup() throws Exception {

		KeyPair keyPair = Rsa.getKeyPair(4096);

		this.publicKey = keyPair.getPublic();

		this.privateKey = keyPair.getPrivate();

	}

	@Test
	public void testGetPublicKey() throws Exception {

		byte[] key = publicKey.getEncoded();

		Printer.println("Length:" + key.length);

		Printer.println("Encoded:" + Hex.upper(key));

		Printer.println("Format:" + publicKey.getFormat());

		System.out.println(publicKey);

		System.out.println(Rsa.getPublicKey(key));

	}

	@Test
	public void testGetPrivateKey() throws Exception {

		byte[] key = privateKey.getEncoded();

		Printer.println("Length:" + key.length);

		Printer.println("Encoded:" + Hex.upper(key));

		Printer.println("Format:" + privateKey.getFormat());

		System.out.println(privateKey);

		System.out.println(Rsa.getPrivateKey(key));

	}

	@Test
	public void testPrivateEncryptAndPublicDecrypt() throws Exception {

		String data = Strings.repeat("1", 1024);

		String encrypt = Base64.encodeBuffer(Rsa.encrypt(data.getBytes(), privateKey));

		System.out.println(encrypt);

		String decrypt = new String(Rsa.decrypt(Base64.decodeBuffer(encrypt), publicKey));

		System.out.println(decrypt);

	}

	@Test
	public void testPublicEncryptAndPrivateDecrypt() throws Exception {

		String data = Strings.repeat("1", 1024);

		String encrypt = Base64.encodeBuffer(Rsa.encrypt(data.getBytes(), publicKey));

		System.out.println(encrypt);

		String decrypt = new String(Rsa.decrypt(Base64.decodeBuffer(encrypt), privateKey));

		System.out.println(decrypt);

	}

	@Test
	public void testESL() throws Exception {

		String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsaB2/kMjC3jfuyzFbQwWKalx9z0DiGuSS3MSMGtNQbsB/cdsjB007j0QTnXMaeYdLgva8tNfNHZriGSGxVnWaTTdGflS1zGCBil9l+VZ/v8Kqyx/V82zozyIX+q70FVDzVbe52Zh0y+pSXCI8XwpNJzboNRQwHZblqbfJEstl4wIDAQAB";

		String data = Base64.encodeBuffer(Rsa.encrypt("zxc111".getBytes(), Rsa.getPublicKey(Base64.decodeBuffer(key))));

		System.out.println(data);
	}

	@Test
	public void testDSL() throws Exception {

		String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCsaB2/kMjC3jfuyzFbQwWKalx9z0DiGuSS3MSMGtNQbsB/cdsjB007j0QTnXMaeYdLgva8tNfNHZriGSGxVnWaTTdGflS1zGCBil9l+VZ/v8Kqyx/V82zozyIX+q70FVDzVbe52Zh0y+pSXCI8XwpNJzboNRQwHZblqbfJEstl4wIDAQAB";

		String rc = "oAEKNSkrEkunLyToAJ7n6ihLGWY8li1WFOpGHWR/7ti1dn0hnvFbag2pS5cVZuIy31eqpOhKULBLRouVIHF1YNAQiT7OGBp+JLfELIYZSZXUSpO5BtQIMhOmNHdlD46iCooAHTsZCvOIno9++n85e9Bf18SRh43jdj8x5G60rkU=";

		System.out.println(new String(Base64.decodeBuffer(rc)));

		String text = new String(Rsa.decrypt(Base64.decodeBuffer(rc), Rsa.getPublicKey(Base64.decodeBuffer(key))));

		System.out.println(text);
	}

}
