package org.wzp.chapter4;

import org.wzp.chapter1.NodeStack;

/**
 * 寻找有向环
 * @author wzp
 * @date: 2018年3月23日 下午5:24:47 
 *
 */
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private NodeStack<Integer> cycle; // 有向环中的所有顶点
	private boolean[] onStack; // 递归调用栈上所有顶点
	
	public DirectedCycle(Digraph G) {
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v])
				dfs(G, v);
		}
	}
	
	private void dfs(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for(int w : G.adj(v)) 
			if(this.hasCycle())
				return;
			else if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			} else if(onStack[w]) {
				cycle = new NodeStack<Integer>();
				for(int x = v; x != w; x= edgeTo[x])
					cycle.push(x);
				cycle.push(w);
				cycle.push(v);
			}
		
		onStack[v] = false;
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Integer> cycle(){
		return cycle;
	}
}
