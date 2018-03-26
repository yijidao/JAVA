package org.wzp.chapter4;
/**
 * 最小生成树的 Prim 算法的延时实现（普里姆算法）
 * 普里姆算法简介：https://zh.wikipedia.org/wiki/%E6%99%AE%E6%9E%97%E5%A7%86%E7%AE%97%E6%B3%95
 * Prim 算法的每一步，都会为一棵生长中的树添加一条边
 * 一开始这个树只有一个顶点，然后会向它添加 V-1 条边，每次总是将下一条连接树中的顶点与不在树中的顶点且权重最小的边加入树中
 * 
 * @author wzp
 * @date: 2018年3月26日 上午11:20:23 
 *
 */

import org.wzp.chapter1.NodeQueue;
import org.wzp.chapter2.MinPQ;

public class LazyPrimMST {
	private  boolean[] marked; // 最小生成树的顶点
	private NodeQueue<Edge> mst; // 最小生成树的边
	private MinPQ<Edge> pq; // 横切边（包括失效的边）
	
	public LazyPrimMST(EdgeWeightedGraph G) {
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		mst = new NodeQueue<Edge>();
		
		visit(G, 0);
		while(!pq.isEmpty()) {
			Edge e = pq.delMin();
			
			int v = e.either(), w = e.other(v);
			if(marked[v] && marked[w])
				continue;
			mst.enqueue(e);
			if(!marked[v])
				visit(G, v);
			if(!marked[w])
				visit(G, w);
		}
	}
	/**
	 * 标记顶点v,并将所有连接 v 和未被标记顶点的边加入 pq
	 * @param G
	 * @param v
	 */
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for(Edge e : G.adj(v))
			if(!marked[e.other(v)])
				pq.insert(e);
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
}
