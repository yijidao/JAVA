package org.wzp.chapter3;

import java.util.concurrent.locks.*;

import com.sun.management.jmx.Trace;

/**
 * 一个使用ReentrantLock 的 中断解决死锁案例
 * @author wzp
 *
 */
public class IntLock implements Runnable {
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lock;
	
	public IntLock(int lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try {
			if(lock == 1) {
				lock1.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock2.lockInterruptibly();
			} else {
				lock2.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock1.lockInterruptibly();
			}
		} catch (InterruptedException e) {
			//e.printStackTrace();
			System.out.println("--" + Thread.currentThread().getId() + "被中断");
		} finally {
			if(lock1.isHeldByCurrentThread()) 
				lock1.unlock(); // 发生中断后解锁
			if(lock2.isHeldByCurrentThread())
				lock2.unlock(); // 发生中断后解锁
			System.out.println("--" + Thread.currentThread().getId() + ":线程退出");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		IntLock r1 = new IntLock(1);
		IntLock r2 = new IntLock(2);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t2.interrupt();
	}
	
}
