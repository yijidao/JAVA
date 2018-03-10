package org.wzp.chapter2;
/**
 * 插入排序
 * 
 * @author wzp
 * @date: 2018年3月9日 下午5:21:39 
 *
 */
public class InsertionSort extends Example {

	@Override
	protected void sort(Comparable[] a) {
		for(int i = 1; i < a.length; i++) {
			for(int j = i; j > 0; j--) {
				if(less(a[j], a[j - 1])) 
					exch(a, j, j-1);;
			}
		}
	}
	
}
