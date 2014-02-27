import java.util.Arrays;

public class week2 {
	public static void main(String[] args) {
		int[] test = new int[] { 29, 30, 31, 32, 28, 27, 26, 26, 26, 26, 26,
				25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10,
				9, 9, 9, 9, 9, 9, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 0 };

		Sort1 s1 = new Sort1();
		MergeSort s2 = new MergeSort();
		QuickSort q = new QuickSort();
		//s1.insertSort(test);
		// s1.shellSort(test);
		// Shuffling shuffer = new Shuffling();
		// shuffer.shuffer(test);
		// s2.bottomupMerge(test);
		//s2.sort(test);
		q.quicksort(test);
		for (int i = 0; i < test.length; i++)
			System.out.println(test[i]);
	}
}