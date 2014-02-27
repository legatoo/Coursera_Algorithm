public class HeapSort {

	private int N;

	public void sort(int[] array) {
		N = array.length;
		heapOrder(array);
		
		//pay attention!!!! cant use N to iterate the array this time!!!!!!!!!
		//N is changing with the sorting progress.
		for(int i = 0; i < array.length; i++){
			swap(array, 0, N-1);
			N-=1;
			sink(array, 0);
			
		}
		
	}

	private void heapOrder(int[] array) {
		for (int i = (N - 2) / 2; i >= 0; i--)
			sink(array, i);
	}

	private void sink(int[] array, int index){
		while(2*index +1 <= N-1){
			int cindex = 2*index +1;
			
			if(cindex +1 <= N-1 && less(array, cindex, cindex+1))
			{
				cindex+=1;
				if(less(array, index, cindex)){
					swap(array, index, cindex);
					index = cindex;
				}
				else
					break;
			}
			else if (less(array, index, cindex)){
				swap (array, index, cindex);
				index = cindex;
			}
			else
				break;
		}
	}

	private boolean less(int[] array, int index1, int index2) {
		return Integer.valueOf(array[index1]).compareTo(
				Integer.valueOf(array[index2])) < 0;
	}

	private void swap(int[] array, int index1, int index2) {
		int swap = array[index2];
		array[index2] = array[index1];
		array[index1] = swap;
	}
}