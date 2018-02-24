package org.wzp.chapter3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * 线程池类有调度任务方法，其中有两个方法值得注意一下，方法签名如下
 * public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit);
 * 这个方法会在初始延迟之后，定时地执行某个线程任务， 延迟+周期+周期+周期
 * public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit);
 * 这个方法每次执行线程的时间都是 (延迟+周期)+(延迟+周期)+(延迟+周期)+(延迟+周期)
 * @author wzp
 * @date: 2018年2月24日 上午10:08:58 
 *
 */

public class ScheduleExecutorServiceDemo {
	public static void main(String[] args) {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);	
		ses.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					//Thread.sleep(8000); // 如果执行时间大于指定的周期，则实际周期以执行时间为准
					System.out.println("--" + System.currentTimeMillis()/1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}, 0, 2, TimeUnit.SECONDS);
	}
}
