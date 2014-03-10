import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class TST<Value> {
	private Node root = null;
	private int N = 0;
	Queue<String> q = new LinkedList<>();

	private class Node {
		private Value val;
		private char ch; //tst
		private Node left, mid, right; //tst
	}

	public boolean isEmpty() {
		return N == 0;
	}

	//recursively put
	public void put(String s, Value val) {
		root = put(root, s, val, 0);
		N++;
	}

	private Node put(Node x, String s, Value val, int pos) {

		char c = s.charAt(pos);

		if (x == null) {
			x = new Node();
			x.ch = c;
		}

		if (c < x.ch)
			x.left = put(x.left, s, val, pos);
		else if (c > x.ch)
			x.right = put(x.right, s, val, pos);
		else if(pos < s.length()-1)
			x.mid = put(x.mid, s, val, pos + 1);
		else
			x.val = val;

		return x;
	}

	public void delete(String s) {
		root = delete(root, s, 0);
	}

	private Node delete(Node x, String s, int pos) {
		if (x == null)
			return null;
		if (pos == s.length() -1) {
			if (x.val != null)
				N--;
			x.val = null;
			return x;
		} else {
			char c = s.charAt(pos);
			if (c < x.ch)
				x.left = delete(x.left, s, pos);
			else if (c > x.ch)
				x.right = delete(x.right, s, pos);
			else
				x.mid = delete(x.mid, s, pos + 1);
		}

		if (x.val != null)
			return x;
		if (x.left == null && x.mid == null && x.right == null)
			return null;
		return x;
	}

	public Node get(String s) {
		Node x = get(root, s, 0);
		if (x == null)
			return null;
		return x;
	}

	private Node get(Node x, String s, int pos) {
		if (x == null)
			return null;
		char c = s.charAt(pos);

		if (c < x.ch)
			return get(x.left, s, pos);
		else if (c > x.ch)
			return get(x.right, s, pos);
		else if(pos < s.length()-1)
			return get(x.mid, s, pos + 1);
		else
			return x;

	}

	public Iterable<String> allPerfixStrings(String s) {
		q.clear();
		collect(root, s);
		return q;
	}

	private void collect(Node x, String prefix) {
		if (x == null)
			return;
		collect(x.left, prefix);

		if (x.val != null)
			q.add(prefix+x.ch);

		collect(x.mid, prefix+x.ch);
		collect(x.right, prefix);

	}

	public Iterable<String> perfixs(String prefix){
		q.clear();
		Node x = get(prefix);
		if (x == null) return q;
		if (x.val != null) q.add(prefix);

		//pass the mid branch to collect function
		collect(x.mid, prefix);
		return q;
	}
	
	//for searching the longest prefix string
	//maintain a int value to remember the length of
	//matched prefix
	public String longestPerfix(String s){
		int length = searchLongestPrefix(root, s, 0, 0);
		return s.substring(0, length);
	}
	
	private int searchLongestPrefix(Node x, String s, int pos, int length){
		char c = s.charAt(pos);
		if ( x == null)
			return length;
		if(x.val != null)
			length = pos;
		if (c < x.ch)
			return searchLongestPrefix(x.left, s, pos, length);
		else if (c > x.ch)
			return searchLongestPrefix(x.right, s, pos, length);
		else if(pos < s.length()-1)
			return searchLongestPrefix(x.mid, s, pos + 1, length);
		else
			return length;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("rwaytrie.txt"));
		TST<Integer> rwt = new TST<>();

		try {
			String line = br.readLine();
			while (line != null) {
				String[] splited = line.split("\\s+");
				rwt.put(splited[0], Integer.valueOf(splited[1]));
				line = br.readLine();
			}

		} finally {
			br.close();
		}

		System.out.print("Searching: shore  -->  ");

		System.out.println(rwt.longestPerfix("shellafgth"));

		for(String s: rwt.perfixs("sh"))
			System.out.println(s);
	}
}