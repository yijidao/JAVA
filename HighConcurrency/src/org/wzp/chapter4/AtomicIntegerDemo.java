package org.wzp.chapter4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 无锁的线程安全整数
 * 无锁跟加锁对比，是另一种解决并发的方式，思想是 CAS(Compate And Swap) 对比交换
 * CAS 算法包含三个参数(V,E,N) V 是要更新的变量，E是预期值，N是新值， 如果 V = E， 则执行操作 V = N， 
 * 若 V != E，则表示发现冲突，则不执行任何操作，返回当前的V
 * @author wzp
 * @date: 2018年2月25日 下午9:47:07 
 *
 */
public class AtomicIntegerDemo {
	static AtomicInteger i = new AtomicInteger();
	public static class AddThread implements Runnable{

		@Override
		public void run() {
			for(int k = 0; k < 10000; k++) {
				i.incrementAndGet();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread[] ts = new Thread[10];
		for(int k = 0; k < 10; k++) {
			ts[k] = new Thread(new AddThread());
		}
		for(int k = 0; k < 10; k++) {
			ts[k].start();
		}
		for(int k = 0; k < 10; k++) {
			ts[k].join();
		}
		System.out.println(i);
	}
}
