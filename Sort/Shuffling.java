import java.util.Random;

public class Shuffling{
	public void shuffer(int[] array){
		for (int i = 0; i < array.length; i++){
			Random random = new Random();
			int r = random.nextInt(i+1);
			if(i != r)
				swap(array, i, r);
		}
	}
	
	public void swap(int[] array, int index1, int index2) {
		int swap = array[index2];
		array[index2] = array[index1];
		array[index1] = swap;
	}
}