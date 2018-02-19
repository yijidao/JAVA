package org.wzp.chapter3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;
/**
 * 限时锁Demo
 * @author wzp
 *
 */
public class TimeLock implements Runnable {
	public static ReentrantLock lock = new ReentrantLock();
	@Override
	public void run() {
		try {
			if(lock.tryLock(5, TimeUnit.SECONDS)) { // 等待5s，如果使用无参，就会立刻返回
				System.out.println("--" + Thread.currentThread().getId() + "获得锁");
				Thread.sleep(6000);
			}
			else {
				System.out.println("--" + Thread.currentThread().getId() + "请求锁失败");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(lock.isHeldByCurrentThread())
				lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		TimeLock l1 = new TimeLock();
		Thread t1 = new Thread(l1);
		Thread t2 = new Thread(l1);
		t1.start();
		t2.start();
	}
}
