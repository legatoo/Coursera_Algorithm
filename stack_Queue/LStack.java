import java.util.Iterator;

public class LStack<Item> implements Iterable<Item> {
	private Node first = null; // remember to initialize

	private class Node {
		Item item;
		Node next;
	}
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}

		public void remove() {}
	}

	// isEmpty
	public boolean isEmpty() {
		return first == null;
	}

	// pop
	public Item pop() {
		Item item = first.item;
		first = first.next;
		return item;
	}

	// push
	public void push(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
	}



}