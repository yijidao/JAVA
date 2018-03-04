package org.wzp.chapter5;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.*;

/**
 * 基于 NIO 的Socket 服务器
 * 基于 NIO 的Socket 服务器，核心就是一个 selector(使用轮训的方式) 管理多个 selectable Channel
 * 使用一个线程去管理 selector，事件触发后，再新建线程去执行对应 channel 的操作
 * 这样的好处就是可以形成了一个使用一个或者极少数线程，去处理大量客户端连接的结构(注意：这一个只是处理客户端连接) 
 * @author wzp
 * @date: 2018年3月4日 下午3:59:30 
 *
 */
public class NIOServer {
	private static Selector selector; 
	private static ExecutorService tp = Executors.newCachedThreadPool();
	public static Map<Socket, Long> time_stat = new HashMap<Socket, Long>(10240); // 用来计时
	private static void startServer() throws Exception {
		selector = SelectorProvider.provider().openSelector();
		ServerSocketChannel ssc = ServerSocketChannel.open(); // 表示服务端的 SocketChannel 实例
		ssc.configureBlocking(false); // 非阻塞模式
		
		InetSocketAddress isa = new InetSocketAddress("localhost", 8000);
		ssc.socket().bind(isa); // 绑定在 127.0.0.0:8000
		SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT); // 注册感兴趣的事件
		
		// 等待分发网络消息
		for(;;) {
			selector.select(); // select() 是一个阻塞的方法，如果没有任何数据准备好，就阻塞；如果准备好了，就返回SelectKey 的数量
			Set<SelectionKey> readyKeys = selector.selectedKeys(); // selectedKeys 返回所有的SelectionKey,所以这里用Set
			Iterator<SelectionKey> i = readyKeys.iterator();
			long e = 0;
			while(i.hasNext()) {
				SelectionKey sk = (SelectionKey) i.next();
				i.remove();
				
				if(sk.isAcceptable()) {
					doAccept(sk);
				}
				else if(sk.isValid() && sk.isReadable()) {
					if(!time_stat.containsKey(((SocketChannel)sk.channel()).socket())) {
						time_stat.put(((SocketChannel)sk.channel()).socket(), System.currentTimeMillis());
						doRead(sk);
					}
				}
				else if(sk.isValid() && sk.isWritable()) {
					doWrite(sk);
					e = System.currentTimeMillis();
					long b = time_stat.remove(((SocketChannel)sk.channel()).socket());
					System.out.println("--spend:" + (e - b) + "ms");
				}
			}
		}
	}
	
	private static void doAccept(SelectionKey sk) {
		ServerSocketChannel server = (ServerSocketChannel) sk.channel();
		SocketChannel clientChannel;
		try {
			clientChannel = server.accept();
			clientChannel.configureBlocking(false);
			
			SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
			
			EchoClient echoClient = new EchoClient();
			clientKey.attach(echoClient);
			
			InetAddress clientAddress = clientChannel.socket().getInetAddress();
			System.out.println("--Accept connection from " + clientAddress.getHostAddress() + ".");
		} catch(Exception e) {
			System.out.println("--Failed to accept new client.");
			e.printStackTrace();
		}
	}
	
	private static void doRead(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		ByteBuffer bb = ByteBuffer.allocate(8192);
		int len;
		
		try {
			len = channel.read(bb);
			if(len < 0) {
				disconnect(sk);
				return;
			}
		} catch(Exception e) {
			System.out.println("Failed to read from client");
			e.printStackTrace();
			disconnect(sk);
			return;
		}
		
		bb.flip();
		tp.execute(new HandleMsg(sk, bb));
	}
	
	private static void doWrite(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		EchoClient echoClient = (EchoClient) sk.attachment();
		LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();
		
		ByteBuffer bb = outq.getLast();
		try {
			int len = channel.write(bb);
			if(len == -1) {
				disconnect(sk);
				return;
			}
			if(bb.remaining() == 0) {
				outq.removeLast();
			}
		} catch (Exception e) {
			System.out.println("--Failed to write to client.");
			e.printStackTrace();
			disconnect(sk);
		}
		
		if(outq.size() == 0) {
			sk.interestOps(SelectionKey.OP_READ);
		}
	}
	
	private static void disconnect(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static class EchoClient {
		private LinkedList<ByteBuffer> outq;
		public EchoClient() {
			outq = new LinkedList<ByteBuffer>();
		}
		public LinkedList<ByteBuffer> getOutputQueue(){
			return outq;
		}
		public void enqueue(ByteBuffer bb) {
			outq.addFirst(bb);
		}
	}
	
	static class HandleMsg implements Runnable{
		SelectionKey sk;
		ByteBuffer bb;
		public HandleMsg(SelectionKey sk, ByteBuffer bb) {
			this.sk = sk;
			this.bb = bb;
		}
		@Override
		public void run() {
			EchoClient echoClient = (EchoClient) sk.attachment();
			echoClient.enqueue(bb);
			sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			selector.wakeup();
		}
	}
	
	public static void main(String[] args) throws Exception {
		startServer();
	}
}
