package org.wzp.chapter4;

import java.util.concurrent.atomic.AtomicReference;
/**
 * 无锁的对象引用：AtomicReference
 * 模拟对象被反复修改，但是最终值还是与 期待值E 一样，导致无法判断
 * 解决方法可以使用加时间戳的无锁对象引用 AtomicStampedReference
 * @author wzp
 * @date: 2018年2月26日 下午4:23:08 
 *
 */
public class AtomicReferenceDemo {
	static AtomicReference<Integer> money = new AtomicReference<Integer>();

	public static void main(String[] args) {
		
		money.set(19);
		
		// 模拟充值线程
		for(int i = 0; i < 3; i++) {
			new Thread() {
				@Override
				public void run() {
					while(true) {
						Integer m = money.get();
						if(m < 20) {
							if(money.compareAndSet(m, m+20)) {
								System.out.println("--余额小于20元，重值成功，余额：" + money.get() + "元");
								break;
							}
						}else {
							System.out.println("--余额大于20元，无需充值");
						}
					}
				}
			}.start();
		}
		
		// 模拟消费线程
		for(int i = 0; i < 7; i++)
		{
			new Thread() {
				@Override
				public void run() {
					while(true) {
						Integer m = money.get();
						if(m > 10) {
							System.out.println("--余额大于10元");
							if(money.compareAndSet(m, m - 10)) {
								System.out.println("--消费10元，当前余额：" + money.get() + "元");
								break;
							}
						}
						else {
							System.out.println("--没有足够金额");
						}
					}
				}
			}.start();
		}
	}
}
