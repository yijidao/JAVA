package org.wzp.chapter3;

import java.util.concurrent.*;

/**
 * Semaphore 信号量的简单DEMO
 * @author wzp
 * @date: 2018年2月21日 上午12:16:55 
 *
 */
public class SemapDemo implements Runnable {
	final Semaphore semp = new Semaphore(5); // 同时可以有五个线程，访问同一临界资源

	@Override
	public void run() {
		try {
			semp.acquire();
			Thread.sleep(2000);
			System.out.println("--" + Thread.currentThread().getId() + ":done!");
			semp.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SemapDemo demo = new SemapDemo();
		for(int i = 0; i < 20; i++) {
			exec.submit(demo);
		}
	}
	
}
