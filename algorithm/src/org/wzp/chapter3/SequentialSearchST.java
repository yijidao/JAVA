package org.wzp.chapter3;

/**
 * 基于链表的查找
 * @author wzp
 * @date: 2018年3月13日 下午2:21:59 
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST<Key, Value> {
	private Node first; 
	private int N;
	
	private class Node{
		private Key key;
		private Value value;
		private Node next;
		
		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	public void put(Key key, Value val) {
		for(Node x = first; x != null; x = x.next) {
			if(x.key.equals(key)) {
				x.value = val;
				return;
			}
		}
		
		first = new Node(key, val, first); // put 就是将新的 key/value 赋值到首节点
		N++;
	}
	
	public Value get(Key key) {
		for(Node x = first; x != null; x = x.next) {
			if(x.key.equals(key))
				return x.value;
		}
		return null;
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	// 从链表的头开始往后扫描，找到就删除
	public void delete(Key key) {
		delete(first, key);
	}
	
	private Node delete(Node x, Key key) {
		if(key.equals(x.key)) {
			N--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}
	
//	public Iterable<Key> keys(){
//
//	}
}
