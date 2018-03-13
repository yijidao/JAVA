package org.wzp.chapter3;
/**
 * 基于有序数组的二分查找
 * @author wzp
 * @date: 2018年3月13日 下午2:22:50 
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private Key[] keys;
	private Value[] vals;
	private int N;
	public BinarySearchST(int capacity) {
		keys = (Key[])new Comparable[capacity];
		vals = (Value[])new Comparable[capacity];
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void put(Key key, Value val) {
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}
		for(int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public Value get(Key key) {
		if(isEmpty()) return null;
		int i = rank(key);
		if(i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		else
			return null;
	}
	/**
	 * 基于有序数组的二分查找（迭代）
	 * @param key
	 * @return
	 */
	public int rank(Key key) {
		int lo = 0, hi  = N - 1;
		while(lo <= hi) {
			int mid = (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if(cmp < 0)
				hi = mid - 1;
			else if(cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}
	
	public Key min() {
		return keys[0];
	}
	
	public Key max() {
		return keys[N - 1];
	}
	
	public Key select(int k) {
		return keys[k];
	}
	
}
