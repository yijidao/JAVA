package org.wzp.chapter3;
/**
 * 基于线性探索的符号表
 * @author wzp
 * @date: 2018年3月18日 下午11:02:29 
 *
 */
public class LinearProbingHashST<Key, Value> {
	private int N; // 符号表中键值对的总数
	private int M = 16; // 线性探测表的大小
	private Key[] keys; // 键
	private Value[] vals; // 值
	
	public LinearProbingHashST(){
		keys = (Key[])new Object[M];
		vals = (Value[])new Object[M];
	}
	private LinearProbingHashST(int cap){
		keys = (Key[])new Object[cap];
		vals = (Value[])new Object[cap];
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	private void resize(int cap) {
		LinearProbingHashST<Key, Value> t;
		t = new LinearProbingHashST<Key, Value>(cap);
		for(int i = 0; i < M; i++) {
			if(keys[i] != null)
				t.put(keys[i], vals[i]);
		}
		keys = t.keys;
		vals = t.vals;
		M = t.M;
	}
	
	public void put(Key key, Value val) {
		if(N >= M/2)
			resize(2 * M);
		int i;
		for(i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if(keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	
	public Value get(Key key) {
		for(int i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if(keys[i].equals(key))
				return vals[i];
		}
		return null;
	}
}
