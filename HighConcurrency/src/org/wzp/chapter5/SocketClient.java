package org.wzp.chapter5;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Socket 客户端
 * @author wzp
 * @date: 2018年3月3日 下午3:45:55 
 *
 */
public class SocketClient {
	public static void main(String[] args) throws IOException, InterruptedException {
		Socket client = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		
		try {
			client = new Socket();
			client.connect(new InetSocketAddress("localhost", 8000));
			writer = new PrintWriter(client.getOutputStream(), true);
			writer.println("--Hello Server!");
			
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println("--from Server:" + reader.readLine());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(writer != null) {
				writer.close();
			}
			if(reader != null) {
				reader.close();
			}
			if(client != null) {
				client.close();
			}
		}
		
	}
}
