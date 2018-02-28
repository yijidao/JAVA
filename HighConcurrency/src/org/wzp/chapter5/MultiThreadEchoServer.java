package org.wzp.chapter5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
/**
 * SocketServer
 * @author wzp
 * @date: 2018年2月28日 下午11:45:22 
 *
 */
public class MultiThreadEchoServer {
	private static ExecutorService tp = Executors.newCachedThreadPool();
	
	static class HandleMsg implements Runnable {

		Socket clientSocket;
		public HandleMsg(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}
		
		@Override
		public void run() {
			BufferedReader is = null;
			PrintWriter os = null;
			
			try {
				is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				os = new PrintWriter(clientSocket.getOutputStream(), true);
				String inputLine = null;
				long b = System.currentTimeMillis();
				while((inputLine = is.readLine()) != null) {
					os.print(inputLine);
				}
				long e = System.currentTimeMillis();
				System.out.println("--spend:" + (e-b) + "ms");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(is != null) is.close();
					if(os != null) os.close();
					clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ServerSocket echoServer = null;
		Socket clientSocket = null;
		try {
			echoServer = new ServerSocket(8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			try {
				clientSocket = echoServer.accept();
				System.out.println("--" + clientSocket.getRemoteSocketAddress() + "connect!");
				tp.execute(new HandleMsg(clientSocket));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
