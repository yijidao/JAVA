package org.wzp.chapter4;
/**
 * 深度优先搜索
 * @author wzp
 * @date: 2018年3月20日 下午11:09:19 
 *
 */
public class DepthFirstSearch {
	private boolean[] marked;
	private int count;
	
	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
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
			if(!marked[w])
				dfs(G, w);
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
}
