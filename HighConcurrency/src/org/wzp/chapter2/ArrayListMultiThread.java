package org.wzp.chapter2;

import java.util.*;

/**
 * Array是线程不安全的,可能会出现三种情况，1.数组越界，2.数据正常，3.数据偏小
 * 解决方法：使用Vector 代替即可
 * @author wzp
 *
 */
public class ArrayListMultiThread {
//	static ArrayList<Integer> al = new ArrayList<Integer>(10);
	static Vector<Integer> al = new Vector<Integer>(10);
	public static class AddThread implements Runnable{

		@Override
		public void run() {
			for(int i = 0; i < 1000000; i++) {
				al.add(i);
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AddThread());
		Thread t2 = new Thread(new AddThread());
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(al.size());
	}
}
