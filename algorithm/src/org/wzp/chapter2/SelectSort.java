package org.wzp.chapter2;
/**
 * 选择排序
 * 首先找到数组中最小的值，将其放到第一位，再找到第二小的值，放到第二位，依次循环，完成排序
 * O(N^2/2)
 * @author wzp
 * @date: 2018年3月9日 下午4:46:05 
 *
 */
public class SelectSort extends Example {

	@Override
	protected void sort(Comparable[] a) {
		int N = a.length;
		for(int i = 0; i < N; i++) {
			int min = i;
			for(int j = i+1; j < N; j++) {
				if(less(a[j], a[min]))
					min = j;				
			}
			exch(a, i, min);
		}
	}

}
