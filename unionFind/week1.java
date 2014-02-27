import java.util.Scanner;


public class week1{
	public static void main(String[] args){
		System.out.print("Input array size:\n");
		Scanner scanner = new Scanner(System.in);
		int arraySize = scanner.nextInt();
		unionFind uf = new unionFind();
		int[] array = new int[arraySize];
		for ( int i = 0; i < array.length; i++)
			array[i] = i;
		
		System.out.print("input integer pair:\n");
		while(scanner.hasNext())
		{
			int p = scanner.nextInt();
			int q = scanner.nextInt();
			
			if(!uf.find(array, p, q))
			{
				uf.uniionfindwithweight(array, p, q);
			}
		}
	}
}