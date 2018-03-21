package org.wzp.chapter4;

import org.wzp.chapter1.Bag;
import org.wzp.util.In;
import org.wzp.util.StdOut;

/**
 * 使用深度优先搜索找出图中的所有连通分量
 * @author wzp
 * @date: 2018年3月21日 上午11:34:30 
 *
 */
public class CC {
	private boolean[] marked;
	private int[] id;
	private int count;
	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s]) {
				dfs(G, s);
				count++;
			}
		}
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
	}
	/**
	 * v 和 w 是否连通
	 * @param v
	 * @param w
	 * @return
	 */
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}
	/**
	 * v 所在的连通分量的标识符(0 ~ count() -1)
	 * @param v
	 * @return
	 */
	public int id(int v) {
		return id[v];
	}
	/**
	 * 连通分量数
	 * @return
	 */
	public int count() {
		return count;
	}
	/**
	 * 测试用例
	 * @param args
	 */
	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		CC cc = new CC(G);
		
		int M = cc.count;
		StdOut.print(M + "componet");
		
		Bag<Integer>[] components;
		components = (Bag<Integer>[])new Bag[M];
		for(int i = 0; i < M; i++) {
			components[i] = new Bag<Integer>();
		}
		for(int v= 0; v < G.V(); v++) {
			components[cc.id(v)].add(v);
		}
		for(int i = 0; i < M; i++) {
			for(int v : components[i])
				StdOut.print(v + " ");
			StdOut.println();
		}
	}
}
