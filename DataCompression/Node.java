public class Node implements Comparable<Node>{
	public char ch;
	private int freq;
	public Node left, right;
	
	public boolean isLeaf(){
		return (left == null && right == null);
	}
	
	public Node(char c, int f, Node l, Node r){
		ch = c;
		freq = f;
		left = l;
		right = r;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Integer.valueOf(freq).compareTo(o.freq);
	}
	
	public boolean less(Node o){
		if(Integer.valueOf(freq).compareTo(o.freq) == -1)
			return true;
		else 
			return false;
	}
	
	public int frequency(){
		return freq;
	}
	
}