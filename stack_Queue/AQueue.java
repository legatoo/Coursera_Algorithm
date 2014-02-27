import java.util.Arrays;
import java.util.Iterator;

public class AQueue<Item> implements Iterable<Item>{
	private Item[] aqueue = (Item[]) new Object[1];
	private int head = 0, tail; //head is zero by default
	private int cap = 0;
	//isEmpty
	public boolean isEmpty()
	{
		return cap == 0;
	}
	
	//enqueue
	public void enqueue(Item item){
		if ( cap == aqueue.length)
			resize(aqueue.length*2);
		aqueue[tail++] = item;
		cap++;
		
	}
	
	//dequeue
	public Item dequeue(){
		cap--;
		return aqueue[head++];
	}

	public void resize(int newsize){
		System.out.println("resizing to size of " +String.valueOf(newsize));
		Item[] newAQueue = (Item[]) new Object[newsize];
		
		for ( int i = 0; i < cap; i++)
			newAQueue[i] = aqueue[head+i];
		
		aqueue = newAQueue;
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return Arrays.asList(aqueue).iterator();
	}
	
}