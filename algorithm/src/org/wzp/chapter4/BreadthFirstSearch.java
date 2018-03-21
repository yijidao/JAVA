package org.wzp.chapter4;

import org.wzp.chapter1.NodeQueue;
import org.wzp.chapter1.NodeStack;

/**
 * 广度优先搜索
 * 广度优先搜索和深度优先搜索的不同之处，仅在于从数据结构中获取下一个顶点的规则
 * 对广度优先搜索来说，获取下一个顶点的规则是最早加入的顶点
 * 对深度优先搜索来说，获取下一个顶点的规则是最晚加入的顶点
 * @author wzp
 * @date: 2018年3月21日 上午10:52:39 
 *
 */
public class BreadthFirstSearch {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	public BreadthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	private void bfs(Graph G, int s) {
		NodeQueue<Integer> queue = new NodeQueue<Integer>();
		marked[s] = true;
		queue.enqueue(s);;
		while(!queue.isEmpty()) {
			int v = queue.dequeue();
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					edgeTo[w] = v;
					marked[w] = true;
					queue.enqueue(w);
				}
			}
		}
	}
	
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
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
