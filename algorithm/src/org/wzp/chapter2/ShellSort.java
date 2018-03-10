package org.wzp.chapter2;
/**
 * 希尔排序
 * @author wzp
 * @date: 2018年3月10日 下午5:55:56 
 *
 */
public class ShellSort extends Example {

	@Override
	protected void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while(h < N /3)
			h = h * 3 + 1;
		while(h >= 1) {
			for(int i = h; i < N; i++) {
				for(int j = i; j >= h && less(a[j], a[j - h]); j-=h) {
					exch(a, j, j - h);
				}
			}
			h = h / 3;
		}
	}
	
}
