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
		
		while(!queue.isEmpty()){
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
		//use dfs to find the cycle.
		
	}
}