package org.wzp.chapter4;

import org.wzp.chapter2.IndexMinPQ;

import com.sun.org.apache.bcel.internal.classfile.Visitor;

/**
 * 最小生成树 Prim 算法（即时版本）
 * 普里姆算法简介：https://zh.wikipedia.org/wiki/%E6%99%AE%E6%9E%97%E5%A7%86%E7%AE%97%E6%B3%95
 * @author wzp
 * @date: 2018年3月26日 下午4:23:47 
 *
 */
public class PrimMST {
	private Edge[] edgeTo; // 距离树最近的点
	private double[] distTo; // distTo[w] = edgeTo[w].weight()
	private boolean[] marked; // 
	private IndexMinPQ<Double> pq; // 有效的横切边
	
	public PrimMST(EdgeWeightedGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		
		for(int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		pq = new IndexMinPQ<Double>(G.V());
		
		distTo[0] = 0.0;
		pq.insert(0, 0.0); // 用顶点 0 和权重 0 初始化 pq
		while(!pq.isEmpty()) {
			visit(G, pq.delMin()); // 将最近的点添加到树中 
		}
	}
	
	private void visit(EdgeWeightedGraph G, int v) {
		marked[v] = true;
		for(Edge e : G.adj(v)) {
			int w = e.other(v);
			
			if(marked[w])
				continue;
			if(e.weight() < distTo[w]) {
				edgeTo[w] = e;
				
				distTo[w] = e.weight();
				if(pq.contains(w))
					pq.change(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}
}
