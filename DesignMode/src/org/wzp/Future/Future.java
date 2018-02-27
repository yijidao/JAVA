package org.wzp.Future;
/**
 * Future 模式
 * Future 是多线程开发中一种常用的设计模式，核心思想是异步调用
 * 组成：Data(数据接口)、FutureData(立刻返回的数据)、RealData(真实的数据，会比较慢)、Client(发起请求的客户端)、Main(系统启动，调用Client 发起请求)
 * JDK 中有复杂实现，Future 和  Callable，通过复写Callable 来实现业务的算法  
 * @author wzp
 * @date: 2018年2月27日 下午2:26:39 
 *
 */
public class Future {
	
	public interface Data{
		public String getResult();
	}
	
	public static class RealData implements Data {
		protected final String result;
		public RealData(String para) { // 模拟耗时的操作
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < 10; i++) {
				sb.append(para);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			result = sb.toString();
		}
		@Override
		public String getResult() {
			return result;
		}
	}
	
	public static class FutureData implements Data{
		protected RealData realdata = null;
		protected boolean isReadly = false;
		public synchronized void setRealData(RealData realdata) {
			if(isReadly) {
				return;
			}
			isReadly = true;
			this.realdata = realdata;
			notifyAll(); // 唤醒所有等待的线程
		}
		@Override
		public synchronized String getResult() {
			while(!isReadly) {
				try {
					wait(); // 等待 RealData 执行完毕
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return realdata.result;
		}
	}
	
	public static class Client{
		public Data request(final String queryStr) {
			final FutureData future = new FutureData();
			new Thread() {
				@Override
				public void run() {
					RealData real = new RealData(queryStr);
					future.setRealData(real);
				}
			}.start();
			return future;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Client client = new Client();
		Data data = client.request("name");
		System.out.println("--请求完毕");
		Thread.sleep(2000); // 模拟其他操作
		System.out.println("--数据：" + data.getResult());
	}
}
