package org.wzp.chapter3;

import java.util.Random;
import java.util.concurrent.locks.*;

/**
 * 读写锁 DEMO
 * @author wzp
 * @date: 2018年2月21日 上午12:30:09 
 *
 */
public class ReadWriteLockDemo {
	private static Lock lock = new ReentrantLock();
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static Lock readLock = readWriteLock.readLock();
	private static Lock writeLock = readWriteLock.writeLock();
	private int value;
	
	public int handleRead(Lock lock) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);
			return value;
		} finally {
			lock.unlock();
		}
	}
	
	public void handleWrite(Lock lock, int index) throws InterruptedException {
		try {
			lock.lock();
			Thread.sleep(1000);
			this.value = index;
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ReadWriteLockDemo demo = new ReadWriteLockDemo();
		
		Runnable readRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					int value = demo.handleRead(readLock);
					//int value = demo.handleRead(lock);
					System.out.println(value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Runnable writeRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					demo.handleWrite(writeLock, new Random().nextInt(30));
					//demo.handleWrite(lock, new Random().nextInt(30));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		for(int i = 18; i < 20; i++) {
			new Thread(writeRunnable).start();
		}
		
		for(int i = 0; i < 18; i++) {
			new Thread(readRunnable).start();
		}
		
	}
}
