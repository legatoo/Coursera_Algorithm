import java.util.ArrayList;

public class RangeSearch<Key extends Comparable<Key>, Value> {
	private BinarySearchTree bst = new BinarySearchTree<Key, Value>();
	private LLRBtree llrb = new LLRBtree<Key, Value>();

	public void insert(Key k, Value v) {
		bst.insert(k, v);
		//llrb.put(k, v);  //much more balance
	}

	public void delete(Key k) {
		bst.delete(k);
	}

	public int numOfItemsInRange(Key k1, Key k2) {
		// not contain [lo, hi)
		return Math.abs(bst.rank(k1) - bst.rank(k2));
	}
	
	public int rank(Key k){
		return bst.rank(k);
	}

	public ArrayList<Value> itemsInRange(Key k1, Key k2) {
		if (k1.equals(k2))
			return null;
		return bst.itemsInRange((k1.compareTo(k2) < 0) ? (k1) : (k2),
				(k1.compareTo(k2) > 0) ? (k1) : (k2));

	}
}