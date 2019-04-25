package cn.quickly.project.utility.codec;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.math.Maths;

public final class Bits {

	private Bits() {
		throw new UnsupportedOperationException();
	}

	public static short getShort(byte[] buffer) {
		return getShort(buffer, ByteOrder.nativeOrder());
	}

	public static short getShort(byte[] buffer, ByteOrder order) {

		Assert.isTrue(buffer.length != 2, "the buffer length must equals 2.");

		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

		byteBuffer.order(order);

		return byteBuffer.getShort();

	}

	public static int getInt(byte[] buffer) {
		return getInt(buffer, ByteOrder.nativeOrder());
	}

	public static int getInt(byte[] buffer, ByteOrder order) {

		Assert.isTrue(buffer.length == 4, "the buffer length must equals 4.");

		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

		byteBuffer.order(order);

		return byteBuffer.getInt();

	}

	public static long getLong(byte[] buffer) {
		return getLong(buffer, ByteOrder.nativeOrder());
	}

	public static long getLong(byte[] buffer, ByteOrder order) {

		Assert.isTrue(buffer.length == 8, "the buffer length must equals 8.");

		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

		byteBuffer.order(order);

		return byteBuffer.getLong();

	}

	public static float getFloat(byte[] buffer) {
		return getFloat(buffer, ByteOrder.nativeOrder());
	}

	public static float getFloat(byte[] buffer, ByteOrder order) {

		Assert.isTrue(buffer.length == 4, "the buffer length must equals 4.");

		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

		byteBuffer.order(order);

		return byteBuffer.getFloat();

	}

	public static double getDouble(byte[] buffer) {
		return getDouble(buffer, ByteOrder.nativeOrder());
	}

	public static double getDouble(byte[] buffer, ByteOrder order) {

		Assert.isTrue(buffer.length == 8, "the buffer length must equals 8.");

		ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);

		byteBuffer.order(order);

		return byteBuffer.getDouble();

	}

	public static short[] getShorts(byte[] buffer) {
		return getShorts(buffer, ByteOrder.nativeOrder());
	}

	public static short[] getShorts(byte[] bytes, ByteOrder order) {

		Assert.isTrue(bytes != null && bytes.length % 2 == 0, "the byte array is null or not multiple of 2.");

		int length = bytes.length, count = (int) (Maths.ceil(length, 2));

		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(order);

		short[] shorts = new short[count];

		for (int i = 0; i < count; i++) {
			shorts[i] = buffer.getShort();
		}

		return shorts;
	}

	public static int[] getInts(byte[] buffer) {
		return getInts(buffer, ByteOrder.nativeOrder());
	}

	public static int[] getInts(byte[] bytes, ByteOrder order) {

		Assert.isTrue(bytes != null && bytes.length % 4 == 0, "the byte array is null or not multiple of 4.");

		int length = bytes.length, count = (int) (Maths.ceil(length, 4));

		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(order);

		int[] ints = new int[count];

		for (int i = 0; i < count; i++) {
			ints[i] = buffer.getInt();
			System.out.println(i);
		}

		return ints;

	}

	public static long[] getLongs(byte[] buffer) {
		return getLongs(buffer, ByteOrder.nativeOrder());
	}

	public static long[] getLongs(byte[] bytes, ByteOrder order) {

		Assert.isTrue(bytes != null && bytes.length % 8 == 0, "the byte array is null or not multiple of 8.");

		int length = bytes.length, count = (int) (Maths.ceil(length, 8));

		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(order);

		long[] longs = new long[count];

		for (int i = 0; i < count; i++) {
			longs[i] = buffer.getLong();
		}

		return longs;
	}

	public static float[] getFloats(byte[] buffer) {
		return getFloats(buffer, ByteOrder.nativeOrder());
	}

	public static float[] getFloats(byte[] bytes, ByteOrder order) {

		Assert.isTrue(bytes != null && bytes.length % 4 == 0, "the byte array is null or not multiple of 4.");

		int length = bytes.length, count = (int) (Maths.ceil(length, 4));

		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(order);

		float[] floats = new float[count];

		for (int i = 0; i < count; i++) {
			floats[i] = buffer.getFloat();
		}

		return floats;

	}

	public static double[] getDoubles(byte[] buffer) {
		return getDoubles(buffer, ByteOrder.nativeOrder());
	}

	public static double[] getDoubles(byte[] bytes, ByteOrder order) {

		Assert.isTrue(bytes != null && bytes.length % 8 == 0, "the byte array is null or not multiple of 8.");

		int length = bytes.length, count = (int) (Maths.ceil(length, 8));

		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		buffer.order(order);

		double[] doubles = new double[count];

		for (int i = 0; i < count; i++) {
			doubles[i] = buffer.getDouble();
		}

		return doubles;
	}

	public static byte[] getBytes(short... values) {
		return getBytes(ByteOrder.nativeOrder(), values);
	}

	public static byte[] getBytes(ByteOrder order, short... values) {

		int length = values.length;

		ByteBuffer buffer = ByteBuffer.allocate(length * 2);
		buffer.order(order);

		for (short value : values) {

			buffer.putShort(value);

		}

		buffer.flip();

		return buffer.array();

	}

	public static byte[] getBytes(int... values) {
		return getBytes(ByteOrder.nativeOrder(), values);
	}

	public static byte[] getBytes(ByteOrder order, int... values) {

		int length = values.length;

		ByteBuffer buffer = ByteBuffer.allocate(length * 4);
		buffer.order(order);

		for (int value : values) {

			buffer.putInt(value);

		}

		buffer.flip();

		return buffer.array();

	}

	public static byte[] getBytes(long... values) {
		return getBytes(ByteOrder.nativeOrder(), values);
	}

	public static byte[] getBytes(ByteOrder order, long... values) {

		int length = values.length;

		ByteBuffer buffer = ByteBuffer.allocate(length * 8);
		buffer.order(order);

		for (long value : values) {

			buffer.putLong(value);

		}

		buffer.flip();

		return buffer.array();

	}

	public static byte[] getBytes(float... values) {
		return getBytes(ByteOrder.nativeOrder(), values);
	}

	public static byte[] getBytes(ByteOrder order, float... values) {

		int length = values.length;

		ByteBuffer buffer = ByteBuffer.allocate(length * 4);
		buffer.order(order);

		for (float value : values) {

			buffer.putFloat(value);

		}

		buffer.flip();

		return buffer.array();

	}

	public static byte[] getBytes(double... values) {
		return getBytes(ByteOrder.nativeOrder(), values);
	}

	public static byte[] getBytes(ByteOrder order, double... values) {

		int length = values.length;

		ByteBuffer buffer = ByteBuffer.allocate(length * 8);
		buffer.order(order);

		for (double value : values) {

			buffer.putDouble(value);

		}

		buffer.flip();

		return buffer.array();

	}

}
