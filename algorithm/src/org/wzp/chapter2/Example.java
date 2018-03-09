package org.wzp.chapter2;

import org.wzp.util.In;
import org.wzp.util.StdOut;

/**
 * 排序算法模版
 * sort()
 * less()
 * exch()
 * isSorted()
 * show()
 * @author wzp
 * @date: 2018年3月9日 下午3:06:38 
 *
 */
public abstract class Example {
	protected abstract void sort(Comparable[] a);
	
	protected static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	protected void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	protected void show(Comparable[] a) {
		for(int i = 0; i < a.length; i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
	
	protected boolean isSorted(Comparable[] a) {
		for(int i = 1; i < a.length; i++) {
			if(less(a[i], a[i-1]))
				return false;
		}
		return true;
	}
	
//	public static void main(String[] args) {
//		String[] a = In.readStrings();
//		sort(a);
//		assert isSorted(a);
//		show(a);
//	}
}
