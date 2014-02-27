import java.util.Iterator;

public class LQueue<Item> implements Iterable<Item>{
	private Node head = null;
	private Node tail = null;

	private class Node {
		Item item;
		Node next;
	}

	// isEmpty
	public boolean isEmpty() {
		return head == null;
	}

	// dequeue
	public Item dequeue() {
		Item item = head.item;
		head = head.next;
		return item;
	}

	// enqueue
	public void enqueue(Item item) {
		Node oldtail = tail;
		tail = new Node();
		tail.item = item;
		tail.next = null;

		if (isEmpty())
			head = tail;
		else
			oldtail.next = tail;
	}

	public Iterator<Item> iterator() {
		return new slefIterator();
	}

	private class slefIterator implements Iterator<Item>{
		private Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			
		}
		
	}
}