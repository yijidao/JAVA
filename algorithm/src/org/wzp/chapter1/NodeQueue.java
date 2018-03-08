package org.wzp.chapter1;
/**
 * 基于链表的队列实现
 * 进队就是在链表的尾节点增加一个 node
 * 出队就是在链表的首节点删除一个 node
 * 注意：进队和出队应该做一下 isEmpty() 的判断
 * @author wzp
 * @date: 2018年3月8日 上午9:37:18 
 *
 */
public class NodeQueue<Item> {
	int N;
	Node first;
	Node last;
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty())
			first = last;
		else
			oldlast.next = last;
		N++;
	}
	
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		if(isEmpty())
			last = null;
		N--;
		return item;
	}
	
	private class Node{
		Item item;
		Node next;
	}
}
