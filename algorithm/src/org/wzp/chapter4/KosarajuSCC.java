package org.wzp.chapter4;
/**
 * 计算强连通分量的Kosaraju算法
 * 1. 先计算出反向图
 * 2. 对反向图进行逆后序排序
 * 3. 进行深度搜索
 * @author wzp
 * @date: 2018年3月25日 下午9:18:59 
 *
 */
public class KosarajuSCC {
	private boolean[] marked;
	private int[] id;
	private int count;
	
	public KosarajuSCC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		DepthFirstOrder order = new DepthFirstOrder(G.reverse());
		for(int s : order.reversePost())
			if(!marked[s]) {
				dfs(G, s);
				count++;
			}
	}
	
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v))
			if(!marked[w])
				dfs(G, w);
	}
	/**
	 * v 和 w 是强连通吗
	 * @param v
	 * @param w
	 * @return
	 */
	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}
	/**
	 * v 所在强连通分量的标识符
	 * @param v
	 * @return
	 */
	public int id(int v) {
		return id[v];
	}
	/**
	 * 强连通分类总数
	 * @return
	 */
	public int count() {
		return count;
	}
}
