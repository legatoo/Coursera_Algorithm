import java.util.ArrayList;


public class BinarySearchTree<Key extends Comparable<Key>, Value> {
	private Node root = null;

	// node
	private class Node {
		private Key k;
		private Value v;
		private int count;
		private Node left, right;

		public Node(Key k, Value v) {
			this.k = k;
			this.v = v;
			count = 1;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int c) {
			this.count = c;
		}
	}

	/** binary search tree functions **/
	// insert
	public void insert(Key k, Value v) {
		root = insert(root, k, v);
	}

	private Node insert(Node x, Key k, Value v) {
		if (x == null)
			return new Node(k, v);

		int cmp = compare(k, x.k);
		if (cmp < 0)
			x.left = insert(x.left, k, v);
		else if (cmp > 0)
			x.right = insert(x.right, k, v);
		else
			x.v = v;

		x.setCount(size(x.left) + size(x.right) + 1);
		return x;
	}

	// delete
	public void delete(Key k) {
		Node x = root;

	}

	private Node delete(Node x, Key k) {
		if (x == null)
			return null;
		int cmp = compare(k, x.k);
		if (cmp < 0)
			x.left = delete(x.left, k);
		else if (cmp > 0)
			x.right = delete(x.right, k);
		else {
			if (x.left == null)
				return x.right;
			if (x.right == null)
				return x.left;

			Node s = min(x.right);
			x.k = s.k;
			x.v = s.v;

			x = deleteMin(x.right);
		}

		x.setCount(size(x.left) + size(x.right) + 1);
		return x;
	}

	private Node min(Node h) {
		if (h == null)
			return null;
		while (h.left != null)
			h = h.left;
		return h;
	}

	private Node deleteMin(Node x) {
		if (x == null)
			return null;

		Node t = x;

		while (t.left.left != null)
			t = t.left;
		t.left = null;
		return x;

	}

	// search
	public Node search(Key k) {
		if (root == null)
			return null;

		Node t = root;
		while (t != null) {
			int cmp = compare(k, t.k);
			if (cmp < 0) {
				t = t.left;
				continue;
			} else if (cmp > 0) {
				t = t.right;
				continue;
			} else
				return t;
		}

		return null;

	}

	public ArrayList<Key> itemsInRange(Key k1, Key k2) {
		ArrayList<Key> result = new ArrayList<>();
		itemsInRange(result, root, k1, k2);
		return result;
	}

	private void itemsInRange(ArrayList<Key> result, Node x, Key lo, Key hi) {
		if (x == null)
			return;

		int cmp1 = compare(x.k, lo);
		int cmp2 = compare(x.k, hi);

		//RANGE IS [LO, HI)
		if (cmp1 >= 0 && cmp2 < 0) {
			result.add(x.k);
			itemsInRange(result, x.left, lo, hi);
			itemsInRange(result, x.right, lo, hi);
		}
		if (cmp1 < 0){
			itemsInRange(result, x.right, lo, hi);
		}
		if (cmp2 > 0){
			itemsInRange(result, x.left, lo, hi);
		}
	}

	/** helper functions **/
	// compare
	private int compare(Key k1, Key k2) {
		return k1.compareTo(k2);
	}

	public int size(Node t) {
		if(t == null)
			return 0;
		return t.getCount();
	}

	public int rank(Key k) {
		return rank(root, k);
	}

	private int rank(Node x, Key k) {
		if (x == null)
			return 0;
		int cmp = compare(x.k, k);
		if (cmp < 0) 
			return 1+ size(x.left) + rank(x.right, k);
		else if (cmp > 0)
			return rank(x.left, k);
		else
			return size(x.left);
		
	}
	
	public int depthOfTree(){
		if (root == null)
			return 0;
		return depth(root);
	}
	
	private int depth(Node h){
		if (h == null)
			return -1;
		return Math.max(depth(h.left), depth(h.right)) +1;
	}
}