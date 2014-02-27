import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class T9<Value> {

	private static int R = 8;
	private int N = 0;
	private Node root = null;
	private Queue<String> q;

	// inside class
	private static class Node {
		//all the words correspond to the input sequence
		private Set<String> val; 
		private Node[] next = new Node[R];
	}

	// isEmpty
	public boolean isEmpty() {
		return N == 0;
	}

	// put
	public void put(String s, Value val) {
		root = put(root, s, val, 0);
		N++;
	}

	private Node put(Node x, String s, Value val, int pos) {
		if (x == null)
			return null;
		if (pos == s.length()) {
			x.val.add(s);
			return x;
		}
		char c = s.charAt(pos);
		x.next[c] = put(x.next[c], s, val, pos + 1);
		return x;
	}

	// get
	public Node get(String s) {
		return get(root, s, 0);
	}

	public Node get(Node x, String s, int pos) {
		if (x == null)
			return null;
		if (pos == s.length())
			return x;
		char c = s.charAt(pos);
		return get(x.next[c], s, pos + 1);
	}

	// delete
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
		}
		else{
			char c = s.charAt(pos);
			x.next[c] = delete(x.next[c], s, pos+1);
		}
		
		if(x.val != null)
			return x;
		for(int i =0; i< 8; i++)
			if(x.next[i].val != null)
				return x;
		return null;
	}

	// collect
	public Iterable<String> collect(String prefix){
		q = new LinkedList<>();
		Node t = get(prefix);
		if (t == null)
			return null;
		else if(t.val != null)
			q.add(prefix);
		collect(t, prefix);
		return q;
	}
	
	private void collect(Node x, String s){
		if(x == null)
			return ;
		if(x.val != null)
			q.add(s);
		
		for(int i = 0; i < 8; i++){
			collect(x.next[i], s+i);
		}
	}

	// longest prefix
	public String longestPrefix(String source){
		int length = search(root, source, 0, 0);
		return source.substring(0, length);
	}
	
	private int search(Node x, String s, int pos, int length){
		if(x == null)
			return length;
		if(x.val != null)
			length = pos;
		if(pos == s.length())
			return length;
		char c = s.charAt(pos);
		return search(x.next[c], s, pos+1, length);
	}
	
}