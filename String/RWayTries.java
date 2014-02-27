import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RWayTries<Value> {
	private static int R = 256;
	private Node root = null;
	private int N = 0;

	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}

	
	private int charAt(String s, int index){
		if(index < s.length())
			return s.charAt(index);
		else
			return -1;
	}

	public void put(String s, Value val) {
		root = put(root, s, val, 0);
		N++;
	}

	private Node put(Node x, String s, Value val, int pos) {
		if (x == null)
			x =  new Node();
		if (pos == s.length()) {
			x.val = val;
			return x;
		}

		char c = s.charAt(pos);
		x.next[c] = put(x.next[c], s, val, pos + 1); 
		return x;
	}

	public void delete(String s) {
		root = delete(root, s, 0);
	}

	private Node delete(Node x, String s, int pos) {
		if (x == null)
			return null;
		if (pos == s.length()) {
			if (x.val != null)
				N--;
			x.val = null;
		} else {
			char c = s.charAt(pos);
			x.next[c] = delete(x.next[c], s, pos + 1);
		}

		if (x.val != null)
			return x;
		for (int r = 0; r < R; r++)
			if (x.next[r] != null)
				return x;
		return null;

	}
	
	public Value get(String s){
		Node x =  get(root, s, 0);
		if(x == null)
			return null;
		else
			return (Value) x.val;
	}
	
	private Node get(Node x, String s, int pos){
		if ( x == null) return null;
		if (pos == s.length())
			return x;
		char c = s.charAt(pos);
		return get(x.next[c], s, pos+1);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("rwaytrie.txt"));
		RWayTries<Integer> rwt = new RWayTries<>();
		
		try{
			String line = br.readLine();
			while(line != null){
				String[] splited = line.split("\\s+");
				rwt.put(splited[0], Integer.valueOf(splited[1]));
				line = br.readLine();
			}
			
		}
		finally{
			br.close();
		}
		
		System.out.println("Searching: shore");
		System.out.print(rwt.get("shore"));
	}

}