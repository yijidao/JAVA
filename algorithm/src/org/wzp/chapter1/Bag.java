package org.wzp.chapter1;
/**
 * Bag实现
 * Bag 特点，只进不出
 * @author wzp
 * @date: 2018年3月19日 下午11:41:20 
 *
 */
public class Bag<Item> {
	private Node first;
	private int N;
	private class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	public Bag() {
		first = null;
		N = 0;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return N;
	}
	
	public void add(Item item) {
		Node<Item> oldfirst = first;
		first.item = item;
		first.next = oldfirst;
	}
}
