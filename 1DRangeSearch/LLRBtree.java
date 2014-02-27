import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LLRBtree<Key extends Comparable<Key>, Value> {
	private final static boolean RED = true;
	private final static boolean BLACK = false;

	private Node root = null;

	private class Node {
		Key k;
		Value v;

		Node left, right;
		int count;
		boolean color;

		public Node(Key k, Value v, boolean c) {
			this.k = k;
			this.v = v;
			color = c;
			count = 1;
		}
	}

	// size
	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.count;
	}

	// is there a key-value pair with the given key?
	public boolean contains(Key key) {
		return (search(key) != null);
	}

	// put
	public void put(Key k, Value v) {
		root = put(root, k, v);
		root.color = BLACK;
	}

	// recursively put to the right place
	private Node put(Node x, Key k, Value v) {
		if (x == null)
			return new Node(k, v, RED);

		int cmp = k.compareTo(x.k);

		if (cmp < 0)
			x.left = put(x.left, k, v);
		else if (cmp > 0)
			x.right = put(x.right, k, v);
		else
			x.v = v;

		if (!isRed(x.left) && isRed(x.right))
			x = rotateToLeft(x);
		if (isRed(x.left) && isRed(x.left.left))
			x = rotateToRight(x);
		if (isRed(x.left) && isRed(x.right))
			flipColor(x);

		x.count = size(x.left) + size(x.right) + 1;
		return x;

	}

	// isRed
	private boolean isRed(Node x) {
		if (x == null)
			return false;
		else
			return x.color == RED;
	}

	// rotateToLeft [not recursive]
	private Node rotateToLeft(Node x) {
		Node t = x.right;
		x.right = t.left;
		t.left = x;

		t.color = x.color;
		x.color = RED;
		
		t.count = x.count;
		x.count = size(x.left) + size(x.right) +1;
		return t;
	}

	// rotateToRight [not recursive]
	private Node rotateToRight(Node x) {
		Node t = x.left;
		x.left = t.right;
		t.right = x;

		t.color = x.color;
		x.color = RED;
		
		t.count = x.count;
		x.count = size(x.left) + size(x.right) +1;

		return t;
	}

	// flipColor
	private void flipColor(Node x) {
		assert (x != null) && (x.left != null) && (x.right != null);
		assert (!isRed(x) && isRed(x.left) && isRed(x.right))
				|| (isRed(x) && !isRed(x.left) && !isRed(x.right));

		x.color = !x.color;
		x.left.color = !x.left.color;
		x.right.color = !x.right.color;
	}

	// search
	public Value search(Key k) {
		Node x = root;

		while (x != null) {
			int cmp = k.compareTo(x.k);
			if (cmp < 0)
				x = x.left;
			else if (cmp > 0)
				x = x.right;
			else
				return x.v;
		}

		return null;
	}

	// delete
	public void delete(Key k) {
		if (!isRed(root.left) && !isRed(root.right))
			root.color = RED;

		root = delete(root, k);
		if (root != null)
			root.color = BLACK;
	}

	private Node delete(Node h, Key k) {
		assert search(k) != null;
//
//		if (!isRed(h.left) && !isRed(h.right))
//			flipColor(h);

		int cmp = k.compareTo(h.k);

		if (cmp < 0) {
			if (!isRed(h.left) && !isRed(h.left.left))
				h = pushRedDownToLeft(h);
			h.left = delete(h.left, k);
		} else {// go to right or equal
			if (isRed(h.left))
				h = rotateToRight(h);
			if (k.compareTo(h.k) == 0 && h.right == null)
				return null;
			if (!isRed(h.right) && !isRed(h.right.left))
				h = pushRedDownToRight(h);
			if (k.compareTo(h.k) == 0) {
				Node t = min(h.right);
				h.k = t.k;
				h.v = t.v;

				h.right = deleteMin(h.right);
			} else
				h.right = delete(h.right, k);
		}
		return fixUp(h);
	}

	// deleteMin
	private Node deleteMin(Node h) {
		if (h.left == null)
			return null;
		
		if (!isRed(h.left) && !isRed(h.left.left))
			 h = pushRedDownToLeft(h);

		h.left = deleteMin(h.left);
		return fixUp(h);
	}

	// min
	private Node min(Node h) {
		if (h == null)
			return null;
		while (h.left != null)
			h = h.left;
		return h;
	}

	// pushRedDownToRight
	private Node pushRedDownToRight(Node h) {
		assert h != null;
		flipColor(h);
		if (isRed(h.left.left)) {
			h = rotateToRight(h);
			//h.color = RED;
			//h.left.color = BLACK;
		}

		return h;
	}

	// pushRedDownToLeft
	private Node pushRedDownToLeft(Node h) {
		assert h != null;

		flipColor(h);
		if (isRed(h.right.left)) {
			h.right = rotateToRight(h.right);
			h = rotateToLeft(h);
			//flipColor(h);
		}

		return h;
	}

	// fixup
	private Node fixUp(Node h) {
		assert h != null;

		if (isRed(h.right))
			h = rotateToLeft(h);
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateToRight(h);
		if (isRed(h.left) && isRed(h.right))
			flipColor(h);

		h.count = size(h.left) + size(h.right) + 1;
		return h;
	}

	// rank
	public int rank(Key k) {
		assert search(k) != null;
		return rank(root, k);
	}

	private int rank(Node h, Key k) {
		if (h == null)
			return 0;
		int cmp = h.k.compareTo(k);
		if (cmp < 0)
			return 1 + size(h.left) + rank(h.right, k);
		else if (cmp > 0)
			return rank(h.left, k);
		else
			return size(h.left);
	}

	// isEmpty
	public boolean isEmpty() {
		return root == null;
	}

	// depth
	public int depthOfTree() {
		if (isEmpty())
			return 0;
		return depth(root);
	}

	private int depth(Node h) {
		if (h == null)
			return -1;
		return Math.max(depth(h.left), depth(h.right)) + 1;
	}

	// number of items in range
	public int numOfItemsInRange(Key k1, Key k2) {
		assert (k1.compareTo(k2) < 0);
		assert (search(k1) != null && search(k2) != null);

		return rank(k2) - rank(k1);
	}

	// item list inside range
	public ArrayList<Key> itemsInRange(Key k1, Key k2) {
		assert (k1.compareTo(k2) < 0);
		assert (search(k1) != null && search(k2) != null);

		ArrayList<Key> result = new ArrayList<>();

		itemsInRange(root, k1, k2, result);
		Collections.sort(result);
		return result;
	}

	private void itemsInRange(Node h, Key lo, Key hi, ArrayList<Key> result) {
		assert (lo.compareTo(hi) < 0);
		assert (search(lo) != null && search(hi) != null);

		if (h == null)
			return;
		int cmp1 = h.k.compareTo(lo);
		int cmp2 = h.k.compareTo(hi);

		if (cmp1 == 0) {
			result.add(h.k);
			itemsInRange(h.right, lo, hi, result);
		}
		if (cmp2 == 0)
			itemsInRange(h.left, lo, hi, result);
		if (cmp1 > 0 && cmp2 < 0) {
			result.add(h.k);
			itemsInRange(h.left, lo, hi, result);
			itemsInRange(h.right, lo, hi, result);
		} else if (cmp1 < 0)
			itemsInRange(h.right, lo, hi, result);
		else if (cmp2 > 0)
			itemsInRange(h.left, lo, hi, result);

	}

}