package cn.quickly.project.utility.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

import cn.quickly.project.utility.lang.Loops;
import cn.quickly.project.utility.reflect.Compat;

public final class Maths {

	private Maths() {
		throw new UnsupportedOperationException();
	}

	public static double scale(double num, int n) {
		return new BigDecimal(num).setScale(n, RoundingMode.HALF_UP).doubleValue();
	}

	public static double door(double num, double min, double max) {
		return Math.max(Math.min(num, max), min);
	}

	public static double loop(double num, double min, double max) {
		if (num > max) {
			num = min;
		} else if (num < min) {
			num = max;
		}
		return num;
	}

	public static boolean in(double num, double min, double max) {
		if (num >= min && num <= max) {
			return true;
		} else
			return false;
	}

	public static double ceil(double x, double y) {
		return Math.ceil(x / y);
	}

	public static double log(double a, double n) {
		return Math.log(n) / Math.log(a);
	}

	public static double sigmod(double x) {
		return 1 / (1 + Math.pow(Math.E, -x));
	}

	/**
	 * 计算阶乘数，即n! = n * (n-1) * ... * 2 * 1
	 * 
	 * @param n
	 * @return
	 */
	public static BigInteger factorial(BigInteger n) {
		return (n.compareTo(BigInteger.ONE) > 0) ? n.multiply(factorial(n.subtract(BigInteger.ONE))) : BigInteger.ONE;
	}

	/**
	 * 计算排列数，即A(n, m) = n!/(n-m)!
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public static BigInteger arrangement(BigInteger n, BigInteger m) {
		return (n.compareTo(m) >= 0) ? factorial(n).divide(factorial(n.subtract(m))) : BigInteger.ZERO;
	}

	public static long arrangement(long n, long m) {
		return arrangement(BigInteger.valueOf(n), BigInteger.valueOf(m)).longValue();
	}

	/**
	 * 计算组合数，即C(n, m) = n!/((n-m)! * m!)
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public static BigInteger combination(BigInteger n, BigInteger m) {

		if (n.compareTo(m) >= 0) {
			return factorial(n).divide(factorial(n.subtract(m)).multiply(factorial(m)));
		} else {
			return BigInteger.ZERO;
		}

	}

	public static long combination(long n, long m) {
		return combination(BigInteger.valueOf(n), BigInteger.valueOf(m)).longValue();
	}

	/**
	 * 求和
	 * 
	 * @param ds
	 * @return
	 */
	public static double sum(double... ds) {

		double total = 0;

		for (double d : ds) {

			total += d;

		}

		return total;

	}

	public static double max(double... ds) {

		double max = ds[0];

		for (double d : ds) {

			max = Math.max(max, d);

		}

		return max;

	}

	public static double min(double... ds) {

		double min = ds[0];

		for (double d : ds) {

			min = Math.min(min, d);

		}

		return min;

	}

	/**
	 * 平均数
	 * 
	 * @param ds
	 * @return
	 */
	public static double avg(double... ds) {

		return sum(ds) / ds.length;

	}

	public static double cos(double[] a, double[] b) {

		List<Double> abs = Loops.repeat(0, Math.min(a.length, b.length) - 1, (i) -> {

			return a[i] * b[i];

		});

		List<Double> as = Loops.repeat(0, a.length - 1, (i) -> {

			return Math.pow(a[i], 2);

		});

		List<Double> bs = Loops.repeat(0, b.length - 1, (i) -> {

			return Math.pow(b[i], 2);

		});

		double sumabs = sum(Compat.cast(abs, double[].class));
		double sumas = sum(Compat.cast(as, double[].class));
		double sumbs = sum(Compat.cast(bs, double[].class));

		return sumabs / (Math.sqrt(sumas) * Math.sqrt(sumbs));

	}

	public static double hypot(double[] a, double[] b) {

		List<Double> abs = Loops.repeat(0, Math.max(a.length, b.length) - 1, (i) -> {

			double xa = 0, xb = 0;

			if (i < a.length) {
				xa = a[i];
			}

			if (i < b.length) {
				xb = b[i];
			}

			return Math.pow(Math.abs(xa - xb), 2);

		});

		return Math.sqrt(sum(Compat.cast(abs, double[].class)));

	}

}
