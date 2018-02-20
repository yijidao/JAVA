package org.wzp.chapter3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁Condition的使用
 * @author wzp
 *
 */
public class ReenterLockCondition implements Runnable {
	public static ReentrantLock lock = new ReentrantLock();
	public static Condition condition = lock.newCondition();
	@Override
	public void run() {
		try {
			lock.lock();
			condition.await(); // 1.会释放锁；2.condition使用之前必须先获得锁
			System.out.println("--线程被唤醒");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ReenterLockCondition r1 = new ReenterLockCondition();
		Thread t1 =  new Thread(r1);
		t1.start();
		Thread.sleep(2000);
		lock.lock(); // condition 使用之前，该线程必须先获得锁
		condition.signal(); // 唤醒线程
		lock.unlock(); // 如果没有释放锁，则 t1 线程没有获得锁会一直请求锁
	}
}
