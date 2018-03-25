package org.wzp.chapter4;
/**
 * 拓扑排序
 * 一副有向无环图的拓扑排序就是所有顶点的逆有序排序
 * @author wzp
 * @date: 2018年3月25日 下午4:54:32 
 *
 */
public class Topological {
	private Iterable<Integer> order;
	
	public Topological(Digraph G) {
		DirectedCycle cycle = new DirectedCycle(G);
		if(!cycle.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order(){
		return order;
	}
	
	public boolean isDAG() {
		return order != null;
	}
}
