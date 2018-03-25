package org.wzp.chapter4;

import org.wzp.chapter1.NodeQueue;
import org.wzp.chapter1.NodeStack;

/**
 * 有向图基于深度搜索的顶点排序
 * 1. 前序排序，在递归调用前，将所有顶点放入队列
 * 2. 后序排序，在递归调用结束后，将顶点放入队列
 * 3. 逆后序排序，在递归调用结束后，将顶点放入堆栈
 * @author wzp
 * @date: 2018年3月25日 下午4:39:53 
 *
 */
public class DepthFirstOrder {
	private boolean marked[]; 
	private NodeQueue<Integer> pre; // 前序排序队列
	private NodeQueue<Integer> post; // 后序排序队列
	private NodeStack<Integer> reversePost; // 逆后序排序堆栈
	
	public DepthFirstOrder(Graph G) {
		pre = new NodeQueue<Integer>();
		post = new NodeQueue<Integer>();
		reversePost = new NodeStack<Integer>();
		
		for(int i = 0; i < G.V(); i++) {
			if(!marked[i])
				dfs(G, i);
		}
	}
	
	private void dfs(Graph G, int v) {
		pre.enqueue(v);
		marked[v] = true;
		for(int w : G.adj(v))
			if(!marked[w])
				dfs(G, w);
		
		post.enqueue(v);;
		reversePost.push(v);
	}
	
	public Iterable<Integer> pre(){
		return pre;
	}
	
	public Iterable<Integer> post(){
		return post;
	}
	
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
}
