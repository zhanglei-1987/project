package cn.quickly.project.utility.codec;

import java.util.Date;

import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.security.Hmac;
import cn.quickly.project.utility.time.Clock;

public final class Otp {

	private static final int[] POWER = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000 };

	private Otp() {
		throw new UnsupportedOperationException();
	}

	public static String hotp(byte[] data, int length, String algorithm, byte[] key) {

		Assert.isNotNull(data, "the argument data required.");
		Assert.isNotNull(algorithm, "the argument algorithm required.");
		Assert.isNotNull(key, "the argument key required.");
		Assert.isTrue(length > 0 && length < POWER.length, "the length must bigger than 0, and less than or equal to " + (POWER.length - 1));

		byte[] hash = Hmac.hash(algorithm, data, key);

		int offset = hash[hash.length - 1] & 0xf;

		int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16) | ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);

		int otp = binary % POWER[length];

		return Strings.lpad(otp + "", '0', length);
	}

	public static String totp(Date time, int step, int length, String algorithm, byte[] key) {

		Assert.isNotNull(time, "the argument time required.");
		Assert.isTrue(length > 0, "the length must bigger than 0.");

		byte[] data = (((Clock.now() - time.getTime()) / step) + "").getBytes();

		return hotp(data, length, algorithm, key);

	}

}
