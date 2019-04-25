package cn.quickly.project.utility.codec;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class Base32 {

	private byte[] encodeTable = {
			//
			'A', 'B', 'C', 'D', 'E', 'F', 'G', //
			'H', 'I', 'J', 'K', 'L', 'M', 'N',//
			'O', 'P', 'Q', 'R', 'S', 'T',//
			'U', 'V', 'W', 'X', 'Y', 'Z',//
			'2', '3', '4', '5', '6', '7' //
	};

	private static final int[] decodeTable = {
			//
			0xFF, 0xFF, 0x1A, 0x1B, 0x1C, 0x1D, 0x1E, 0x1F, // '0', '1', '2', '3', '4', '5', '6', '7'
			0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, // '8', '9', ':', ';', '<', '=', '>', '?'
			0xFF, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, // '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G'
			0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, // 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'
			0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, // 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W'
			0x17, 0x18, 0x19, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, // 'X', 'Y', 'Z', '[', '\', ']', '^', '_'
			0xFF, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, // '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g'
			0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, // 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o'
			0x0F, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, // 'p', 'q', 'r', 's', 't', 'u', 'v', 'w'
			0x17, 0x18, 0x19, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF // 'x', 'y', 'z', '{', '|', '}', '~', 'DEL'
	};

	private static final Base32 BASE32 = new Base32();

	private Base32() {
	}

	public byte[] encrypt(byte[] content) {
		int i = 0, index = 0, digit = 0;
		int currByte, nextByte;
		ByteArrayOutputStream out = new ByteArrayOutputStream((content.length + 7) * 8 / 5);

		while (i < content.length) {
			currByte = (content[i] >= 0) ? content[i] : (content[i] + 256); // unsign

			/* Is the current digit going to span a byte boundary? */
			if (index > 3) {
				if ((i + 1) < content.length)
					nextByte = (content[i + 1] >= 0) ? content[i + 1] : (content[i + 1] + 256);
				else
					nextByte = 0;

				digit = currByte & (0xFF >> index);
				index = (index + 5) % 8;
				digit <<= index;
				digit |= nextByte >> (8 - index);
				i++;
			} else {
				digit = (currByte >> (8 - (index + 5))) & 0x1F;
				index = (index + 5) % 8;
				if (index == 0)
					i++;
			}
			out.write(encodeTable[digit]);
		}

		return out.toByteArray();
	}

	public byte[] decrypt(byte[] content) {
		int i, index, lookup, offset, digit;
		byte[] bytes = new byte[content.length * 5 / 8];

		if (bytes.length == 0) {
			return (bytes);
		}

		for (i = 0, index = 0, offset = 0; i < content.length; i++) {
			lookup = content[i] - '0';

			/* Skip chars outside the lookup table */
			if (lookup < 0 || lookup >= decodeTable.length)
				continue;

			digit = decodeTable[lookup];

			/* If this digit is not in the table, ignore it */
			if (digit == 0xFF)
				continue;

			if (index <= 3) {
				index = (index + 5) % 8;
				if (index == 0) {
					bytes[offset] |= digit;
					offset++;
					if (offset >= bytes.length)
						break;
				} else
					bytes[offset] |= digit << (8 - index);
			} else {
				index = (index + 5) % 8;
				bytes[offset] |= (digit >>> index);
				offset++;

				if (offset >= bytes.length)
					break;
				bytes[offset] |= digit << (8 - index);
			}
		}
		return bytes;
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
		return new String(BASE32.encrypt(data));
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
		return BASE32.decrypt(message.getBytes());
	}

}
