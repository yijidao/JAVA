package org.wzp.chapter5;

import java.io.*;
import java.net.*;
import java.net.UnknownHostException;
import java.util.concurrent.*;

public class HeavySocketClient {
	private static ExecutorService tp = Executors.newCachedThreadPool();
	public static class EchoClient implements Runnable {
		@Override
		public void run() {
			Socket client = null;
			PrintWriter writer = null;
			BufferedReader reader = null;
			try {
				client = new Socket();
				client.connect(new InetSocketAddress("localhost", 8000));
				writer = new PrintWriter(client.getOutputStream(), true);
				try {
					writer.print("H");
					Thread.sleep(1000);
					writer.print("e");
					Thread.sleep(1000);
					writer.print("l");
					Thread.sleep(1000);
					writer.print("l");
					Thread.sleep(1000);
					writer.print("o");
					Thread.sleep(1000);
					writer.print("!");
					Thread.sleep(1000);
					writer.println();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				writer.flush();
				reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				System.out.println("--from Server:" + reader.readLine());
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(writer != null) {
						writer.close();
					}
					if(reader != null) {
						reader.close();
					}
					if(client != null) {
						client.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		EchoClient ec = new EchoClient();
		for(int i = 0; i < 10; i++) {
			tp.execute(ec);
		}
	}
}
