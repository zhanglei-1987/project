package cn.quickly.project.utility.security;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public final class Rsa {

	private static final String ALGORITHM = "RSA";

	private Rsa() {
		throw new UnsupportedOperationException();
	}

	public static int getKeySize(PublicKey publicKey) {
		return Asymmetry.getKeySize(publicKey);
	}

	public static int getKeySize(PrivateKey privateKey) {
		return Asymmetry.getKeySize(privateKey);
	}

	public static KeyPair getKeyPair(int size) throws Exception {

		return Asymmetry.getKeyPair(ALGORITHM, size);

	}

	public static PrivateKey getPrivateKey(byte[] key) throws Exception {

		return Asymmetry.getPrivateKey(ALGORITHM, key);

	}

	public static PublicKey getPublicKey(byte[] key) throws Exception {

		return Asymmetry.getPublicKey(ALGORITHM, key);

	}

	public static byte[] encrypt(byte[] data, PublicKey publicKey) throws Exception {

		return Asymmetry.encrypt(ALGORITHM, data, publicKey, getKeySize(publicKey) / 8 - 11);

	}

	public static byte[] encrypt(byte[] data, PrivateKey privateKey) throws Exception {

		return Asymmetry.encrypt(ALGORITHM, data, privateKey, getKeySize(privateKey) / 8 - 11);

	}

	public static byte[] decrypt(byte[] data, PublicKey publicKey) throws Exception {

		return Asymmetry.decrypt(ALGORITHM, data, publicKey, getKeySize(publicKey) / 8);

	}

	public static byte[] decrypt(byte[] data, PrivateKey privateKey) throws Exception {

		return Asymmetry.decrypt(ALGORITHM, data, privateKey, getKeySize(privateKey) / 8);

	}

}
