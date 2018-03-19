package org.wzp.chapter4;

import org.wzp.chapter1.Bag;
import org.wzp.util.In;

/**
 * 使用邻接表数组实现无向图
 * 就是一个数组放着所有顶点，每个数组的格子里放着一个链表存放与其连接的顶点（这里的链表使用Bag实现）
 * @author wzp
 * @date: 2018年3月19日 下午11:46:43 
 *
 */
public class Graph {
	private final int V; // 顶点数目
	private int E; // 边的数目
	private Bag<Integer>[] adj; // 邻接表
	
	public Graph(int V){
		this.V = V;
		adj = (Bag<Integer>[])new Bag[V];
		for(int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	
	public Graph(In in) {
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}
	
	public int E() {
		return E;
	}
	
	public int V() {
		return V;
	}
	
	/**
	 * 形成一条表，就是将两个顶点互相加到对方的链表中去
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w); // 将w加入到v的链表中
		adj[w].add(v); // 将v加入到w的链表中
		E++;
	}
	
}
