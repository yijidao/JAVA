package org.wzp.chapter1;

import java.util.Iterator;

/**
 * Bag实现
 * Bag 特点，只进不出
 * @author wzp
 * @date: 2018年3月19日 下午11:41:20 
 *
 */
public class Bag<Item> implements Iterable<Item> {
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

	@Override
	public Iterator<Item> iterator() {
		return null;
	}
	
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;
		
		public ListIterator(Node<Item> first) {
			current = first;
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
}
