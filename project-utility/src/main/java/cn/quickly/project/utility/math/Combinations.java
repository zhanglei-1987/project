package cn.quickly.project.utility.math;

import java.util.function.Consumer;

import cn.quickly.project.utility.collection.Arrays;

public final class Combinations {

	/**
	 * 组合选择（从列表中选择n个组合）
	 * 
	 * @param datas
	 *            待选列表
	 * @param n
	 *            选择个数
	 */
	public static <T> void select(T[] datas, int n, Consumer<T[]> action) {

		select(datas, 0, Arrays.clone(datas, n), 0, action);
		
	}

	/**
	 * 组合选择
	 * 
	 * @param datas
	 *            待选列表
	 * @param di
	 *            待选开始索引
	 * @param result
	 *            前面（ri-1）个的组合结果
	 * @param ri
	 *            选择索引，从0开始
	 */
	private static <T> void select(T[] datas, int di, T[] result, int ri, Consumer<T[]> action) {

		int rl = result.length, rc = ri + 1;

		if (rc > rl) { // 全部选择完时，输出组合结果
			action.accept(result);
			return;
		}

		// 递归选择下一个
		for (int i = di; i < datas.length + rc - rl; i++) {

			result[ri] = datas[i];

			select(datas, i + 1, result, ri + 1, action);

		}

	}

}
