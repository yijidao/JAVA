package org.wzp.chapter4;
/**
 * 利用深度优先搜索检查G是否是无环图
 * @author wzp
 * @date: 2018年3月21日 下午11:29:24 
 *
 */
public class Cycle {
	private boolean[] marked;
	private boolean hasCycle;
	public Cycle(Graph G) {
		marked = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s])
				dfs(G, s, s);
		}
	}
	
	private void dfs(Graph G, int v, int u) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w, v);
			else if(w != u)
				hasCycle = true;
		}
	}
	
	public boolean hasCycle() {
		return hasCycle;
	}
}
