package org.wzp.chapter3;

import java.util.Random;
import java.util.concurrent.*;

/**
 * CountDownLatch 倒计时器Demo
 * CountDownLatch 就是火箭发射前的对仪器的检查一样
 * @author wzp
 * @date: 2018年2月22日 下午9:25:31 
 *
 */
public class CountDownLatchDemo implements Runnable {
	static final CountDownLatch end = new CountDownLatch(10);
	static final CountDownLatchDemo demo = new CountDownLatchDemo();
	
	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10) * 1000); // 模拟火箭检查
			System.out.println("--check complete");
			end.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 10; i++) {
			exec.submit(demo);
		}
		end.await(); // 等待检查完毕
		System.out.println("--Fire!");
		exec.shutdown();
	}
}
