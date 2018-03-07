package org.wzp.chapter1;

import java.util.Iterator;
/**
 * 下压栈的实现
 * 实现了动态扩容和减小，使的栈始终不会溢出，和使用率永远大于 1/4
 * 实现了后进先出的迭代
 * @author wzp
 * @date: 2018年3月7日 下午5:25:01 
 *
 * @param <Item>
 */
public class MyStack<Item> implements Iterable<Item> {
	private int N;
	private Item[] a;
	
	public MyStack(int capacity){
		a = (Item[])new Object[capacity];
	}
	
	public int size() {
		return N;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	private void resize(int max) {
		Item[] temp = (Item[])new Object[max];
		for(int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}
	
	public void push(Item item) {
		if(N == a.length)  // 防止栈溢出
			resize(2 * a.length);
		a[N++] = item;
	}
	
	public Item pop() {
		Item item = a[--N];
		a[N] = null; // 防止对象游离，让GC工作
		if(N > 0 && N == a.length / 4) // 使栈使用率永远不会低于 1/4 
			resize(a.length / 2);
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	/**
	 * 使用内部类实现迭代器
	 * @author wzp
	 * @date: 2018年3月7日 下午5:26:05 
	 *
	 */
	private class ReverseArrayIterator implements Iterator<Item>{
		private int i = N;
		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public Item next() {
			return a[--i];
		}
	}
}
