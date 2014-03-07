import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

//in this category, this is no tree structure 
public class KeyIndexCount {
	int[] count;
	int[] aux;
	int R = 256;
	int cutoff = 3;

	public int[] indexCountingSort(int[] s) {
		count = new int[s.length + 1];
		aux = new int[s.length];

		// N times loops, 2N times array access(read & write)
		for (int i = 0; i < s.length; i++)
			count[s[i] + 1]++;

		// N times loop, 2N times array access(read & write)
		for (int i = 1; i <= s.length; i++)
			count[i] += count[i - 1];

		// N times loop, 3N times array access(read & write)
		for (int i = 0; i < s.length; i++)
			aux[count[s[i]]++] = s[i];

		// N times loop, N times write
		for (int i = 0; i < s.length; i++)
			s[i] = aux[i];

		// total, 4N time loop + 4N write
		return s;
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

	public void MSD(String[] aux, String[] s, int lo, int hi, int pos) {
		if (hi >= lo + cutoff){
			insertSort(s, lo, hi, pos);
			return;
		}

		// R+1 --> MAKE SPACE SO THAT THE FIRST ELEMENT OF ACCUMULATE ARRAY IS
		// 0(BEGIN FROM 0)
		// R+2 --> BESIDE THE RESAON BEFORE, WE NEED TO MAKE SPACE FOR RECURSIVE
		int[] count = new int[R + 1];

		for (int i = lo; i <= hi; i++)
			count[charAt(s[i], pos) + 1]++;

		for (int r = 0; r < R; r++)
			count[r + 1] += count[r];

		for (int i = lo; i <= hi; i++)
			aux[count[charAt(s[i], pos)]++] = s[i];

		for (int i = lo; i <= hi; i++)
			s[i] = aux[i - lo];

		for (int r = 0; r < R; r++)
			MSD(aux, s, lo + count[r], lo + count[r + 1] - 1, pos + 1);

	}
	
	public void insertSort(String[] s, int lo, int hi, int pos){
		for (int i = lo; i <= hi; i++)
			for(int j = i; j >lo; j--)
				if( s[j].charAt(pos) < s[j-1].charAt(pos))
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
		for (String s : kic.LSD(strings, size, 3))
			System.out.println(s);

		System.out.println();

		for (String s : kic.MSDsort(strings))
			System.out.println(s);
		
		System.out.println();

		for (String s : kic.threeWayRadix(strings))
			System.out.println(s);

	}
}