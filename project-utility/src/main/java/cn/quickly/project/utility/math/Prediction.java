package cn.quickly.project.utility.math;

import cn.quickly.project.utility.lang.Iterables;
import cn.quickly.project.utility.reflect.Compat;

public class Prediction {

	private Prediction() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 滑动求和
	 * 
	 * @param ds
	 * @return
	 */
	public static double ssum(double... ds) {

		double total = 0;

		for (int i = 1; i < ds.length; i++) {

			total += ds[i - 1] + ds[i];

		}

		return total;

	}

	/**
	 * 滑动平均数
	 * 
	 * @param ds
	 * @return
	 */
	public static double savg(double... ds) {

		return ssum(ds) / (ds.length * 2 - 2);

	}

	/**
	 * 加权平均数
	 * 
	 * [weight, value]
	 * 
	 * @param ds
	 * @return
	 */
	public static double wavg(double[]... ds) {

		double molecule = Maths.sum(Compat.cast(Iterables.flat(ds, (i, d) -> d[0] * d[1]), double[].class));

		double denominator = Maths.sum(Compat.cast(Iterables.flat(ds, (i, d) -> d[0]), double[].class));

		return molecule / denominator;

	}

	/**
	 * 加权滑动平均数
	 * 
	 * @param ds
	 * @return
	 */
	public static double swavg(double[]... ds) {

		double molecule = ssum(Compat.cast(Iterables.flat(ds, (i, d) -> d[0] * d[1]), double[].class));

		double denominator = ssum(Compat.cast(Iterables.flat(ds, (i, d) -> d[0]), double[].class));

		return molecule / denominator;

	}

}
