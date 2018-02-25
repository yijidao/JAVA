package org.wzp.chapter4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * ThradLocal 就是典型的以空间换时间的做法
 * 所以当我们使用线程池时，线程不会退出，若没有及时对线程的ThreadLocal 进行gc，则容易发现内存泄露
 * ThreadLocal 通知GC有两种方法，1：ThreadLocal.remove();2.跟普通对象一样，使用 obj o = null
 * @author wzp
 * @date: 2018年2月25日 下午11:36:45 
 *
 */
public class ThreadLocalDemo_Gc {
	static volatile ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>() {
		protected void finalize() throws Throwable {
			System.out.println("--11111111" + this.toString() + " is gc");
		}
	};
	static volatile CountDownLatch cd = new CountDownLatch(10000);
	public static class ParseDate implements Runnable{
		int i = 0;
		public ParseDate(int i) {
			this.i = i;
		}
		@Override
		public void run() {
			try {
				if(t1.get() == null) {
					t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") {
						protected void finalize() throws Throwable {
							System.out.println("--222222" + this.toString() + " is gc");
						}
					});
					System.out.println("--" + Thread.currentThread().getId() + ":create SimpleDateFormat");
				}
				Date t = t1.get().parse("2018-02-25 20:20:" + i % 60);
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				cd.countDown();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 10000; i++) {
			es.execute(new ParseDate(i));
		}
		cd.await();
		System.out.println("--mission complement!");
		t1 = null;
		System.gc();
		System.out.println("--first GC complement!");
		t1 = new ThreadLocal<SimpleDateFormat>(){
			protected void finalize() throws Throwable {
				System.out.println("--3333333333" + this.toString() + " is gc");
			}};
		cd = new CountDownLatch(10000);
		for(int i = 0; i < 10000; i++) {
			es.execute(new ParseDate(i));
		}
		cd.await();
		Thread.sleep(1000);
		es.shutdown(); // 关闭线程池才会启动GC
//		t1 = null;
		System.gc();
		System.out.println("--second GC complement!");
	}
}
