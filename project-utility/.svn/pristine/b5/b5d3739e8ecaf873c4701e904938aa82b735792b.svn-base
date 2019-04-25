package cn.quickly.project.utility.codec;

import java.nio.ByteOrder;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;

public class BitsTest {

	@Test
	public void testGetShorts() {

		byte[] bs = { 23, 45, 67, 45, 23, 34, 0, 0 };

		Printer.println(Bits.getShorts(bs, ByteOrder.nativeOrder()));

		Printer.println(Bits.getShorts(bs, ByteOrder.BIG_ENDIAN));

		Printer.println(Bits.getShorts(bs, ByteOrder.LITTLE_ENDIAN));

	}

	@Test
	public void testGetInts() {

		byte[] bs = { 23, 45, 67, 45, 23, 34, 0, 0 };

		Printer.println(Bits.getInts(bs, ByteOrder.nativeOrder()));

		Printer.println(Bits.getInts(bs, ByteOrder.BIG_ENDIAN));

		Printer.println(Bits.getInts(bs, ByteOrder.LITTLE_ENDIAN));

	}

	@Test
	public void testGetBytes() {

		Printer.dump(Bits.getBytes(ByteOrder.nativeOrder(), (short) 100));
		Printer.dump(Bits.getBytes(ByteOrder.BIG_ENDIAN, (short) 100));

		Printer.dump(Bits.getBytes(ByteOrder.nativeOrder(), 100));
		Printer.dump(Bits.getBytes(ByteOrder.BIG_ENDIAN, 100));

		Printer.dump(Bits.getBytes(ByteOrder.nativeOrder(), 100l));
		Printer.dump(Bits.getBytes(ByteOrder.BIG_ENDIAN, 100l));

		Printer.dump(Bits.getBytes(ByteOrder.nativeOrder(), 100f));
		Printer.dump(Bits.getBytes(ByteOrder.BIG_ENDIAN, 100f));

		Printer.dump(Bits.getBytes(ByteOrder.nativeOrder(), 100d));
		Printer.dump(Bits.getBytes(ByteOrder.BIG_ENDIAN, 100d));

	}

}
