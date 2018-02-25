package org.wzp.chapter4;

import java.text.*;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal Demo
 * @author wzp
 * @date: 2018年2月24日 下午12:01:56 
 *
 */
public class ThreadLocalDemo {
//	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
	static ThreadLocal<SimpleDateFormat> t1 = new ThreadLocal<SimpleDateFormat>();
	public static class ParseDate implements Runnable{
		int i;
		public ParseDate(int i) {
			this.i = i;
		}
		@Override
		public void run() {
			try {
//				Date t = sdf.parse("2018-02-23 17:49:" + i % 60);
				if(t1.get() == null) {
					t1.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:SS"));
				}
				Date t = t1.get().parse("2018-02-23 17:49:" + i*60 );
				System.out.println("--" + i + ":" + t);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 1000; i++) {
			es.execute(new ParseDate(i));
		}
	}
}
