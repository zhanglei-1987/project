package cn.quickly.project.utility.security;

import java.io.InputStream;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class Macs {

	private Macs() {
		throw new UnsupportedOperationException();
	}

	public static SecretKey getRandomKey(String algorithm, int size) {

		return Symmetry.getRandomKey(algorithm, size);

	}

	public static SecretKey getSecretKey(String algorithm, byte[] key) {

		return Symmetry.getSecretKey(algorithm, key);

	}

	public static byte[] mac(String algorithm, byte[] data, byte[] key) {

		try {

			Mac mac = Mac.getInstance(algorithm);
			mac.init(new SecretKeySpec(key, algorithm));

			return mac.doFinal(data);

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static byte[] mac(String algorithm, InputStream in, byte[] key) {

		try {

			Mac mac = Mac.getInstance(algorithm);
			mac.init(new SecretKeySpec(key, algorithm));

			byte[] buffer = new byte[10240];

			int count = -1;

			while ((count = in.read(buffer)) > 0) {
				mac.update(buffer, 0, count);
			}

			return mac.doFinal();

		} catch (Exception e) {

			throw Exceptions.argument(e);

		} finally {

			Quiet.close(in);

		}

	}

}
