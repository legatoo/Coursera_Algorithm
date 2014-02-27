import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Week5 {
	public static void main(String[] args) throws IOException {
		BinarySearchTree<Integer, String> bts = new BinarySearchTree<>();
		BufferedReader br = new BufferedReader(new FileReader("read.txt"));
		try {

			String line = br.readLine();

			while (line != null) {
				String[] splited = line.split("\\s+");
				bts.put(Integer.valueOf(Integer.parseInt(splited[0])),
						splited[1]);
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		
		System.out.println(bts.search(Integer.valueOf(6)));
	}
}