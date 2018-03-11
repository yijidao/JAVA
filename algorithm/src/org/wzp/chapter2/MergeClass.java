package org.wzp.chapter2;
/**
 * 自顶向下的归并排序
 * 使用递归实现的归并排序，是分而治之思想的一个实现
 * 自顶向下就像一颗树结构，先分为一个个小叶子进行排序，再逐级往上
 * @author wzp
 * @date: 2018年3月11日 下午4:01:17 
 *
 */
public class MergeClass extends Example {
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
	
	private Comparable[] aux;

	@Override
	protected void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, 0, a.length - 1);
	}
	
	private void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo) 
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid); // 将左半边排序
		sort(a, mid + 1, hi); // 将右半边排序
		merge(a, lo, mid, hi);
	}
}
