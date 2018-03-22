package org.wzp.chapter4;

import org.wzp.util.StdIn;
import org.wzp.util.StdOut;

/**
 * 间隔的度数
 * 就是路径
 * @author wzp
 * @date: 2018年3月22日 下午11:39:47 
 *
 */
public class DegreesOfSeparation {
	public static void main(String[] args) {
		String stream = args[0];
		String sp = args[1];
		String source = args[2]; // 搜索的节点
		SymbolGraph sg = new SymbolGraph(stream, sp);
		Graph G = sg.G();
		
		int s = sg.index(source);
		BreadthFirstSearch bfs = new BreadthFirstSearch(G, s);
		
		while(!StdIn.isEmpty()) {
			String sink = StdIn.readLine();
			if(sg.contains(sink)) {
				int t = sg.index(sink);
				if(bfs.hasPathTo(t))
					for(int v : bfs.pathTo(t))
						StdOut.println("  " + sg.name(v));
			}
		}
	}
}
