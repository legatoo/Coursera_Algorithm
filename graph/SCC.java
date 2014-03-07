import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class SCC {
	
	private boolean[] marked;
	private int[] SCC;
	private Stack<Integer> rOrder;
	private int SCCNum = 0;

	
	public SCC(Graph g) {
		marked = new boolean[g.size()];
		SCC = new int[g.size()];
		rOrder = new Stack<>();
	}

	// phrase 1, computer topo order on reversed graph

	// phrase 2, use this order to computer scc
	public void findSCCs(Graph g) {
		DFS dfs = new DFS(g);
		
		for (Object o : dfs.reverseTopoOrder(g)) {
			if (!marked[(int) o]) {
				dfs(g, (int) o);
				//after the dfs, one cc has been found
				SCCNum++;
			}
		}
		
		for (int i = 0; i < g.size(); i++)
			System.out.print(String.valueOf(SCC[i]) + ":" + String.valueOf(i) + " ");

	}

	private void dfs(Graph g, int v) {
		marked[v] = true;
		SCC[v] = SCCNum;

		for (Object n : g.adj(v)) {
			if (!marked[(int) n]) {
				dfs(g, (int) n);
				
			}
		}
	}
	
	public Iterable<Integer> reverseOrder(){
		return rOrder;
	}
}