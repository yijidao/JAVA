package org.wzp.chapter4;
/**
 * 有向图顶点对的可达性
 * @author wzp
 * @date: 2018年3月25日 下午9:43:16 
 *
 */
public class TransitiveClosure {
	private DirectedDFS[] all;
	public TransitiveClosure(Digraph G) {
		all = new DirectedDFS[G.V()];
		for(int v = 0; v < G.V(); v++) {
			all[v] = new DirectedDFS(G, v);
		}
	}
	
	/**
	 * w 是从 v 可到达的吗
	 * @param v
	 * @param w
	 * @return
	 */
	boolean reachable(int v, int w) {
		return all[v].marked(w);
	}
}
