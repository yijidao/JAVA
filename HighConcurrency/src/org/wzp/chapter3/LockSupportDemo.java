package org.wzp.chapter3;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程堵塞工具类：LockSupport
 * 可以替代 resume, suspend,不会出现需要 resume 一定必须在 suspend 后执行的要求
 * @author wzp
 * @date: 2018年2月22日 下午11:29:22 
 *
 */
public class LockSupportDemo {
	public static Object u =  new Object();
	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");
	
	public static class ChangeObjectThread extends Thread{
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		@Override
		public void run() {
			synchronized (u) {
				System.out.println("--in" + getName());
				LockSupport.park();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		LockSupport.unpark(t1);
		LockSupport.unpark(t2);
		t1.join();
		t2.join();
	}
}
