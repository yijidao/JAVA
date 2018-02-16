package org.wzp.chapter2;

public class Interrupt{
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				while(true)
				{
					if(Thread.currentThread().isInterrupted())
					{
						System.out.println("--Interrupt");
						break;
					}
					try {
						Thread.sleep(500);
						System.out.println("--run");
					} catch (InterruptedException e) {
						System.out.println("Interrupt When Sleep");
						Thread.currentThread().interrupt();
					}
					//Thread.yield();
				}
			};
		};
		t1.start();
		Thread.sleep(2000);
		//t1.interrupt();
	}
}
