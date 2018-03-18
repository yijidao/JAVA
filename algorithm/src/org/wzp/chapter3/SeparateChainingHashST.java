package org.wzp.chapter3;
/**
 * 基于拉链法的散列表
 * 数组的每个位置都放着一个链表，每个链表存放这相应的值
 * @author wzp
 * @date: 2018年3月18日 下午5:30:46 
 *
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {
	private int N;
	private int M; // 散列表大小
	private SequentialSearchST<Key, Value>[] st; // 存放链表对象的数组
	
	public SeparateChainingHashST() {
		this(997); // 一般数组大小使用素数
	}
	
	public SeparateChainingHashST(int M) {
		this.M = M;
		st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[M];
		for(int i = 0; i < M; i++)
			st[i] = new SequentialSearchST<Key, Value>();
	}
	
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	public Value get(Key key) {
		return st[hash(key)].get(key);
	}
	
	public void put(Key key, Value val) {
		st[hash(key)].put(key, val);
	}
}
