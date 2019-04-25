package cn.quickly.project.utility.lang;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import cn.quickly.project.utility.codec.IdentityCard;
import cn.quickly.project.utility.text.Formats;

public class Randoms {

	public final static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public final static String NUMBERS = "0123456789";

	public final static String AN = Strings.concat(ALPHABET, NUMBERS);

	public static final Random random = new Random();

	public static final String[] PHONE_NUMBER_SEGMENTS = {
			// 移动号码段开始
			"1340", "1341", "1342", "1343", "1344", "1345", "1346", //
			"1347", "1348", "135", "136", "137", "138", "139", "147", //
			"150", "151", "152", "157", "158", "159", "187", "188", //
			// 联通号码段
			"130", "131", "132", "155", "156", "185", "186", //
			// 电信号码段
			"133", "153", "180", "189"

	};

	public static final String PROVINCE_CODES[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",
			"44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91" };

	public static boolean nextBoolean() {

		return random.nextBoolean();

	}

	public static int nextInt(int bound) {

		return random.nextInt(bound);

	}

	public static int nextInt(int min, int max) {

		return nextInt(Math.abs(max)) % (max - min + 2) + min;

	}

	public static int nextInt() {

		return random.nextInt();

	}

	public static long nextLong() {

		return random.nextLong();

	}

	public static float nextFloat() {

		return random.nextFloat();

	}

	public static double nextDouble(double min, double max) {

		return min + nextDouble(max) % (max - min + 2);

	}

	public static double nextDouble(double bound) {

		return random.nextDouble() * bound;

	}

	public static double nextDouble() {

		return random.nextDouble() * Math.pow(2, nextInt(-64, 64));

	}

	public static double nextGaussian() {

		return random.nextGaussian();

	}

	public static String nextString() {
		String skey = Long.toHexString(System.nanoTime());
		int i = 0, len = skey.length(), hash = 5381;
		for (; i < len; ++i) {
			hash += (hash << 5) + skey.charAt(i);
		}
		return Long.toHexString(hash & 0x7fffffff);
	}

	public static String nextString(int length) {

		StringBuffer buffer = new StringBuffer();

		Loops.repeat(1, length, (i) -> buffer.append(AN.charAt(nextInt(AN.length()))));

		return buffer.toString();

	}

	public static String nextMobile() {

		String numberSegment = PHONE_NUMBER_SEGMENTS[nextInt(PHONE_NUMBER_SEGMENTS.length - 1)];

		int needLength = 11 - numberSegment.length();

		int maxNumber = Integer.parseInt(Strings.rpad(1, '0', needLength)) - 1;

		return Strings.concat(numberSegment, Strings.lpad(nextInt(maxNumber), '0', needLength));

	}

	public static <T> T nextItem(Collection<T> items) {

		return new ArrayList<>(items).get(nextInt(items.size() - 1));

	}

	public static <T> T nextItem(T[] items) {

		return items[nextInt(items.length - 1)];
	}

	public static <T extends Enum<T>> T nextEnum(Class<T> enumClass) {

		return (T) nextItem(enumClass.getEnumConstants());

	}

	public static Date nextDate() {

		return nextDate(-nextInt(30), -nextInt(12));

	}

	public static Date nextDate(Integer year, Integer month) {

		Calendar calendar = Calendar.getInstance();

		if (year != null) {

			calendar.add(Calendar.YEAR, year);

		}

		if (month != null) {

			calendar.add(Calendar.MONTH, month);

		}

		calendar.add(Calendar.DAY_OF_YEAR, nextInt(365 - calendar.get(Calendar.DAY_OF_YEAR)));

		return calendar.getTime();

	}

	public static String nextIdentityCard() {

		StringBuilder builder = new StringBuilder();
		builder.append(nextItem(PROVINCE_CODES));
		builder.append(nextInt(1000, 9999));
		builder.append(Formats.format(nextDate(), "yyyyMMdd"));
		builder.append(nextInt(100, 999));
		builder.append(IdentityCard.getParityBit(Strings.concat(builder.toString(), "0")));

		return builder.toString();

	}

}
