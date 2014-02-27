public class Graph<Value> {
	private int size;
	private int edgeNum;
	private Bag<Integer>[] graph;

	public Graph(int v) {
		this.size = v;
		edgeNum = 0;
		graph = (Bag<Integer>[]) new Bag[size];

		for (int i = 0; i < size; i++){
			graph[i] = new Bag<Integer>();
		}
	}

	public void addEdge(int k1, int k2) {
		assert (k1 >= 0) && (k1 < size);
		assert (k2 >= 0) && (k2 < size);

		edgeNum++;
		graph[k1].add(Integer.valueOf(k2));
		graph[k2].add(Integer.valueOf(k1));
	}
	
	public void addDirectedEdge(int k1, int k2) {
		assert (k1 >= 0) && (k1 < size);
		assert (k2 >= 0) && (k2 < size);

		edgeNum++;
		graph[k1].add(Integer.valueOf(k2));
		
	}
	
	public void reverseGraph(Graph g){
		for(int i = 0; i < size; i++){
			for(Object v: g.adj(i)){
				graph[(int)v].add(Integer.valueOf(i));
			}
		}
	}

	//interesting
	public Iterable<Integer> adj(int v) {
		assert (v >= 0) && (v < size);
		return graph[v];
	}
	

	public int size() {
		return size;
	}
}