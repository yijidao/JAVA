package org.wzp.chapter2;
/**
 * Suspend 和 Resume 正确的使用方法 (使用wait 和 notify)
 * @author wzp
 *
 */
public class GoodSuspend {
	public static Object u = new Object();
	
	public static class ChangeObjectThread extends Thread{
		volatile boolean suspendme = false;
		
		public void suspendMe() {
			suspendme = true;
		}
		
		public void resumeMe() {
			suspendme = false;
			synchronized (this) {
				notify();
			}
		}
		
		@Override
		public void run() {
			while(true) {
				synchronized (this) {
					while(suspendme) {
						try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
				synchronized (u) {
					System.out.println("--in ChangeObjectThread");
				}
				Thread.yield();
			}
		}
	}
	
	public static class ReadObejctThread extends Thread{
		@Override
		public void run() {
			while(true) {
				synchronized (u) {
					System.out.println("--in ReadObjectThread");
				}
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		ChangeObjectThread T1 = new ChangeObjectThread();
		ReadObejctThread T2 = new ReadObejctThread();
		T1.start();
		T2.start();
		Thread.sleep(1000);
		T1.suspendMe();
		System.out.println("suspend t1 2 sec");
		Thread.sleep(2000);
		System.out.println("resume t1");
		T1.resumeMe();
	}
}
