package cn.quickly.project.utility.security;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import cn.quickly.project.utility.function.BinarySupplier;
import cn.quickly.project.utility.lang.Exceptions;

public final class Asymmetry {

	private Asymmetry() {
		throw new UnsupportedOperationException();
	}

	public static int getKeySize(PublicKey publicKey) {

		if (publicKey instanceof RSAPublicKey) {

			return ((RSAPublicKey) publicKey).getModulus().toString(2).length();

		} else if (publicKey instanceof DSAPublicKey) {

			return ((DSAPublicKey) publicKey).getY().toString(2).length();

		}

		return 0;
	}

	public static int getKeySize(PrivateKey privateKey) {

		if (privateKey instanceof RSAPrivateKey) {
			return ((RSAPrivateKey) privateKey).getModulus().toString(2).length();
		} else if (privateKey instanceof DSAPublicKey) {
			return ((DSAPublicKey) privateKey).getY().toString(2).length();
		}

		return 0;
	}

	public static KeyPair getKeyPair(String algorithm, int size) {
		return getKeyPair(algorithm, size, Providers.detect(Providers.TYPE_KEY_PAIR_GENERATOR, algorithm));
	}

	public static KeyPair getKeyPair(String algorithm, int size, Provider provider) {

		try {

			KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm, provider);
			generator.initialize(size);
			return generator.generateKeyPair();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static PrivateKey getPrivateKey(String algorithm, byte[] key) {
		return getPrivateKey(algorithm, key, Providers.detect(Providers.TYPE_KEY_FACTORY, algorithm));
	}

	public static PrivateKey getPrivateKey(String algorithm, byte[] key, Provider provider) {

		try {

			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(algorithm, provider);
			return keyFactory.generatePrivate(pkcs8KeySpec);

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static PrivateKey getPrivateKey(KeyStore keyStore, String password) {
		return (PrivateKey) KeyStores.key(keyStore, password);
	}

	public static PublicKey getPublicKey(String algorithm, byte[] key) {
		return getPublicKey(algorithm, key, Providers.detect(Providers.TYPE_KEY_FACTORY, algorithm));
	}

	public static PublicKey getPublicKey(String algorithm, byte[] key, Provider provider) {

		try {

			X509EncodedKeySpec X509KeySpec = new X509EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(algorithm, provider);
			return keyFactory.generatePublic(X509KeySpec);

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static byte[] encrypt(String algorithm, byte[] data, PublicKey publicKey, int blockSize) throws Exception {
		return encrypt(algorithm, data, publicKey, blockSize, Providers.detect(Providers.TYPE_CIPHER, algorithm));
	}

	public static byte[] encrypt(String algorithm, byte[] data, PublicKey publicKey, int blockSize, Provider provider) throws Exception {

		return encrypt(algorithm, data, publicKey, (d, i) -> blockSize, provider);

	}

	public static byte[] encrypt(String algorithm, byte[] data, PublicKey publicKey, BinarySupplier<byte[], Integer, Integer> blockSizeSuppiler,
			Provider provider) throws Exception {

		Cipher cipher = Cipher.getInstance(algorithm, provider);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		for (int i = 0, end = data.length; i < end;) {

			int blockSize = blockSizeSuppiler.get(data, i);

			int offset = end - i;

			if (offset > blockSize) {
				offset = blockSize;
			}

			out.write(cipher.doFinal(data, i, offset));

			i += blockSize;
		}

		return out.toByteArray();

	}

	public static byte[] encrypt(String algorithm, byte[] data, PrivateKey privateKey, int blockSize) throws Exception {
		return encrypt(algorithm, data, privateKey, blockSize, Providers.detect(Providers.TYPE_CIPHER, algorithm));
	}

	public static byte[] encrypt(String algorithm, byte[] data, PrivateKey privateKey, int blockSize, Provider provider) throws Exception {

		return encrypt(algorithm, data, privateKey, (d, i) -> blockSize, provider);

	}

	public static byte[] encrypt(String algorithm, byte[] data, PrivateKey privateKey, BinarySupplier<byte[], Integer, Integer> blockSizeSuppiler,
			Provider provider) throws Exception {

		Cipher cipher = Cipher.getInstance(algorithm, provider);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		for (int i = 0, end = data.length; i < end;) {

			int blockSize = blockSizeSuppiler.get(data, i);

			int offset = end - i;

			if (offset > blockSize) {
				offset = blockSize;
			}

			out.write(cipher.doFinal(data, i, offset));

			i += blockSize;

		}

		return out.toByteArray();

	}

	public static byte[] decrypt(String algorithm, byte[] data, PublicKey publicKey, int blockSize) throws Exception {
		return decrypt(algorithm, data, publicKey, blockSize, Providers.detect(Providers.TYPE_CIPHER, algorithm));
	}

	public static byte[] decrypt(String algorithm, byte[] data, PublicKey publicKey, int blockSize, Provider provider) throws Exception {

		return decrypt(algorithm, data, publicKey, (d, i) -> blockSize, provider);

	}

	public static byte[] decrypt(String algorithm, byte[] data, PublicKey publicKey, BinarySupplier<byte[], Integer, Integer> blockSizeSuppiler,
			Provider provider) throws Exception {

		Cipher cipher = Cipher.getInstance(algorithm, provider);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		for (int i = 0, end = data.length; i < end;) {

			int blockSize = blockSizeSuppiler.get(data, i);

			int offset = end - i;

			if (offset > blockSize) {
				offset = blockSize;
			}

			out.write(cipher.doFinal(data, i, offset));

			i += blockSize;

		}

		return out.toByteArray();

	}

	public static byte[] decrypt(String algorithm, byte[] data, PrivateKey privateKey, int blockSize) throws Exception {
		return decrypt(algorithm, data, privateKey, blockSize, Providers.detect(Providers.TYPE_CIPHER, algorithm));
	}

	public static byte[] decrypt(String algorithm, byte[] data, PrivateKey privateKey, int blockSize, Provider provider) throws Exception {

		return decrypt(algorithm, data, privateKey, (d, i) -> blockSize, provider);

	}

	public static byte[] decrypt(String algorithm, byte[] data, PrivateKey privateKey, BinarySupplier<byte[], Integer, Integer> blockSizeSuppiler,
			Provider provider) throws Exception {

		Cipher cipher = Cipher.getInstance(algorithm, provider);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		for (int i = 0, end = data.length; i < end;) {

			int blockSize = blockSizeSuppiler.get(data, i);

			int offset = end - i;

			if (offset > blockSize) {
				offset = blockSize;
			}

			out.write(cipher.doFinal(data, i, offset));

			i += blockSize;
		}

		return out.toByteArray();

	}

}
