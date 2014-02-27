public class Sort1 {
	public void selectSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = array[i];
			int index = i;
			for (int j = i; j < array.length; j++) {
				if (min > array[j]) {
					min = array[j];
					index = j;
				}
			}
			swap(array, i, index);
		}
	}

	public void insertSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
				}
			}
		}
	}

	//partial insert sort
	public void insertsort(int[] array, int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j > lo; j--) {
				if (array[j] < array[j - 1])
					swap(array, j, j - 1);
			}
		}
	}

	/**
	 * since for insert sort, if a small value locate at the end of the array,
	 * it needs a lot of operations to bring it to the right place. Shell sort
	 * tries to fix this by bring some small value at back to the front with big
	 * step
	 */
	public void shellSort(int[] array) {
		int n = array.length;
		int h = 1;
		while (h < n / 3)
			h = 3 * h + 1; // knurt method to generate a increment secquence

		while (h >= 1) {
			for (int i = h; i < n; i++) {
				for (int j = i; j >= h && array[j] < array[j - h]; j -= h)
					swap(array, j, j - h);
			}

			h = h / 3;
		}
	}

	public void swap(int[] array, int index1, int index2) {
		int swap = array[index2];
		array[index2] = array[index1];
		array[index1] = swap;
	}
}