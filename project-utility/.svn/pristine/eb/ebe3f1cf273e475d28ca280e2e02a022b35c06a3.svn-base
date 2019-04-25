package cn.quickly.project.utility.codec;

import java.util.Date;

import org.junit.Test;

import cn.quickly.project.utility.lang.Randoms;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.security.Hmac;
import cn.quickly.project.utility.time.Dates;

public class OtpTest {

	private String algorithm = "SHA256";

	private byte[] key = Hmac.getRandomKey(algorithm, 256).getEncoded();

	@Test
	public void testHotp() {

		byte[] data = Randoms.nextString(256).getBytes();

		System.out.println(Otp.hotp(data, 9, algorithm, key));

		System.out.println(Otp.hotp(data, 1, algorithm, key));

		System.out.println(Otp.hotp(data, 10, algorithm, key));

	}

	@Test
	public void testTotp() throws Exception {

		Date time = Dates.date("2017-12-13 11:05:01.123", "yyyy-MM-dd HH:mm:ss.SSS");

		System.out.println(Otp.totp(time, 60 * 1000, 6, algorithm, key));

		Thread.sleep(5000);

		System.out.println(Otp.totp(time, 60 * 1000, 6, algorithm, key));

		for (int i = 0; i < 10; i++) {
			System.out.println(Otp.totp(time, 1, 9, algorithm, key));
		}

	}

	@Test
	public void testPayCode() {

		Date time = Dates.date("2017-12-13 11:05:00", "yyyy-MM-dd HH:mm:ss");

		for (int i = 0; i < 10; i++) {

			String topt = Otp.totp(time, 60, 5, algorithm, key);

			String uid = "1234567890";

			int offset = Randoms.nextInt(2, 9);

			String code = Strings.concat("77", offset, Drift.encode(Strings.concat(topt, uid), offset));

			System.out.println(code);

		}

	}

}
