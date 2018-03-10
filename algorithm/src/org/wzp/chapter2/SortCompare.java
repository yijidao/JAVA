package org.wzp.chapter2;

import org.wzp.util.StdRandom;
import org.wzp.util.Stopwatch;

/**
 * 测试不同的排序算法性能
 * @author wzp
 * @date: 2018年3月10日 下午5:24:11 
 *
 */
public class SortCompare {
	/**
	 * 计时
	 * @param alg 算法类型
	 * @param a 需要排序的数组
	 * @return
	 */
	public static double time(String alg, Double[] a) {
		Stopwatch timer = new Stopwatch();
		Example sort = null;
		switch(alg) {
			case "Insertion": 
				sort = new InsertionSort(); break;
			case "Select":
				sort = new SelectSort();break;
		}
		sort.sort(a);
		return timer.elapsedTime();
	}
	/**
	 * 计算总耗时
	 * @param alg 算法类型
	 * @param T 执行t次排序
	 * @param N 被排序的数组长度
	 * @return
	 */
	public static double timeRandomInput(String alg, int N, int T) {
		double total = 0.0;
		Double[] a = new Double[N];
		for(int t = 0; t < T; t++) {
			for(int n = 0; n < N; n++) {
				a[n] = StdRandom.uniform();
			}
			total += time(alg, a);
		}
		return total;
	}
	
	public static void main(String[] args) {
		String alg1 = "Insertion";
		String alg2 = "Select";
		int N = 10000;
		int T = 10;
		double t1 = timeRandomInput(alg1, N, T);
		double t2 = timeRandomInput(alg2, N, T);
		System.out.println("--" + alg1 + "耗时：" + t1);
		System.out.println("--" + alg2 + "耗时：" + t2);
	}
}
