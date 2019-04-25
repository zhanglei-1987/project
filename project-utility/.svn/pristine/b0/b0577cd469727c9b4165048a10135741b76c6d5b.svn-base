package cn.quickly.project.utility.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class Base58 {

	private static final Base58 BASE58 = new Base58();

	private int[] indexs = new int[128];

	private byte[] alphabet;

	public Base58() {
		this.alphabet = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".getBytes();
		setAlphabet(alphabet);
	}

	public Base58(byte[] alphabet) {
		this.alphabet = alphabet;
		setAlphabet(alphabet);
	}

	public byte[] encrypt(byte[] content) {
		if (content.length == 0) {
			return new byte[0];
		}
		// Count leading zeroes.
		int zeroCount = 0;
		while (zeroCount < content.length && content[zeroCount] == 0) {
			++zeroCount;
		}
		// The actual encoding.
		byte[] temp = new byte[content.length * 2];
		int j = temp.length;

		int startAt = zeroCount;
		while (startAt < content.length) {
			byte mod = divmod58(content, startAt);
			if (content[startAt] == 0) {
				++startAt;
			}
			temp[--j] = (byte) alphabet[mod];
		}

		// Strip extra '1' if there are some after decoding.
		while (j < temp.length && temp[j] == alphabet[0]) {
			++j;
		}
		// Add as many leading '1' as there were leading zeros.
		while (--zeroCount >= 0) {
			temp[--j] = (byte) alphabet[0];
		}

		return copyOfRange(temp, j, temp.length);
	}

	public byte[] decrypt(byte[] content) {
		if (content.length == 0) {
			return new byte[0];
		}
		byte[] input58 = new byte[content.length];
		// Transform the String to a base58 byte sequence
		for (int i = 0; i < content.length; ++i) {
			byte c = content[i];

			int digit58 = -1;
			if (c >= 0 && c < 128) {
				digit58 = indexs[c];
			}
			if (digit58 < 0) {
				throw new IllegalArgumentException("Illegal character " + c + " at " + i);
			}

			input58[i] = (byte) digit58;
		}
		// Count leading zeroes
		int zeroCount = 0;
		while (zeroCount < input58.length && input58[zeroCount] == 0) {
			++zeroCount;
		}
		// The encoding
		byte[] temp = new byte[content.length];
		int j = temp.length;

		int startAt = zeroCount;
		while (startAt < input58.length) {
			byte mod = divmod256(input58, startAt);
			if (input58[startAt] == 0) {
				++startAt;
			}

			temp[--j] = mod;
		}
		// Do no add extra leading zeroes, move j to first non null byte.
		while (j < temp.length && temp[j] == 0) {
			++j;
		}

		return copyOfRange(temp, j - zeroCount, temp.length);
	}

	public void encrypt(InputStream in, OutputStream out) throws IOException {
		throw new RuntimeException("not support");
	}

	public void decrypt(InputStream in, OutputStream out) throws IOException {
		throw new RuntimeException("not support");
	}

	private void setAlphabet(byte[] alphabet) {
		this.alphabet = alphabet;

		for (int i = 0; i < indexs.length; i++) {
			indexs[i] = -1;
		}
		for (int i = 0; i < alphabet.length; i++) {
			indexs[alphabet[i]] = i;
		}
	}

	// number -> number / 58, returns number % 58
	private byte divmod58(byte[] number, int startAt) {
		int remainder = 0;
		for (int i = startAt; i < number.length; i++) {
			int digit256 = (int) number[i] & 0xFF;
			int temp = remainder * 256 + digit256;

			number[i] = (byte) (temp / 58);

			remainder = temp % 58;
		}

		return (byte) remainder;
	}

	//
	// number -> number / 256, returns number % 256
	//
	private byte divmod256(byte[] number58, int startAt) {
		int remainder = 0;
		for (int i = startAt; i < number58.length; i++) {
			int digit58 = (int) number58[i] & 0xFF;
			int temp = remainder * 58 + digit58;

			number58[i] = (byte) (temp / 256);

			remainder = temp % 256;
		}

		return (byte) remainder;
	}

	private byte[] copyOfRange(byte[] source, int from, int to) {
		byte[] range = new byte[to - from];
		System.arraycopy(source, from, range, 0, range.length);
		return range;
	}

	public static String encode(String message) {
		return encode(message, Charset.defaultCharset().name());
	}

	public static String encode(String message, String charset) {
		try {
			return encodeBuffer(message.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String encodeBuffer(byte[] data) {
		return new String(BASE58.encrypt(data));
	}

	public static String decode(String message) {
		return decode(message, Charset.defaultCharset().name());
	}

	public static String decode(String message, String charset) {
		try {
			return new String(decodeBuffer(message), charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] decodeBuffer(String message) {
		return BASE58.decrypt(message.getBytes());
	}

}
