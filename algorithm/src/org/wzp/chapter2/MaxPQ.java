package org.wzp.chapter2;
/**
 * 基于堆的优先队列
 * @author wzp
 * @date: 2018年3月12日 下午3:45:37 
 *
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int N = 0;
	
	public MaxPQ(int maxN) {
		pq = (Key[])new Comparable[maxN + 1];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void insert(Key v) {
		pq[++N] = v;
		swim(N);
	}
	
	public Key delMax() {
		Key max  = pq[1];
		exch(1, N--);
		pq[N + 1] = null;
		sink(1);
		return max;
	}
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
	// 上浮
	private void swim(int k) {
		if(k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k = k/2;
		}
	}
	// 下沉
	private void sink(int k) {
		while(2 * k <= N)
		{
			int j =  2 * k;
			if(j < N && less(j, j + 1))
				j++;
			if(!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}
}
