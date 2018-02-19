package org.wzp.chapter3;

import java.util.concurrent.locks.*;

/**
 * 公平锁。一般的锁都是非公平，因为效率更好，一个线程会倾向于再次获得已经持有的锁
 * @author wzp
 *
 */
public class FairLock implements Runnable {
	//public static ReentrantLock fairLock = new ReentrantLock(true); // 公平锁
	public static ReentrantLock fairLock = new ReentrantLock(); 
	@Override
	public void run() {
		while(true) {
			try {
				fairLock.lock();
				System.out.println("--" + Thread.currentThread().getName() + "：获得锁");
			} finally {
				fairLock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		FairLock r1 = new FairLock();
		Thread t1 = new Thread(r1, "Thread_t1");
		Thread t2 = new Thread(r1, "Thread_t2");
		t1.start();
		t2.start();
	}

}
