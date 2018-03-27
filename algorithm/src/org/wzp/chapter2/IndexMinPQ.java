package org.wzp.chapter2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.wzp.util.StdOut;
/**
 * 带索引的最小二叉堆
 * @author wzp
 * @date: 2018年3月27日 下午3:15:08 
 *
 * @param <Key>
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
	private int maxN;
    private int n;
    private int[] pq; // 存放 item 的索引
    private int[] qp; // pq 的逆数组，即 qp[pq[i]] = pq[qp[i] = i
    private Key[] keys;
	
	public IndexMinPQ(int maxN) {
		this.maxN = maxN;
		n = 0;
		keys = (Key[])new Comparable[maxN + 1];
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		for(int i = 0; i <= maxN; i++)
			qp[i] = -1;
	}
    
	public boolean isEmpty() {
		return n == 0;
	}
	
	public boolean contains(int i) {
		return qp[i] != -1;
	}
	
	public int size() {
		return n;
	}
	
	public void insert(int i, Key key) {
		if(contains(i))
			throw new IllegalArgumentException("index is already in the priority queue");
		n++;
		qp[i] = n;
		pq[n] = i;
		keys[i] = key;
		swim(n);
	}
	
	public int minIndex() {
		if(n == 0)
			throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}
	
	public Key minKey() {
		return keys[pq[1]];
	}
	
	public int delMin() {
		int min = pq[1];
		exch(1, n--);
		sink(1);
		assert min == pq[n + 1];
		qp[min] = -1;
		keys[min] = null;
		pq[n + 1] = -1;
		return min;
	}
	
	public Key keyOf(int i) {
		return keys[i];
	}
	
	public void changeKey(int i, Key key) {
		keys[i] = key;
		swim(qp[i]);
		sink(qp[i]);
	}
	
	public void change(int i, Key key) {
		changeKey(i, key);
	}
	
	public void decreaseKey(int i, Key key) {
		if(keys[i].compareTo(key) <= 0)
			throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
		keys[i] = key;
		swim(qp[i]);
	}
	
	public void increaseKey(int i, Key key) {
        if (keys[i].compareTo(key) >= 0)
            throw new IllegalArgumentException("Calling increaseKey() with given argument would not strictly increase the key");
        keys[i] = key;
        sink(qp[i]);
	}
	
	public void delete(int i) {
		int index = qp[i];
		exch(index, n--);
		swim(index);
		sink(index);
		keys[i] = null;
		qp[i] = -1;
	}
	
	private void swim(int k) {
		while(k > 1 && greater(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}
	
	private void sink(int k) {
		while(2 * k <= n) {
			int j = 2 * k;
			if(j < n && greater(j, j + 1))
				j++;
			if(!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}
	
	private boolean greater(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
	}
	
	private void exch(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new HeapIterator();
	}
	
    private class HeapIterator implements Iterator<Integer> {
        private IndexMinPQ<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }

    public static void main(String[] args) {
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPQ<String> pq = new IndexMinPQ<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        while (!pq.isEmpty()) {
            int i = pq.delMin();
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();

        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        for (int i : pq) {
            StdOut.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.delMin();
        }

    }
}
