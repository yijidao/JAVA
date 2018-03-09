package org.wzp.chapter1;

import org.wzp.util.StdIn;
import org.wzp.util.StdOut;

/**
 * 动态连通算法实现
 * @author wzp
 * @date: 2018年3月9日 上午10:34:30 
 *
 */
public class UnionFind {
	private int[] id; // 分量id（以触电作为索引）
	private int[] sz; // 连通分量的数量
	private int count; // 分量数量
	
	public UnionFind(int N) {
		count = N;
		id = new int[N];
		for(int i = 0; i < N; i++) 
			id[i] = i;
		sz = new int[N];
		for(int i = 0; i < N; i++) // 初始化都是1一个分量
			sz[i] = 1;
	}
	
	public int count() {
		return count;
	}
	
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}
	
	/**
	 * quick-find
	 * 操作数组的次数在 (N+3)到(2N+1)之间，完成所有连通，需要N-1次，所以(N+3)(N-1)~N^2，平方级别
	 * @param p
	 * @param q
	 */
//	public int find(int p) {
//		return id[p];
//	}
//
//	public void union(int p, int q) {
//		int pID = find(p);
//		int qID = find(q);
//		if(pID == qID)
//			return;
//		for(int i = 0; i < id.length; i++) {
//			if(id[i] == pID)
//				id[i] = qID;
//		}
//		count--;
//	}
	
	/**
	 * quick-union
	 * 森林算法实现，用树来连接每个触点
	 * @param p
	 * @return
	 */
//	public int find(int p) {
//		while(p != id[p])
//			p = id[p];
//		return p;
//	}
//	
//	public void union(int p, int q) {
//		int pRoot = find(p);
//		int qRoot = find(q);
//		if(pRoot == qRoot) 
//			return;
//		id[pRoot] = qRoot;
//		count--;
//	}
	
	/**
	 * 加权的森林算法实现
	 * 将小树连接到大树上
	 * @param p
	 * @return
	 */
	public int find(int p) {
		while(p != id[p]) p = id[p];
		return p;
	}
	
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if(sz[pRoot] < sz[qRoot]) {
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		}
		else {
			id[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
	}
	
	public static void main(String[] args) {
		int N = StdIn.readInt();
		UnionFind uf = new UnionFind(N);
		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(uf.connected(p, q))
				continue;
			uf.union(p, q);
			StdOut.println(p + "" + q);
		}
		StdOut.println(uf.count() + "components");
	}
}
