package org.wzp.chapter4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/**
 * 根据开闭原则，对扩展开放，对修改关闭，JDK 有 AtomicIntegerFieldUpdater 可以将一个实例的变量变成无锁的变量
 * 注意事项：1.Update 只能修改其可见范围的变量，也就是不能是private修饰的变量
 * 2. 为了变量能被正确读取，其变量必须是 volatile 的，反止类似 long 的错误修改
 * 3. 不支持 static 变量，因为 CSA 会直接操作实例的偏移量（Unsafe.objectFieldOffset()不支持静态变量）
 * @author wzp
 * @date: 2018年2月26日 下午5:03:40 
 *
 */
public class AtomicIntegerFieldUpdaterDemo {
	public static class Candidate{
		int id;
		volatile int score;
	}
	public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
	// 验证数据是否正确
	public static AtomicInteger allScore = new AtomicInteger(0);
	public static void main(String[] args) throws InterruptedException {
		final Candidate stu = new Candidate();
		Thread[] t = new Thread[10000];
		for(int i = 0; i < 10000; i++) {
			t[i] = new Thread() {
				@Override
				public void run() {
					if(Math.random() > 0.4) {
						scoreUpdater.incrementAndGet(stu);
						allScore.incrementAndGet();
					}
				}
			};
			t[i].start();
		}
		for(int i = 0; i < 10000; i++) {
			t[i].join();
		}
		System.out.println("--score = " + stu.score);
		System.out.println("--allScore=" + allScore);
	}
	
}
