public class MergeSort {
	private static int[] aux; //not in-pace sorting algorithm
	private static int cutoff = 4;

	// private function
	private static void merge(int[] array, int lo, int mid, int hi) {
		assert isSorted(array, lo, mid);
		assert isSorted(array, mid + 1, hi);

		/**
		 * copy, using extra space N this part is possible to avoid by replacing
		 * the operations on array to operations on aux. By doing this, we don't
		 * need to initialize this aux array save a little time
		 **/
		for (int i = lo; i <= hi; i++)
			aux[i] = array[i];

		// merge
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			//low part is done, copy high part
			if (i > mid)
				array[k] = aux[j++];
			else if (j > hi)
				array[k] = aux[i++];
			else if (aux[i] < aux[j])
				array[k] = aux[i++];
			else
				array[k] = aux[j++];
		}

		assert isSorted(array, lo, hi);
	}

	// private function
	private static boolean isSorted(int[] array, int start, int end) {
		for (int i = start; i < end; i++) {
			if (array[i + 1] < array[i])
				return false;
		}

		return true;
	}

	private static void insertsort(int[] array, int lo, int hi) {
		for (int i = lo; i < hi; i++) {
			for (int j = i + 1; j > lo; j--) {
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
				}
			}
		}
	}

	private static void swap(int[] array, int index1, int index2) {
		int swap = array[index2];
		array[index2] = array[index1];
		array[index1] = swap;
	}

	// private function
	private static void sort(int[] array, int lo, int hi) {
		// improvement #1 when the subarray is small, use insert sort
		if (hi <= lo + cutoff - 1) {
			insertsort(array, lo, hi);
			return;
		}
		// if (hi <= lo)
		// return;
		
		int mid = lo + (hi - lo) / 2;
		sort(array, lo, mid);
		sort(array, mid + 1, hi);

		// improvement #2
		if (array[mid + 1] > array[mid])
			return;
		//merge is at the end of recursive sort
		merge(array, lo, mid, hi);
	}

	// public function
	public static void sort(int[] array) {
		/**
		 * it is important to alloc this array here since the mergesort is a
		 * recursive sort, it is actually a bug to put the alloc inside
		 * recursive method.
		 **/
		aux = new int[array.length];
		sort(array, 0, array.length - 1);
	}

	public static void bottomupMerge(int[] array) {
		aux = new int[array.length];
		int n = array.length;

		// need lgN level
		for (int k =0, i = 2; k <= (n+1)/2; i = i + i, k++) {
			// k is how many level of this tree, i is the merge size
			// for every level, do n/i times merge
			for (int j = 0; j < n - 1; j += i) {
				int mid = j + (i - 1) / 2; // j + (j+i-1-j)/2
				int hi = j + i - 1;
				merge(array, j, mid, Math.min(n - 1, hi));
			}
		}

		/**
		 * bottom up merge sort, dowsn't care about whether the halves are with equal size
		 */
//		for (int i = 1; i < n; i = i + i) {
//			for (int lo = 0; lo < n - i; lo += i + i) {
//				/**
//				 * int hi = lo +i+i-1; int mid = lo + (lo +i+i-1-lo)/2 = lo
//				 * +i-1/2 = lo + i -1
//				 */
//				merge(array, lo, lo + i - 1, Math.min(n - 1, lo + i + i - 1));
//			}
//		}
	}
}