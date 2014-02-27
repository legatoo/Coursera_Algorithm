import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Week5{
	public static void main(String[] args) throws IOException {
		LLRBtree<String, String> llrb = new LLRBtree<>();
		//BinarySearchTree<String, String> bst = new BinarySearchTree<>();
		
		BufferedReader br = new BufferedReader(new FileReader("read.txt"));
		try {

			String line = br.readLine();

			while (line != null) {
				String[] splited = line.split("\\s+");
				llrb.put(splited[0],splited[1]);
				//bst.insert(splited[0], splited[1]);
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		
		System.out.println(llrb.itemsInRange("A", "J"));
		llrb.delete("E");
		llrb.delete("I");
		llrb.delete("H");
//		llrb.delete("T");
//		llrb.delete("M");
//		llrb.delete("P");
//		llrb.delete("Q");
//		llrb.delete("H");
		
		System.out.println(llrb.itemsInRange("A", "J"));
		//System.out.println(llrb.numOfItemsInRange("J", "Q"));
		
		
		System.out.println("Tree height: " + String.valueOf(llrb.depthOfTree()));
		//System.out.println(bst.depthOfTree());
	}
}