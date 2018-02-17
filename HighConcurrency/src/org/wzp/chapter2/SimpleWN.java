package org.wzp.chapter2;
/**
 * wait 和  notify，这两个方法都需要先获得对象的监视器
 * @author wzp
 *
 */
public class SimpleWN {
	final static Object object = new Object();
	/**
	 * wait 类
	 * @author wzp
	 *
	 */
	public static class T1 extends Thread
	{
		@Override
		public void run() {
			synchronized(object) {
				System.out.println("--" + System.currentTimeMillis() + ":T1 start!");
				try {
					System.out.println("--" + System.currentTimeMillis() + ":T1 wait!");
					object.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("--" + System.currentTimeMillis() + "T1 end!");
			}
		}
	}
	/**
	 * notify 类
	 * @author wzp
	 *
	 */
	public static class T2 extends Thread
	{
		@Override
		public void run() {
			synchronized(object) {
				System.out.println("--" + System.currentTimeMillis() + ":T2 start! notify one thread");
				object.notify();
				System.out.println("--" + System.currentTimeMillis() + "T2 end!");
				try {
					Thread.sleep(2000); // sleep 2s，让效果更明显，notify 和  wait 都需要获得对象监视器 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Thread T1 = new T1();
		Thread T2 = new T2();
		T1.start();
		T2.start();
	}
}
