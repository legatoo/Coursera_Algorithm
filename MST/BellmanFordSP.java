import java.util.Arrays;
import java.util.LinkedList;

public class BellmanFordSP{
	private double[] distTo;
	private int[] edgeTo;
	private LinkedList<Integer> queue;
	private boolean[] marked;
	private int cost;
	
	public void bellmanfordSP(DiGraph dg, int s){
		distTo = new double[dg.size()];
		edgeTo = new int[dg.size()];
		marked = new boolean[dg.size()];
		queue = new LinkedList<>();
		cost = 0;
		
		Arrays.fill(distTo, Double.POSITIVE_INFINITY);
		Arrays.fill(marked, false);
		distTo[s] = 0;
		
		queue.add(s);
		marked[s] = true;
		
		//while (!queue.isEmpty() && !noNegetativeCycle())
		while(!queue.isEmpty() ){
			int v = queue.pollFirst();
			marked[v] = false;
			relax(dg, v);
		}
		
	}
	
	private void relax(DiGraph dg, int v){
		for (DiEdge e : dg.adj(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.getWeight()){
				distTo[w] = distTo[v] + e.getWeight();
				edgeTo[w] = v;
				if (!marked[w]){
					queue.add(w);
					marked[w] = true;
				}
			}
		}	
		
		if (cost++ % dg.size() == 0)
			System.out.print("negative cycle exists.\n");
	}
	
	private void findNegativeCycle(){
		
	}
	
	//an approach to detect negetative edge:
	// Bellman Ford doesn't always work, the problem is its a single source
	// shortest path algorithm, if the negative loop is not reachable from the
	// source you pick, it fails. However a little change to Bellman Ford could
	// help, instead of selecting a source vertex and initialise its distance to
	// 0, we can initialise all the distance to 0 and then run Bellman Ford.
	// This is equivalent to add a extra vertex s' which points to all the
	// vertexes in the original graph with 0 weight edge. Once Bellman Ford is
	// run on the graph and we found any vertex u and edge (u,v) that make d[u]
	// + w[u][v] < d[v], we know there must be a negative cycle leading to u,
	// tracking back from u in the predecessor graph and we'll find the cycle.
	//
	// DFS is not gonna work in any way, it's obviously not able to exhaust all
	// possible cycles. DFS can be used to find the presence of cycle in graph,
	// but no more.
}