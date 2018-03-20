package org.wzp.chapter1;

import java.util.Iterator;

/**
 * 链表实现的下压栈
 * @author wzp
 * @date: 2018年3月7日 下午11:31:38 
 *
 */
public class NodeStack<Item> implements Iterable<Item> {
	private int N;
	private Node first; // 栈顶，最近添加的元素
	
	/**
	 * 定义了节点的嵌套类
	 * @author wzp
	 * @date: 2018年3月7日 下午11:33:59 
	 *
	 */
	private class Node{
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return N;
	}
	
	public void push(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		N++;
	}
	
	public Item pop() {
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}

	@Override
	public Iterator<Item> iterator() {

		return null;
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current;
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
