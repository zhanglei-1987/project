package cn.quickly.project.utility.security;

import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cn.quickly.project.utility.lang.Exceptions;

public final class Symmetry {

	private Symmetry() {
		throw new UnsupportedOperationException();
	}

	public static SecretKey getSecretKey(String algorithm, byte[] key) {
		return new SecretKeySpec(key, algorithm);
	}

	public static SecretKey getRandomKey(String algorithm, int size) {

		return getRandomKey(algorithm, size, Providers.detect(Providers.TYPE_KEY_GENERATOR, algorithm));

	}

	public static SecretKey getRandomKey(String algorithm, int size, String provider) {
		return getRandomKey(algorithm, size, Security.getProvider(provider));
	}

	public static SecretKey getRandomKey(String algorithm, int size, Provider provider) {

		try {

			KeyGenerator generator = KeyGenerator.getInstance(algorithm, provider);
			generator.init(size);
			return generator.generateKey();

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static byte[] encrypt(String algorithm, byte[] data, SecretKey secretKey) {

		return encrypt(algorithm, data, secretKey, Providers.detect(Providers.TYPE_CIPHER, algorithm));

	}

	public static byte[] encrypt(String algorithm, byte[] data, SecretKey secretKey, String provider) {

		return encrypt(algorithm, data, secretKey, Security.getProvider(provider));

	}

	public static byte[] encrypt(String algorithm, byte[] data, SecretKey secretKey, Provider provider) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm, provider);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(data);
		} catch (Exception e) {

			throw Exceptions.argument(e);

		}
	}

	public static byte[] decrypt(String algorithm, byte[] data, SecretKey secretKey) {

		return decrypt(algorithm, data, secretKey, Providers.detect(Providers.TYPE_CIPHER, algorithm));

	}

	public static byte[] decrypt(String algorithm, byte[] data, SecretKey secretKey, String provider) {

		return decrypt(algorithm, data, secretKey, Security.getProvider(provider));

	}

	public static byte[] decrypt(String algorithm, byte[] data, SecretKey secretKey, Provider provider) {
		try {
			Cipher cipher = Cipher.getInstance(algorithm, provider);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return cipher.doFinal(data);
		} catch (Exception e) {

			throw Exceptions.argument(e);

		}
	}

}
