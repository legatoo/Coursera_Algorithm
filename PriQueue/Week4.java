import java.util.Scanner;

public class Week4 {
	public static void main(String[] args) {
		// Scanner s = new Scanner(System.in);
		// BinaryHeap bh = new BinaryHeap();
		//
		// int foo = s.nextInt();
		// while (true){
		// bh.insert(foo);
		// foo = s.nextInt();
		// if (foo == 0){
		// System.out.print(bh.toString());
		// break;
		// }
		// }

		int[] test = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ,16,18,16,16,19,14,12};
		HeapSort hs = new HeapSort();
		hs.sort(test);

		for (int i = 0; i < test.length; i++)
			System.out.println(test[i]);

	}
}