import java.util.Arrays;
import java.util.Iterator;


//heap based priority queue
public class PriQueue<Key extends Comparable<Key>> implements Iterable<Key> {
	private Key[] pq = (Key[]) new Object[2];
	private int N = 0;

	public Iterator<Key> iterator() {
		return Arrays.asList(pq).iterator();
	}

	// insert
	public void insert(Key k) {
		if (N == pq.length - 1)
			resize(2 * pq.length);
		pq[++N] = k;
		swimup(N);
	}

	// isEmpty
	public boolean isEmpty() {
		return N == 0;
	}

	// delMax
	public Key delMax() {
		Key max = pq[1];
		swap(1, N--);
		sinkdown(1);
		pq[N+1] = null;
		return max;
	}

	// swimup
	private void swimup(int index) {
		while (index > 1) {
			int pindex = index / 2;
			if (less(pindex, index)) {
				swap(index, pindex);
				index = pindex;
			} else
				break;
		}
	}

	// sinkdown
	private void sinkdown(int index) {
		while (index * 2 < N) {
			int cindex = 2 * index;
			if (cindex < N && less(cindex, cindex + 1))
				cindex += 1;
			if (less(index, cindex)) {
				swap(index, cindex);
				index = cindex;
			}
			else
				break;
		}
	}

	// swap
	private void swap(int index1, int index2) {
		Key swap = pq[index2];
		pq[index2] = pq[index1];
		pq[index1] = swap;
	}

	// less: return true if i < j
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	

	// resize
	private void resize(int newsize) {
		Key[] newpq = (Key[]) new Object[newsize];

		for (int i = 1; i <= N; i++)
			newpq[i] = pq[i];

		pq = newpq;
	}

	public String toString() {
		String tostring = "";
		for (Key s : pq)
			tostring.concat(s.getClass().getSimpleName());
		return tostring;
	}
}