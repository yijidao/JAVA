package org.wzp.chapter1;

import java.util.Iterator;

/**
 * 基于链表的队列实现
 * 进队就是在链表的尾节点增加一个 node
 * 出队就是在链表的首节点删除一个 node
 * 注意：进队和出队应该做一下 isEmpty() 的判断
 * @author wzp
 * @date: 2018年3月8日 上午9:37:18 
 *
 */
public class NodeQueue<Item> implements Iterable<Item> {
	int N;
	Node<Item> first;
	Node<Item> last;
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	/**
	 * 进队：加入尾结点
	 * @param item
	 */
	public void enqueue(Item item) {
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if(isEmpty())
			first = last;
		else
			oldlast.next = last;
		N++;
	}
	/**
	 * 出队：删除首节点
	 * @return
	 */
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		if(isEmpty())
			last = null;
		N--;
		return item;
	}
	
	private class Node<Item>{
		Item item;
		Node<Item> next;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	/**
	 * 实现迭代功能
	 * @author wzp
	 * @date: 2018年3月14日 下午2:17:56 
	 *
	 * @param <Item>
	 */
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;
		
		public ListIterator(Node<Item> first){
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
