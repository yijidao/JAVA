package org.wzp.chapter2;

import org.wzp.util.StdRandom;

/**
 * 三向切分的快速排序
 * @author wzp
 * @date: 2018年3月11日 下午6:13:56 
 *
 */
public class Quick3way extends Example {

	@Override
	protected void sort(Comparable[] a) {
		StdRandom.shuffle(a); // 消除对输入的依赖
		sort(a, 0, a.length - 1);
	}
	
	private void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) return;
		int lt = lo, i = lo + 1, gt = hi;
		Comparable v = a[lo];
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if(cmp < 0) exch(a, lo++, i++);
			else if(cmp > 0) exch(a, i, gt--);
			else i++;
		}
		sort(a, lo, lt -1);
		sort(a, gt + 1, hi);
	}
}
