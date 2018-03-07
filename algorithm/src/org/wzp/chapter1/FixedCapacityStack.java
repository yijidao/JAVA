package org.wzp.chapter1;

import java.util.Scanner;

/**
 * 定容栈的简单实现
 * @author wzp
 * @date: 2018年3月7日 上午11:31:31 
 *
 */
public class FixedCapacityStack {
	
	static class FixedCapacityStackOfString{
		private int N;
		private String[] a;
		
		public FixedCapacityStackOfString(int cap) {
			a = new String[cap];
		}
		
		public boolean isEmpty() {
			return N == 0;
		}
		
		public int size() {
			return N;
		}
		
		public void push(String item) {
			a[N++] = item;
		}
		
		public String pop() {
			return a[--N];
		}
	}
	/**
	 * 泛型定容栈，因为数组不允许使用泛型，所以使用转型来实现泛型数组
	 * @author wzp
	 * @date: 2018年3月7日 上午11:52:58 
	 *
	 * @param <Item>
	 */
	static class FixedCapacityStackOfGenerics<Item>{
		private int N;
		private Item[] a;
		
		public FixedCapacityStackOfGenerics(int cap) {
			a = (Item[])new Object[cap];
		}
		
		public boolean isEmpty() {
			return N == 0;
		}
		
		public int size() {
			return N;
		}
		
		public void resize(int max) {
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
	}
	
	public static void main(String[] args) {
//		FixedCapacityStackOfString stack = new FixedCapacityStackOfString(1024);
		FixedCapacityStackOfGenerics<String> stack = new FixedCapacityStackOfGenerics<String>(1024);
		Scanner sc = new Scanner(System.in);
		while(true) {
			String s = sc.nextLine();
			if(s.equals("$"))
				break;
			else
				stack.push(s);
		}
		String out = "";
		while(stack.size() > 0) {
			out = stack.pop() + out;
		}
		System.out.println(out);
	}
}
