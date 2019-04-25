package cn.quickly.project.utility.math;

import java.math.BigDecimal;

import org.junit.Test;

public class DecimalCalculatorTest {

	private DecimalCalculator calculator = new DecimalCalculator();

	@Test
	public void testAdd() {

		System.out.println(calculator.add("age", 12));

	}

	@Test
	public void testSubtract() {

		System.out.println(calculator.subtract("age", 12));

	}

	@Test
	public void testMultiply() {

		System.out.println(calculator.add("age", 12));
		System.out.println(calculator.multiply("age", 12));

	}

	@Test
	public void testDivide() {

		System.out.println(calculator.add("age", 12));
		System.out.println(calculator.divide("age", 12));

	}

	@Test
	public void testGetResult() {

		System.out.println(calculator.add("age", 12));
		System.out.println(calculator.divide("length", 12));
		System.out.println(calculator.getResult());

	}

	@Test
	public void testExec() {

		System.out.println(calculator.add("age", 12));
		System.out.println(calculator.add("length", 12));
		System.out.println(calculator.exec("age * length"));

		System.out.println(new BigDecimal("0.00").compareTo(new BigDecimal(0)));

	}

}
