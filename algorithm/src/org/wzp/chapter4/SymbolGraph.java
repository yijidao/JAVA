package org.wzp.chapter4;

import org.wzp.chapter3.ST;
import org.wzp.util.In;

/**
 * 符号图实现
 * 利用方向索引
 * @author wzp
 * @date: 2018年3月22日 下午11:21:09 
 *
 */
public class SymbolGraph {
	private ST<String, Integer> st; // 符号名 -> 索引
	private String[] keys; // 反向索引： 索引 -> 符号名
	private Graph G;
	
	public SymbolGraph(String stream, String sp) {
		st = new ST<String, Integer>();
		In in = new In(stream);
		while(in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			
			for(int i = 0;  i < a.length; i++) {
				if(!st.contains(a[i]))
					st.put(a[i], st.size());
			}
		}
		
		keys = new String[st.size()];
		
		for(String name : st.keys()) {
			keys[st.get(name)] = name;
		}
		
		G = new Graph(st.size());
		in = new In(stream);
		while(in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			int v = st.get(a[0]);
			for(int i = 1; i < a.length; i++) {
				G.addEdge(v, st.get(a[i]));
			}
		}
	}
	
	public boolean contains(String s) {
		return st.contains(s);
	}
	
	public int index(String s) {
		return st.get(s);
	}
	
	public String name(int v) {
		return keys[v];
	}
	
	public Graph G() {
		return G;
	}
}
