package cn.quickly.project.utility.lang;

import org.junit.Test;

import cn.quickly.project.utility.codec.IdentityCard;
import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.mock.MockEnum;

public class RandomsTest {

	@Test
	public void testNextInt() {

		for (int i = -10; i < 10; i++) {
			System.out.println(Randoms.nextInt(i, 10));
		}

		for (int i = -10; i < 10; i++) {
			System.out.println(Randoms.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
		}

		for (int i = -10; i < 10; i++) {
			System.out.println(Randoms.nextInt(Integer.MIN_VALUE, Integer.MIN_VALUE / 2));
		}

	}

	@Test
	public void testNextDouble() {

		for (int i = -10; i < 50; i++) {
			System.out.println(Randoms.nextDouble(i, 50));
		}

		for (int i = -10; i < 10; i++) {
			System.out.println(Randoms.nextDouble(Double.MIN_VALUE, Double.MAX_VALUE));
		}

		for (int i = -10; i < 10; i++) {
			System.out.println(Randoms.nextDouble(Long.MIN_VALUE, Long.MIN_VALUE / 2));
		}

	}

	@Test
	public void testNextMobile() {

		for (int i = 0; i < 1000; i++) {
			System.out.println(Randoms.nextMobile());
		}

	}

	@Test
	public void testNextItem() {

		for (int i = 0; i < 1000; i++) {
			System.out.println(Randoms.nextItem(Arrays.as("a", "b", "c")));
		}

	}

	@Test
	public void testNextEnum() {

		for (int i = 0; i < 1000; i++) {

			System.out.println(Randoms.nextEnum(MockEnum.class));

		}

	}

	@Test
	public void testNextIdentityCard() {

		for (int i = 0; i < 1000; i++) {

			System.out.println(IdentityCard.isValidated(Randoms.nextIdentityCard()));

		}

	}

	@Test
	public void testNextString() {

		System.out.println(Randoms.nextString());

		System.out.println(Randoms.nextString(2));
		
		System.out.println(Randoms.nextString(102400));

	}

}
