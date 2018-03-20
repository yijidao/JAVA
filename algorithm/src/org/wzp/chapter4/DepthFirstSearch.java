package org.wzp.chapter4;

import org.wzp.chapter1.NodeStack;

/**
 * 深度优先搜索
 * @author wzp
 * @date: 2018年3月20日 下午11:09:19 
 *
 */
public class DepthFirstSearch {
	private boolean[] marked; // 与 s 是否连通
	private int count; // 与s连通的顶点总数
	private int[] edgeTo; // 从起点到一个顶点的已知路径上的最后一个顶点
	private final int s; // 起点
	
	
	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	
	/**
	 * 将链表中与 v 连通的点迭代出来，并且全部设置为 true
	 * @param G
	 * @param v
	 */
	private void dfs(Graph G, int v) {
		marked[v] = true;
		count++;
		for(int w : G.adj(v))
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
	}
	
	/**
	 * s 和 w 是连通的吗 
	 * @param w
	 * @return
	 */
	private boolean marked(int w) {
		return marked[w];
	}
	
	/**
	 * 与 s 连通的顶点总数
	 * @return
	 */
	public int count() {
		return count;
	}
	
	/**
	 * 是否存在 s 到 v 的路径
	 * @param v
	 * @return
	 */
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	/**
	 * 返回 s 到 v 的路径，如果不存在，返回 null
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v))
			return null;
		NodeStack<Integer> path = new NodeStack<Integer>();
		for(int x= v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
}
