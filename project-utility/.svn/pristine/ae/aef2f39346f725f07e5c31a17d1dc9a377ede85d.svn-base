package cn.quickly.project.utility.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.thirdparty.Ognls;

public final class Buffers {

	private Buffers() {
		throw new UnsupportedOperationException();
	}

	public static void head(Buffer buffer) {

		if (buffer.limit() > 0) {

			buffer.position(0);

		} else {

			buffer.flip();

		}

	}

	public static void tail(Buffer buffer) {

		if (buffer.limit() < buffer.capacity()) {

			buffer.position(buffer.limit());
			buffer.limit(buffer.capacity());

		}

	}

	public static void release(ByteBuffer buffer) {

		if (buffer.isDirect()) {

			Ognls.execute("buffer.cleaner().clean()", Maps.asMap("buffer", buffer));

		} else {

			buffer.clear();

		}

	}

	public static byte[] bytes(ByteBuffer buffer) {
		return bytes(buffer, 0, buffer.position());
	}

	public static byte[] bytes(ByteBuffer buffer, int start, int end) {

		head(buffer);

		buffer.position(start);

		int length = buffer.limit();

		if (end < 0) {
			end = length + end;
		}

		byte[] data = new byte[end - start];

		buffer.get(data);

		return data;
	}

	public static char[] chars(CharBuffer buffer) {
		return chars(buffer, 0, buffer.position());
	}

	public static char[] chars(CharBuffer buffer, int start, int end) {

		head(buffer);

		buffer.position(start);

		int length = buffer.limit();

		if (end < 0) {
			end = length + end;
		}

		char[] data = new char[end - start];

		buffer.get(data);

		return data;
	}

	public static void append(ByteBuffer buffer, Object... datas) {

		tail(buffer);

		for (Object data : datas) {

			if (data instanceof Character) {
				buffer.putChar((Character) data);
			} else if (data instanceof Byte) {
				buffer.put((Byte) data);
			} else if (data instanceof char[]) {
				for (char c : (char[]) data) {
					buffer.putChar(c);
				}
			} else if (data instanceof byte[]) {
				buffer.put((byte[]) data);
			} else if (data instanceof String) {
				append(buffer, ((String) data).toCharArray());
			}

		}

	}

}
