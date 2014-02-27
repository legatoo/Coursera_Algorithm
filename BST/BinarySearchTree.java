public class BinarySearchTree<Key extends Comparable<Key>, Value> {

	private class Node {

		public Key k;
		public Value v;
		public int count;
		public Node right, left;

		public Node(Key k2, Value v) {
			this.k = k2;
			this.v = v;
		}

		public int getCount() {
			return count;
		}

	}

	public Node root = null;

	// size
	public int size() {
		return size(root);
	}

	private int size(Node n) {
		return n.getCount();
	}

	// isEmpty

	public boolean isEmpty() {
		return root == null;
	}

	// insert
	public void put(Key k, Value v) {
		root = put(root, k, v);
	}

	private Node put(Node x, Key k, Value v) {
		if (x == null)
			return new Node(k, v);
		int cmp = k.compareTo(x.k);
		if (cmp < 0)
			x.left = put(x.left, k, v);
		if (cmp > 0)
			x.right = put(x.right, k, v);
		else {
			x.v = v;
		}
		// x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	// delete
	public void delete(Key k) {
		root = delete(root, k);
	}

	/**
	 * hibband delete leads to a unbalanced tree after many many delete that's
	 * why we need a red-black tree to matain the balance
	 */
	private Node delete(Node x, Key k) {
		if (x == null)
			return null;
		int cmp = k.compareTo(x.k);
		if (cmp < 0)
			x.left = delete(x.left, k);
		if (cmp > 0)
			x.right = delete(x.right, k);
		else {
			// ok, here we have some situations
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;

			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		// x.count = 1 + size(x.right) + size(x.left);
		return x;
	}

	private Node min(Node t) {
		if (t == null)
			return null;
		while (t.left != null) {
			t = t.left;
		}
		return t;
	}

	private Node deleteMin(Node t) {
		if (t == null)
			return null;
		Node x = t;
		while (x.left.left != null)
			x = x.left;
		x.left = null;

		return t;
	}

	// search
	public Value search(Key k) {
		return search(root, k);
	}

	private Value search(Node x, Key k) {
		if (x == null)
			return null;
		int cmp = k.compareTo(x.k);

		if (cmp < 0)
			return search(x.left, k);
		if (cmp > 0)
			return search(x.right, k);
		else
			return x.v;
	}
}