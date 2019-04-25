package cn.quickly.project.utility.math;

import java.util.function.Consumer;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Assert;

public final class Arrangements {

	/**
	 * 排列选择（从列表中选择n个排列）
	 * 
	 * @param datas
	 *            待选列表
	 * @param n
	 *            选择个数
	 */
	public static <T> void select(T[] datas, int n, Consumer<T[]> action) {

		Assert.isTrue(datas.length >= n, "the argument n can't bigger than datas length");

		select(datas, Arrays.clone(datas, n), 0, action);

	}

	/**
	 * 排列选择
	 * 
	 * @param datas
	 *            待选列表
	 * @param result
	 *            前面（ri-1）个的排列结果
	 * @param ri
	 *            选择索引，从0开始
	 */
	private static <T> void select(T[] datas, T[] result, int ri, Consumer<T[]> action) {

		int rl = result.length;

		if (ri >= rl) { // 全部选择完时，输出排列结果
			action.accept(result);
			return;
		}

		// 递归选择下一个
		for (int i = 0; i < datas.length; i++) {

			// 判断待选项是否存在于排列结果中
			boolean exists = false;

			for (int j = 0; j < ri; j++) {

				if (datas[i].equals(result[j])) {
					exists = true;
					break;
				}

			}

			if (!exists) { // 排列结果不存在该项，才可选择

				result[ri] = datas[i];

				select(datas, result, ri + 1, action);

			}

		}
	}

	public static <T> void factorial(T[] datas, int n, Consumer<T[]> action) {

		T[] result = Arrays.clone(datas, n);

		int[] position = new int[n];

		for (;;) {

			for (int i = position.length - 1, max = datas.length - 1; i >= 0; i--) {

				result[i] = datas[position[i]];

				if (i + 1 >= position.length) {
					position[i]++;
				} else if (position[i + 1] > max) {
					position[i]++;
					position[i + 1] = 0;
				}

			}

			action.accept(result);

			if (position[0] >= datas.length) {
				break;
			}

		}

	}

}
