package org.wzp.chapter2;
/**
 * 自底向上的归并排序
 * 自底向上比自顶向下的代码量更少一些
 * 自底向上可以想象成先将节点大小为1的链表进行排序，排完序，就是节点大小2的链表进行排序，再逐级往上
 * @author wzp
 * @date: 2018年3月11日 下午4:28:03 
 *
 */
public class MergeBUSort extends Example {

	private Comparable[] aux;
	
	@Override
	protected void sort(Comparable[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for(int sz = 1; sz < N; sz = sz + sz) { // sz 子数组大小
			for(int lo = 0; lo < N - sz; lo += sz + sz) { // lo 子数组索引
				// 最后一个子数组的大小，只有在数组大小是sz的偶数倍时才会等于sz，否者它会比sz小，所以这里使用N - 1
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
			}
		}
	}
	
	private void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		
		for(int k = 0; k <= hi; k++) {
			aux[k] = a[k];
		}
		
		for(int k = lo; k <= hi; k++) {
			if(i > mid)
				a[k] = aux[j++];
			else if(j > hi)
				a[k] = aux[i++];
			else if(less(aux[j], aux[i]))
				a[k] = aux[j++];
			else 
				a[k] = aux[i++];
		}
	}
}
