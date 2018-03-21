package org.wzp.chapter4;
/**
 * 使用深度优先搜索检查二分图（双色问题）
 * 任意一条边的两个断点颜色不同
 * @author wzp
 * @date: 2018年3月21日 下午11:38:20 
 *
 */
public class TwoColor {
	private boolean[] marked;
	private boolean[] color;
	private boolean isTwoColorable = true;
	public TwoColor(Graph G) {
		marked = new boolean[G.V()];
		color = new boolean[G.V()];
		for(int s = 0; s < G.V(); s++) {
			if(!marked[s])
				dfs(G, s);
		}
	}
	
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w]) {
				color[w] = !color[v];
				dfs(G, w);
			}
			else if(color[w] == color[v])
				isTwoColorable = false;
		}
	}
	
	public boolean isBipartite() {
		return isTwoColorable;
	}
}
