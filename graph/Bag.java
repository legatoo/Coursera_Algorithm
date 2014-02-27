import java.util.Iterator;

//BAG is a list based queue contains all the adjacent points 
//if it is necessary, put the value here
public class Bag<Key extends Comparable<Key>> implements Iterable<Key>{
	private int bagSize;
	private Node<Key> first;
	
	
	private class Node<Key>{
		private Key k;
		private Node<Key> next;
		
		public Node(Key k, Node n){
			this.k = k;
			this.next = n;
		
		}
	}

	@Override
	public Iterator<Key> iterator() {
		// TODO Auto-generated method stub
		return new BagIterator<Key>();
	}
	
	private class BagIterator<Key> implements Iterator<Key>{
		private Node<Key> current = (Node<Key>) first;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current != null;
		}

		@Override
		public Key next() {
			// TODO Auto-generated method stub
			if( hasNext()){
				Key k = current.k;
				current = current.next;
				return k;
			}
			return null;
		}

		@Override
		public void remove() {
		}

		
	}
	//constructor
	public Bag(){
		first = null;
		bagSize = 0;
	}
	
	
	//isEmpty
	public boolean isEmpty(){
		return bagSize == 0;
	}
	
	//add
	public void add(Key k){
		Node t = new Node(k,first);
		first = t;
		bagSize++;
	}
	
	public boolean contains(Key s){
		for (Key k : this){
			if(k.compareTo(s) == 0)
				return true;
		}
		
		return false;
	}
}