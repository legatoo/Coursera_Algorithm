public class Node implements Comparable<Node> {

	int x;

	public Node(int i) {
		x = i;
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Integer.valueOf(x).compareTo(Integer.valueOf(o.x));
	}

}