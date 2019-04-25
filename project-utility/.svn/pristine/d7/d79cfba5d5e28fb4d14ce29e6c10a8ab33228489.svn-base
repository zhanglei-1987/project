package cn.quickly.project.utility.codec;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class Base64 {

	private static final Base64 BASE64 = new Base64();

	public byte[] table;

	public Base64() {
		this.table = new byte[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0',
				'1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/', '=' };
	}

	public Base64(byte[] table) {
		this.table = table;
	}

	public byte[] encrypt(byte[] content) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = 0, type = 2; i < content.length; i++) {
			switch (type) {
			case 2: {
				out.write(table[((content[i] & 0xff) >> 2) & 0x3f]);
				if (i == content.length - 1) {
					out.write(table[((content[i] & 0xff) << 4) & 0x3f]);
					out.write(table[64]);
					out.write(table[64]);
				} else
					type = 4;
				break;
			}
			case 4: {
				out.write(table[(((content[i - 1] & 0xff) << 4) & 0x3f) + (((content[i] & 0xff) >> 4) & 0x3f)]);
				if (i == content.length - 1) {
					out.write(table[(((content[i] & 0xff) << 2)) & 0x3f]);
					out.write(table[64]);
				} else
					type = 6;
				break;
			}
			case 6: {
				out.write(table[(((content[i - 1] & 0xff) << 2) & 0x3f) + (((content[i] & 0xff) >> 6) & 0x3f)]);
				out.write(table[(content[i] & 0xff) & 0x3f]);
				type = 2;
				break;
			}
			default:
				break;
			}
		}
		return out.toByteArray();
	}

	public byte[] decrypt(byte[] content) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		for (int i = 0, type = 2; i < content.length; i++) {
			int byteIndex = findIndex(content[i]);
			int nextIndex = 0;
			if (i + 1 < content.length)
				nextIndex = findIndex(content[i + 1]);

			switch (type) {
			case 2: {
				buffer.write((byte) ((byteIndex << 2) & 0xff) + (byte) ((nextIndex >> 4) & 0xff));
				type = 4;
				break;
			}
			case 4: {
				if (nextIndex == 64) {
					type = -1;
				} else {
					buffer.write((byte) ((byteIndex << 4) & 0xff) + (byte) ((nextIndex >> 2) & 0xff));
					type = 6;
				}
				break;
			}
			case 6: {
				if (nextIndex == 64) {
					type = -1;
				} else {
					buffer.write((byte) ((byteIndex << 6) & 0xff) + (byte) (nextIndex & 0xff));
					type = 0;
				}
				break;
			}
			case 0: {
				type = 2;
			}
			default: {
				break;
			}
			}
		}
		return buffer.toByteArray();
	}

	private int findIndex(byte rec) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == rec)
				return i;
		}
		return 64;
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
		return new String(BASE64.encrypt(data));
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
		return BASE64.decrypt(message.getBytes());
	}

}
