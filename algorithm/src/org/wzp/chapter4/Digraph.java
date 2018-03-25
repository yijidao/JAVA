package org.wzp.chapter4;
/**
 * 有向图
 * @author wzp
 * @date: 2018年3月22日 下午11:53:24 
 *
 */

import org.wzp.chapter1.Bag;

public class Digraph {
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	
	public Digraph(int V) {
		this.V = V;
		this.E = E;
		adj = (Bag<Integer>[])new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	/**
	 * 向有向图添加一条边 v -> w
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w);
		E++;
	}
	/**
	 * 由 v 指出的边所连接的所有顶点
	 * @param v
	 * @return
	 */
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	/**
	 * 返回有向图的副本，并将其中所有的方向反转
	 * @return
	 */
	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for(int v = 0; v < V; v++) {
			for(int w : adj(v))
				R.addEdge(w, v);
		}
		return R;
	}
}
