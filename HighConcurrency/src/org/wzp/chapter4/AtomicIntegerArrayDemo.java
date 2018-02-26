package org.wzp.chapter4;

import java.util.concurrent.atomic.AtomicIntegerArray;
/**
 * 无锁整型数组 AtomicIntegerArray
 * 无锁long型数组 AtomicLongArray
 * 无锁对象数字 AtomicReferenceArray
 * @author wzp
 * @date: 2018年2月26日 下午4:45:33 
 *
 */
public class AtomicIntegerArrayDemo {
	static AtomicIntegerArray arr = new AtomicIntegerArray(10);
	public static class AddThread implements Runnable{

		@Override
		public void run() {
			for(int i = 0; i < 10000; i++)
			{
				arr.getAndIncrement(i % arr.length());
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] ts = new Thread[10];
		for(int i = 0; i < 10; i++) {
			ts[i] = new Thread(new AddThread());
		}
		for(int i = 0; i < 10; i++) {
			ts[i].start();
		}
		for(int i = 0; i < 10; i++) {
			ts[i].join();
		}
		System.out.println(arr);
	}
}
