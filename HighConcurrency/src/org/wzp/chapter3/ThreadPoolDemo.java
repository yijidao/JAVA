package org.wzp.chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池Demo
 * 大量反复的新建和销毁线程，会对CPU造出负担，所以根据数据库连接池的模式，线程则有对应的线程池
 * 线程池执行类：ThreadPoolExecetor
 * 工厂类：Exectors，方法签名如下：
 * newFixedThreadPool(int nThreads)
 * newCachedThreadPool()
 * newSingleThreadExecutor()
 * newSingleThreadScheduledExecutor()
 * @author wzp
 * @date: 2018年2月24日 上午9:43:51 
 *
 */
public class ThreadPoolDemo {
	public static class MyTask implements Runnable{

		@Override
		public void run() {
			System.out.println("--" + System.currentTimeMillis() + ":ThreadID:" + Thread.currentThread().getId());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MyTask task = new MyTask();
		ExecutorService es = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 10; i++) {
			es.submit(task);
		}
	}
}
