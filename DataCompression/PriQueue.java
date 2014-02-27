public class PriQueue{
	private Node root = null;
	private Node[] heap = new Node[2];
	private int N = 0;
	
	//isEmpty
	public boolean isEmpty(){
		return N == 0;
	}
	
	//insert
	public void insert(Node x){
		if( N >= heap.length)
			resize(2*heap.length);
		heap[++N]= x;
		swimup(N);
	}
	
	//swimup
	private void swimup(int x){
		
		while(x > 1){
			int pIndex = x/2;
			if(heap[x].less(heap[pIndex])){
				swap(x, pIndex);
				x = pIndex;
			}
			else
				break;
		}
	}
	
	//deleteMin
	public Node deleteMin(){
		Node min = heap[1];
		swap(1, N--);
		sink(1);
		heap[N+1] = null;
		return min;
	}
	
	//sink
	private void sink(int index){
		while(2*index <= N){
			int cIndex = 2*index;
			if(cIndex < N && heap[cIndex+1].less(heap[cIndex])){
				cIndex++;
			}
			
			if(heap[index].less(heap[cIndex])){
				swap(index, cIndex);
				index = cIndex;
			}
			else
				break;
		}
	}
	
	//swap
	private void swap(int index1, int index2){
		Node x = heap[index2];
		heap[index2] = heap[index1];
		heap[index1] = x;
	}
	
	//size
	public int size(){
		return N;
	}
	
	//resize
	private void resize(int size){
		Node[] newHeap = new Node[size];
		for(int i = 1; i <= N; i++)
			newHeap[i] = heap[i];
		heap = newHeap;
	}
}