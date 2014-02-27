import java.util.Arrays;
import java.util.Iterator;

public class BinaryHeap implements Iterable {

	int[] heap = new int[2];
	int N = 0;

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return Arrays.asList(heap).iterator();
	}

	// isEmpty
	public boolean isEmpty() {
		return N == 0;
	}

	// insert
	public void insert(int k) {
		if (N == heap.length - 1)
			resize(2 * heap.length);
		heap[++N] = k;
		swimup(N);
	}

	private void swimup(int index) {
		while (index > 1) {
			int pindex = index / 2;
			if (heap[pindex] < heap[index]) {
				swap(pindex, index);
				index = pindex;
			} else
				break;
		}
	}

	// delete
	public int delMax() {
		int max = heap[1];
		N--;
		sink(1);
		// heap[N+1] = 0;
		return max;
	}

	// sink
	private void sink(int index) {
		while (2 * index < N) {
			int cindex = 2 * index;
			if (heap[cindex] < heap[cindex + 1])
				cindex += 1;
			if (heap[cindex] < heap[index])
				break;
			else {
				swap(cindex, index);
				index = cindex;
			}
		}
	}

	// aux1: swap
	private void swap(int i, int j) {
		int swap = heap[i];
		heap[i] = heap[j];
		heap[j] = swap;
	}

	// aux2: resize
	private void resize(int newSize) {
		int[] newHeap = new int[newSize];
		for (int i = 1; i <= N; i++)
			newHeap[i] = heap[i];
		heap = newHeap;
	}
	
	public String toString(){
		String s = "";
		for(int i: heap)
			s.concat(String.valueOf(i));
		return s;
	}

}