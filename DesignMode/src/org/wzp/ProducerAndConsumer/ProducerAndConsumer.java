package org.wzp.ProducerAndConsumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者模式是一个典型的多线程应用
 * 主要是由共享数据模型，内存缓存区，消费者线程，生产者线程组成
 * 内存缓存区由BlockingQueue 充当，BlockingQueue 有两种实现，ArrayBlockingQueue(基于数组) 和 LinkBlockingQueue(基于链表，易于扩大容量)
 * @author wzp
 * @date: 2018年2月27日 下午12:01:32 
 *
 */
public class ProducerAndConsumer {
	/**
	 * 共享数据模型
	 * @author wzp
	 * @date: 2018年2月27日 上午11:06:56 
	 *
	 */
	public static class PCData{
		private final int intData;
		public PCData(int d) {
			this.intData = d;
		}
		public PCData(String d) {
			this.intData = Integer.valueOf(d);
		}
		public int getPCData() {
			return intData;
		}
		@Override
		public String toString() {
			return "--data:" + intData;
		}
	}
	/**
	 * 生产者
	 * @author wzp
	 * @date: 2018年2月27日 上午11:06:50 
	 *
	 */
	public static class Producer implements Runnable {
		private volatile boolean isRunnable = true;
		private BlockingQueue<PCData> queue;
		private static final int SLEEPTIME = 1000;
		private static AtomicInteger count = new AtomicInteger();
		
		public Producer(BlockingQueue<PCData> queue) {
			this.queue = queue;
		}
		
		public void stop() {
			isRunnable = false;
		}
		
		@Override
		public void run() {
			PCData data = null;
			Random r = new Random();
			System.out.println("--start producter:" + Thread.currentThread().getId());
			try {
				while(isRunnable) {
					Thread.sleep(r.nextInt(SLEEPTIME));
					data = new PCData(count.incrementAndGet());
					if(queue.offer(data, 2, TimeUnit.SECONDS)) {
						System.out.println("--" + data + " is put into queue");
					} else {
						System.out.println("--failed to put data" + data);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public static class Consumer implements Runnable {
		private volatile boolean isRunnable = true;
		private BlockingQueue<PCData> queue;
		private static final int SLEEPTIME = 1000;
		
		public Consumer(BlockingQueue<PCData> queue) {
			this.queue = queue;
		}
		
		public void stop() {
			isRunnable = false;
		}

		@Override
		public void run() {
			try {
				Random r = new Random();
				System.out.println("--start consumer:" + Thread.currentThread().getId());
				
				while(isRunnable) {
					PCData data = queue.take();
					if(data != null) {
						int re = data.getPCData() * data.getPCData();
						System.out.println(MessageFormat.format("--{0}*{1}={2}", data.getPCData(), data.getPCData(), re));
						Thread.sleep(r.nextInt(SLEEPTIME));
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>(10);
		
		Producer producer1 = new Producer(queue);
		Producer producer2 = new Producer(queue);
		Producer producer3 = new Producer(queue);
		Consumer consumer1 = new Consumer(queue);
		Consumer consumer2 = new Consumer(queue);
		Consumer consumer3 = new Consumer(queue);
		
		es.execute(producer1);
		es.execute(producer2);
		es.execute(producer3);
		es.execute(consumer1);
		es.execute(consumer2);
		es.execute(consumer3);
		
		Thread.sleep(10 * 1000);
		
		producer1.stop();
		producer2.stop();
		producer3.stop();
		
		Thread.sleep(3 * 1000);
		es.shutdown();
	}
}
