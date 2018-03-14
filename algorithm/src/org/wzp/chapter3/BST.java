package org.wzp.chapter3;

import org.wzp.chapter1.NodeQueue;
import org.wzp.util.StdOut;

/**
 * 二叉查找树
 * 二叉查找树的左子节点一定小于父节点，右子节点一定大于父节点
 * 将二叉查找树投影到一个直线上，就是一个递增的序列
 * @author wzp
 * @date: 2018年3月13日 下午1:57:39 
 *
 */
public class BST<Key extends Comparable<Key>, Value> {
	private Node root; // 二叉查找树的根结点
	private class Node{
		private Key key;
		private Value val;
		private int N; // 以该结点为根的子节点数量
		private Node left, right; // 左子树和右子树
		
		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N= N;
		}
	}
	
	public int size() {
		return size(root);
	}
	
	public int size(Node x) {
		if(x == null) 
			return 0;
		else
			return x.N;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp > 0)
			return get(x.right, key);
		else if(cmp < 0)
			return get(x.left, key);
		else
			return x.val;
	}
	
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val) {
		if(x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
			x.left = put(x.left, key, val);
		else if(cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node x) {
		if(x.left == null)
			return x;
		return min(x.left);
	}
	
	public Key max() {
		return max(root).key;
	}
	
	private Node max(Node x) {
		if(x == null)
			return x;
		return max(x.right);
	}
	// return <= key
	public Key floor(Key key) {
		Node x = floor(root, key);
		if(x == null)
			return null;
		return x.key;
	}
	
	private Node floor(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0)
			return x;
		else if(cmp > 0) {
			if(x.right != null)
				return floor(x.right, key);
			else
				return x;
		} else {
			if(x.left != null) 
				return floor(x.left, key);
			else 
				return null;
		}
	}
	/**
	 * return >= key
	 * @param key
	 * @return
	 */
	public Key ceiling(Key key) {
		Node x = ceiling(root, key);
		if(x == null)
			return null;
		return x.key;
	}
	
	private Node ceiling(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0)
			return x;
		else if(cmp < 0) {
			if(x.left != null)
				return floor(x.left, key);
			else
				return x;
		} else {
			if(x.right != null)
				return floor(x.right, key);
			else
				return null;
		}
	}
	/**
	 * 寻找第k个结点
	 * @param k
	 * @return
	 */
	public Key select(int k) {
		Node x = select(root, k);
		if(x == null)
			return null;
		return x.key;
	}
	
	private Node select(Node x, int k) {
		if(x == null)
			return null;
		int t = size(x.left);
		if(t > k) {
			return select(x.left, k);
		} else if (t < k) {
			return select(x.right, k - t - 1);
		} else
			return x;
	}
	/**
	 * 返回小于等于key的结点数
	 * @param key
	 * @return
	 */
	public int rank(Key key) {
		return rank(root, key);
	}
	
	private int rank(Node x, Key key) {
		if(x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if(cmp > 0)
			return 1 + size(x.left) + rank(x.right, key);
		else if(cmp < 0)
			return rank(x.left, key);
		else
			return size(x.left);
	}
	
	/**
	 * 删除最小结点
	 */
	public void deleteMin() {
		root = deleteMin(root);
	}
	/**
	 * 不断深入根结点的左子树中的最小结点，直到遇到null，将指向该结点的链接，指向该结点的右子树
	 * @param x
	 * @return
	 */
	private Node deleteMin(Node x) {
		if(x.left == null)
			return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	/**
	 * 删除最大结点
	 */
	public void deleteMax() {
		root = deleteMax(root);
	}
	/**
	 * 递归深入根结点树的右结点，直到知道null，将指向该结点的链接，指向该结点的左子树
	 * @param x
	 * @return
	 */
	private Node deleteMax(Node x) {
		if(x.right == null)
			return x.left;
		x.right = deleteMax(x.right);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	/**
	 * 删除结点
	 * @param key
	 */
	public void delete(Key key) {
		root = delete(root, key);
	}
	/**
	 * 找到要删除的结点，然后将该结点赋值为该结点右子树的最小结点，并将该结点的右子树的最小结点删除
	 * @param x
	 * @param key
	 * @return
	 */
	public Node delete(Node x, Key key) {
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp > 0)
			x.right = delete(x.right, key);
		else if(cmp < 0)
			x.left = delete(x.left, key);
		else {
			if(x.left == null) return x.right;
			if(x.right == null) return x.left;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(x.right);
			x.left = x.left;
		}
		x.N = size(x.left) + size(x.right ) + 1;
		return x;
	}
	
	/**
	 * 顺序打印二叉树所有键
	 * @param x
	 */
	private void print(Node x) {
		if(x == null)
			return;
		print(x.left);
		StdOut.print(x.key);
		print(x.right);
	}
	
	/**
	 * 二叉查找树的范围查找操作
	 * @return
	 */
	public Iterable<Key> keys(){
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key lo, Key hi){
		NodeQueue<Key> queue = new NodeQueue<Key>();
		keys(root, queue, lo, hi);
		return queue;
	}
	
	private void keys(Node x, NodeQueue<Key> queue, Key lo, Key hi) {
		if(x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = lo.compareTo(x.key);
		
		if(cmplo < 0)
			keys(x.left, queue, lo, hi);
		if(cmplo <= 0 && cmphi >= 0)
			queue.enqueue(x.key);
		if(cmphi > 0)
			keys(x.right, queue, lo, hi);
	}
}
