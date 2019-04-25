package cn.quickly.project.utility.codec;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class Base24 {

	private static final Base24 BASE24 = new Base24();

	private byte[] table;

	public Base24() {
		this.table = new byte[] { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'M', 'P', 'Q', 'R', 'T', 'V', 'W', 'X', 'Y', '2', '3', '4', '6', '7', '8', '9' };
	}

	public Base24(byte[] table) {
		this.table = table;
	}

	public byte[] encrypt(byte[] content) {
		int length = content.length, size = length * 2;
		byte[] bytes = new byte[size];
		for (int i = 0; i < length; i++) {
			byte n = content[i];
			byte n1 = (byte) ((n & 0xff) >> 4);
			byte n2 = (byte) (n & 0x0f);
			bytes[2 * i] = table[n1];
			bytes[2 * i + 1] = table[23 - n2];
		}
		return bytes;
	}

	public byte[] decrypt(byte[] content) {
		int length = content.length, size = length / 2;
		if (length % 2 == 1) {
			throw new IllegalArgumentException("the wrong encrypt data.");
		}
		byte[] bytes = new byte[size];
		for (int i = 0; i < size; i++) {
			byte n1 = findIndex(content[i * 2]);
			byte n2 = findIndex(content[i * 2 + 1]);

			n2 = (byte) (23 - n2);

			bytes[i] = (byte) ((n1 << 4) | n2);
		}
		return bytes;
	}

	private byte findIndex(byte rec) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == rec)
				return (byte) i;
		}
		return -1;
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
		return new String(BASE24.encrypt(data));
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
		return BASE24.decrypt(message.getBytes());
	}

}
