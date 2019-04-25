package cn.quickly.project.utility.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.Provider;

import cn.quickly.project.utility.io.Serialization;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class MessageDigests {

	private MessageDigests() {
		throw new UnsupportedOperationException();
	}

	public static byte[] object(String algorithm, Object data) {
		return object(algorithm, data, Providers.detect(Providers.TYPE_MESSAGE_DIGEST, algorithm));
	}

	public static byte[] object(String algorithm, Object data, Provider provider) {
		return bytes(algorithm, Serialization.serialize(data), provider);
	}

	public static byte[] bytes(String algorithm, byte[] data) {
		return bytes(algorithm, data, Providers.detect(Providers.TYPE_MESSAGE_DIGEST, algorithm));
	}

	public static byte[] bytes(String algorithm, byte[] data, Provider provider) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm, provider);
			return md.digest(data);
		} catch (Exception e) {
			throw Exceptions.argument(e);
		}
	}

	public static byte[] file(String algorithm, File file) {
		return file(algorithm, file, Providers.detect(Providers.TYPE_MESSAGE_DIGEST, algorithm));
	}

	public static byte[] file(String algorithm, File file, Provider provider) {
		try {
			return stream(algorithm, new FileInputStream(file), provider);
		} catch (IOException e) {
			throw Exceptions.argument(e);
		}
	}

	public static byte[] stream(String algorithm, InputStream in) {
		return stream(algorithm, in, Providers.detect(Providers.TYPE_MESSAGE_DIGEST, algorithm));
	}

	public static byte[] stream(String algorithm, InputStream in, Provider provider) {

		try {

			MessageDigest md = MessageDigest.getInstance(algorithm, provider);

			byte[] buffer = new byte[10240];

			int count = -1;

			while ((count = in.read(buffer)) > 0) {
				md.update(buffer, 0, count);
			}

			Quiet.close(in);

			return md.digest();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}
	}

}
