package cn.quickly.project.utility.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.junit.Test;

public class BuffersTest {

	@Test
	public void testRelease() {

		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		buffer.putChar('a');

		buffer.flip();

		System.out.println(buffer.getChar());

		Buffers.release(buffer);

		buffer.put((byte) 'a');

		buffer.flip();

		System.out.println(buffer.getChar());

	}

	@Test
	public void testByteBuffer() {

		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		System.out.println(buffer.remaining());

		buffer.put((byte) 'a');

		buffer.flip();

		Buffers.tail(buffer);

		buffer.put((byte) 'b');

		buffer.put("fuck".getBytes());

		Buffers.append(buffer, "fuck".getBytes());

		buffer.flip();

		System.out.println("limit:" + buffer.limit() + ", position:" + buffer.position());

		System.out.println("limit:" + buffer.limit());

		System.out.println(new String(Buffers.bytes(buffer)));

		System.out.println(new String(Buffers.bytes(buffer, 0, 2)));

		System.out.println(new String(Buffers.bytes(buffer, 0, -2)));

	}

	@Test
	public void testCharBuffer() {

		CharBuffer buffer = CharBuffer.allocate(1024);

		buffer.put('a');
		buffer.put('b');
		buffer.put('c');

		System.out.println(new String(Buffers.chars(buffer)));

	}
}
