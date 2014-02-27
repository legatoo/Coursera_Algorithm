import java.util.Arrays;
import java.util.Iterator;

//auto size has been implemented
public class AStack<Item>  implements Iterable<Item>{
	
	private Item[] astack = (Item[]) new Object[1];
	private int capacity = 0;
	
	//isEmpty
	public boolean isEmpty(){
		return capacity == 0;
	}
	//pop
	public Item pop(){
		Item item = astack[--capacity];
		astack[capacity] = null;      //let java recollect the garbage
		if (capacity > 0 && capacity == astack.length/4)
			resize(astack.length/2);
		return item;
	}
	//push
	public void push(Item s){
		if (capacity == astack.length)
			resize(astack.length*2);
		astack[capacity++] = s;
		
	}
	
	//resize
	public void resize(int c){
		Item[] newAStack = (Item[]) new Object[c];
		for (int i = 0; i < capacity; i++)
			newAStack[i] = astack[i];
		astack = newAStack;
		
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		//return Arrays.asList(astack).iterator();  this will iterate the array 
		
		//according to the property of a stack, we have to implement the reserver iterator by ourselves
		return new reserverIterator();
	}
	
	private class reserverIterator implements Iterator<Item>{

		int start = capacity;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return start > 0;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			return astack[--start];
		}

		@Override
		public void remove() {
			
		}
		
	}
}