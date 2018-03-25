package org.wzp.chapter4;
/**
 * 带权重边的数据类型
 * @author wzp
 * @date: 2018年3月25日 下午11:27:55 
 *
 */
public class Edge {
	private final int v; // 顶点之一
	private final int w; // 另一个顶点
	private final double weight; // 边的权重
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	/**
	 * 边的权重
	 * @return
	 */
	public double weight() {
		return weight;
	}
	/**
	 * 边两端的顶点之一
	 * @return
	 */
	public int either() {
		return v;
	}
	/**
	 * 另一个顶点
	 * @param vertex
	 * @return
	 */
	public int other(int vertex) {
		if(vertex == v)
			return w;
		else if(vertex == w)
			return v;
		else throw new RuntimeException("Inconsistent edge");
	}
	/**
	 * 将这条边与 that 比较
	 * @param that
	 * @return
	 */
	public int compareTo(Edge that) {
		if(this.weight() < that.weight())
			return -1;
		else if(this.weight() > that.weight())
			return +1;
		else
			return 0;
	}
	
	public String toString() {
		return String.format("%d-%d %.2f", v, w, weight);
	}
}
