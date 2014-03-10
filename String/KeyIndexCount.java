import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//in this category, this is no tree structure 
public class KeyIndexCount {
	int[] count;
	int[] aux;
	int R = 256;
	int cutoff = 10;
	
	//using index sorting to sort an array
	public String indexCountingSort(String s) {
		count = new int[R+1]; //Notice the size of count
		aux = new int[s.length()];
		StringBuilder result = new StringBuilder();
		
		//go through the string, count the frequency of each character
		//put the number of frequency 1 position behind of the actual position
		//since the count is used to record the starting position of every character
		//which equals to the end position of previous character
		for (int i = 0; i < s.length(); i++)
			count[s.charAt(i)+1]++;
		
		//convert counting array to index array. eg. the character with value 10 will start 
		//from position recorded in the 9th slot of index(counting) array
		for (int i = 0; i < R; i++)
			count[i+1] += count[i];
		
		//go through the original array again, and put characters onto the right
		//position
		for (int i = 0; i < s.length(); i++)
			aux[count[s.charAt(i)]++] = s.charAt(i);
		
		//copy the result back from auxiliary array
		for (int i = 0; i < s.length(); i++)
			result.append((char)aux[i]);

		return result.toString();
	}

	public String[] LSD(String[] s, int n, int x) {
		// x is the string length, we assume all strings have the same length
		int N = n;
		String[] aux = new String[N];

		// gows down from the string
		for (int pos = x - 1; pos >= 0; pos--) {
			count = new int[R + 1];

			for (int i = 0; i < N; i++) {
				count[s[i].charAt(pos) + 1]++;
			}

			for (int r = 0; r < R; r++)
				count[r + 1] += count[r];

			for (int i = 0; i < N; i++)
				aux[count[s[i].charAt(pos)]++] = s[i];

			for (int i = 0; i < N; i++)
				s[i] = aux[i];
		}

		return s;
	}

	public static int charAt(String s, int pos) {
		if (pos < s.length())
			return s.charAt(pos);
		else
			return -1;
	}

	public String[] MSDsort(String[] s) {
		String[] aux = new String[s.length];
		MSD(aux, s, 0, s.length - 1, 0);
		return s;
	}
	
	//with the same prefix, shorter strings will be ordered to the front
	public void MSD(String[] aux, String[] s, int lo, int hi, int pos) {
		if (hi <= lo)
			return;
		if (hi >= lo + cutoff){
			insertSort(s, lo, hi, pos);
			return;
		}

		//refer to the page 711 of <<algorithms>> for the details of this "+2"
		int[] count = new int[R + 2];

		for (int i = lo; i <= hi; i++)
			count[charAt(s[i], pos) + 2]++;

		for (int r = 0; r < R+1; r++)
			count[r + 1] += count[r];

		for (int i = lo; i <= hi; i++)
			aux[count[charAt(s[i], pos)+1]++] = s[i];

		for (int i = lo; i <= hi; i++)
			s[i] = aux[i - lo];

		for (int r = 0; r < R; r++)
			MSD(aux, s, lo + count[r], lo + count[r + 1] - 1, pos + 1);

	}
	
	public void insertSort(String[] s, int lo, int hi, int pos){
		for (int i = lo; i <= hi; i++)
			for(int j = i; j >lo; j--)
				if( s[j].charAt(pos) < s[j-1].charAt(pos))
				//s[j].subString(pos).compareTo(s[j-1].subString(pos))
					swap(s, j, j-1);
	}
	
	private void swap(String[] s, int i, int j){
		String t = s[j];
		s[j] = s[i];
		s[i] = t;
	}
	
	//3-way radix method
	public String[] threeWayRadix(String[] s){
		threeWayRadixSort(s, 0, s.length-1, 0);
		return s;
	}
	
	private void threeWayRadixSort(String[] s, int lo, int hi, int pos){
		if(hi >= lo + cutoff){
			insertSort(s, lo, hi, pos);
			return;
		}
		
		//3 way partition
		int lt = lo;
		int gt = hi;
		int i = lt+1;
		
		int v = charAt(s[lo], pos); //pivot
		
		while(i <= gt){
			if(charAt(s[i], pos) < v)
				swap(s, lt++, i++);
	
			else if (charAt(s[i], pos) > v)
				swap(s, i, gt--);
			else
				i++;
		}
		
		threeWayRadixSort(s, lo, lt-1, pos);
		if (v >= 0) threeWayRadixSort(s, lt, gt, pos++); //if v = -1 means we reach the end of string
		threeWayRadixSort(s, gt+1, hi, pos);
	}
	

	public static void main(String[] args) throws IOException {
		int[] test = new int[] { 29, 30, 31, 32, 28, 27, 26, 26, 26, 26, 26,
				25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10,
				9, 9, 9, 9, 9, 9, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 0 };
		String[] strings;
		int size;
		// KeyIndexCount kic = new KeyIndexCount();
		// for(int i: kic.indexCountingSort(test))
		// System.out.println(i);

		BufferedReader br = new BufferedReader(new FileReader("words3.txt"));
		try {
			int p = 0;
			String line = br.readLine();
			size = Integer.valueOf(line);
			strings = new String[size];
			line = br.readLine();

			while (line != null) {
				String[] splited = line.split("\\s+");
				for (int i = 0; i < splited.length; i++)
					strings[p++] = splited[i];
				line = br.readLine();
			}

		} finally {
			br.close();
		}
		
		KeyIndexCount kic = new KeyIndexCount();
		System.out.println(kic.indexCountingSort("jjiojfalfhuehzqibzhajbaqirambzmhfka"));

		//KeyIndexCount kic = new KeyIndexCount();
		for (String s : kic.LSD(strings, size, 3))
			System.out.println(s);

		System.out.println();

		for (String s : kic.MSDsort(strings))
			System.out.println(s);
		
		System.out.println();
//
//		for (String s : kic.threeWayRadix(strings))
//			System.out.println(s);

	}
}