package cn.quickly.project.utility.io;

import java.util.Arrays;

public final class Bytes {

	private Bytes() {

		throw new UnsupportedOperationException();

	}

	public static byte[] merge(byte[] first, byte[] second) {

		byte[] merge = Arrays.copyOf(first, first.length + second.length);

		System.arraycopy(second, 0, merge, first.length, second.length);

		return merge;

	}

	public static byte[] copy(byte[] original, int from) {

		return copy(original, from, original.length);

	}

	public static byte[] copy(byte[] original, int from, int to) {

		if (to < 0) {
			to = original.length + to;
		}

		return Arrays.copyOfRange(original, from, to);
	}

}
