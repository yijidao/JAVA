package org.wzp.chapter4;

import org.wzp.chapter1.Bag;

/**
 * 加权无向图的数据类型
 * @author wzp
 * @date: 2018年3月25日 下午11:38:21 
 *
 */
public class EdgeWeightedGraph {
	private final int V;
	private int E;
	private Bag<Edge>[] adj;
	
	public EdgeWeightedGraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[])new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Edge>();
	}
	/**
	 * 顶点数
	 * @return
	 */
	public int V() {
		return V;
	}
	/**
	 * 图的边数
	 * @return
	 */
	public int E() {
		return E;
	}
	/**
	 * 向图中添加一条边e
	 * @param e
	 */
	public void addEdge(Edge e) {
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	/**
	 * 与 v 相关联的所有边
	 * @param v
	 * @return
	 */
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
}
