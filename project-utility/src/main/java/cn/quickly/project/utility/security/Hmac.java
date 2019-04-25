package cn.quickly.project.utility.security;

import java.io.InputStream;

import javax.crypto.SecretKey;

import cn.quickly.project.utility.lang.Strings;

public final class Hmac {

	private Hmac() {
		throw new UnsupportedOperationException();
	}

	public static SecretKey getRandomKey(String algorithm, int size) {

		return Macs.getRandomKey(Strings.concat("Hmac", algorithm), size);

	}

	public static SecretKey getSecretKey(String algorithm, byte[] key) {

		return Macs.getSecretKey(Strings.concat("Hmac", algorithm), key);

	}

	public static byte[] hash(String algorithm, byte[] data, byte[] key) {

		return Macs.mac(Strings.concat("Hmac", algorithm), data, key);

	}

	public static byte[] hash(String algorithm, InputStream in, byte[] key) {

		return Macs.mac(Strings.concat("Hmac", algorithm), in, key);

	}

	public static byte[] md5(byte[] data, byte[] key) {

		return hash("MD5", data, key);

	}

	public static byte[] md5(InputStream in, byte[] key) {

		return hash("MD5", in, key);

	}

	public static byte[] sha1(byte[] data, byte[] key) {

		return hash("SHA1", data, key);

	}

	public static byte[] sha1(InputStream in, byte[] key) {

		return hash("SHA1", in, key);

	}

	public static byte[] sha256(byte[] data, byte[] key) {

		return hash("SHA256", data, key);

	}

	public static byte[] sha256(InputStream in, byte[] key) {

		return hash("SHA256", in, key);

	}

	public static byte[] sha384(byte[] data, byte[] key) {

		return hash("SHA384", data, key);

	}

	public static byte[] sha384(InputStream in, byte[] key) {

		return hash("SHA384", in, key);

	}

	public static byte[] sha512(byte[] data, byte[] key) {

		return hash("SHA512", data, key);

	}

	public static byte[] sha512(InputStream in, byte[] key) {

		return hash("SHA512", in, key);

	}

}
