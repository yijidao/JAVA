package org.wzp.chapter3;

import java.util.TreeMap;
/**
 * 查找
 * @author wzp
 * @date: 2018年3月13日 下午2:22:36 
 *
 * @param <Key>
 * @param <Value>
 */
public class ST<Key, Value> {
	
	private TreeMap<Key, Value> st;
	
	public ST() {
		st = new TreeMap<Key, Value>();
	}
	
	public void put(Key key, Value val) {
        if (key == null) 
        	throw new IllegalArgumentException("calls put() with null key");
        if (val == null) 
        	st.remove(key);
        else st.put(key, val);
	}
	
	public Value get(Key key) {
		if(key == null)
			throw new IllegalArgumentException("call get() with null key");
		return st.get(key);
	}
	
	public void delete(Key key) {
		if(key == null)
			throw new IllegalArgumentException("call get() with null key");
		st.remove(key);
	}
	
	public boolean contains(Key key) {
		return st.containsKey(key);
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return st.size();
	}
	
	public Iterable<Key> keys(){
		return st.keySet();
	}
	
	public Key min() {
		return st.firstKey();
	}
	
	public Key max() {
		return st.lastKey();
	}
	
	public Key floor(Key key) {
		Key k = st.floorKey(key);
		return k;
	}
	
	public Key ceiling(Key key) {
		Key k = st.ceilingKey(key);
		return k;
	}
	
//	public int rank() {
//		
//	}
//	
//	public Key select(int k) {
//		
//	}
//	
//	public void deleteMin() {
//		
//	}
//	
//	public void deleteMax() {
//		
//	}
//	
//	public int size(Key lo, Key hi) {
//		
//	}
//	
//	Iterable<Key> keys(Key lo, Key hi) {
//		
//	}
	
}
