import java.util.Random;

public class QuickSort {
	private final static int cutoff = 6;

	// main entry of sort algorithm
	public void quicksort(int[] array) {
		shuffling(array);
		sort(array, 0, array.length - 1);
	}

	// shuffling for performance guarantee
	private void shuffling(int[] array) {
		for (int i = 0; i < array.length; i++) {
			Random r = new Random();
			int j = r.nextInt(i + 1);
			if (i != j)
				swap(array, i, j);
		}
	}

	// recursive sort
	private void sort(int[] array, int low, int high) {
		// improvement #1
		if (high <= low + cutoff) {
			Sort1 s1 = new Sort1();
			s1.insertsort(array, low, high);
			return;
		}
		// improvement #2
		median3(array, low, high);
		
//    ------trational partition-----
//		// get pivot
//		int pivot = partition(array, low, high);
//		// recursive sort
//		sort(array, low, pivot - 1);
//		sort(array, pivot + 1, high);

//    ------3 way partition version--------
		int[] pivots = partition3(array, low, high);
		
		int lt = pivots[0];
		int gt = pivots[1];
		// recursive sort
		sort(array, low, lt - 1);
		sort(array, gt + 1, high);

	}

	// partition
	private int partition(int[] array, int low, int high) {
		int pivotValue = array[low];
		int i = low;
		int j = high + 1;

		while (true) {
			//find the first one greater than pivotValue (left-to-right scan)
			while (array[++i] < pivotValue)
				if (i == high)
					break;
			//find the fisrt one less than pivotValue (right-to-left scan)
			while (array[--j] > pivotValue)
				if (j == low)
					break;

			if (i >= j)
				break;
			//swap them to keep order
			swap(array, i, j);
		}

		//put pivot to the right place, j will remember the location for pivot
		swap(array, low, j);
		//return the position of pivot
		return j;
	}

	// 3 way partition [take duplicate keys into consideration]
	private int[] partition3(int[] array, int lo, int hi) {
		//compare with normal partition
		//we need two more variables: lt, gt
		//lt: the first element with the pivot value
		//the last element with pivot value

		int lt = lo, gt = hi;
		int cmp = array[lo];
		int i = lo;
		
		while (true) {
			if (array[i] < cmp)
				swap(array, i++, lt++);
			else if (array[i] > cmp)
				swap(array, i, gt--);
			else
				i++;
			
			if(i > gt)
				break;
		}

		return new int[] { lt, gt };

	}

	// swap
	private void swap(int[] array, int index1, int index2) {
		int swap = array[index2];
		array[index2] = array[index1];
		array[index1] = swap;
	}

	// median 3
	public void median3(int[] array, int lo, int hi) {
		int mid = lo + (hi - lo) / 2;
		if (array[lo] > array[hi])
			swap(array, lo, hi);
		if (array[mid] > array[hi])
			swap(array, mid, hi);
		if (array[mid] < array[lo])
			swap(array, lo, mid);

		swap(array, mid, lo);
	}
}