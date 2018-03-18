package org.wzp.chapter3;
/**
 * 红黑查找树
 * @author wzp
 * @date: 2018年3月18日 下午12:53:43 
 *
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node{
		Key key;
		Value val;
		Node left, right; // 左右子树
		boolean color; // 由其父节点指向它的链接颜色
		int N; // 这个子树中的结点总数
		
		Node(Key key, Value val, boolean color, int N){
			this.key = key;
			this.val = val;
			this.color = color;
			this.N = N;
		}
	}
	
	private boolean isRed(Node h) {
		if(h == null)
			return false;
		return h.color == RED;
	}
	
	/**
	 * 左旋，就是将原来的根结点，更换成右侧更大的结点
	 * @param h
	 * @return
	 */
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = size(h.left) + size(h.right) + 1;
		return x;
		
	}
	
	/**
	 * 右旋，就是将原来的根结点，更换成左侧更小的结点
	 * @param h
	 * @return
	 */
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	/**
	 * 将左右两个子节点的颜色由红变黑，将自身的链接由黑变红
	 * @param h
	 */
	private void flipColors(Node h) {
		h.left.color = BLACK;
		h.right.color = BLACK;
		h.color = RED;
	}
	
	private int size(Node x) {
		return x.N;
	}
	
	/**
	 * 红黑树的插入算法
	 * 根结点的颜色永远是黑的
	 * @param key
	 * @param val
	 */
	public void put(Key key, Value val) {
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	
	private Node put(Node h, Key key, Value val) {
		if(h == null)
			return new Node(key, val, BLACK, 1);
		
		int cmp = key.compareTo(h.key);
		if(cmp < 0)
			put(h.left, key, val);
		else if(cmp > 0)
			put(h.right, key, val);
		else
			h.val = val;
		
		if(isRed(h.right) && !isRed(h.left))
			rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left))
			rotateRight(h);
		if(isRed(h.left) && isRed(h.right))
			flipColors(h);
		
		h.N = 1 + size(h.left) + size(h.right);
		return h;
	}
}
