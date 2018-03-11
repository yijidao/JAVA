package org.wzp.chapter2;

import org.wzp.util.StdRandom;

/**
 * 快速排序
 * @author wzp
 * @date: 2018年3月11日 下午5:22:27 
 *
 */
public class QuickSort extends Example {

	@Override
	protected void sort(Comparable[] a) {
		StdRandom.shuffle(a); // 消除对输入的依赖
		sort(a, 0, a.length - 1);
	}
	
	private void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	/**
	 * 切分方法
	 * 先随意地取 a[lo] 为切分元素，
	 * 然后从左往右扫描数组，寻找到一个大于切分元素的值，
	 * 然后从右往左扫描，寻找到一个小于切分元素的值，
	 * 然后交换两个元素的位置，
	 * 如此继续，这样我们就能保证左指针 i 的左侧元素均不大于切分元素，右指针 j 的右侧元素均不小于切分元素，
	 * 当两个指针相遇时，只需要将切分元素 a[lo] 与做数组的最大元素 a[j] 交换，然后返回 j 即可
	 * @param a
	 * @param lo
	 * @param hi
	 * @return
	 */
	private int partition(Comparable[] a, int lo, int hi) {
		int i = lo, j = hi +1;
		Comparable v = a[lo];
		while(true) {
			// 从左往右扫描
			while(less(a[++i], v))
				if(i == hi) break;
			// 从右往左扫描
			while(less(v, a[--j]))
				if(j == lo) break;
			
			if(i >= j) break;
			
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
}
