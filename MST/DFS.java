import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.ListIterator;
import java.util.Stack;

public class DFS {
	private boolean[] marked;
	private int[] edgeTo;
	ArrayList<ArrayList<DiEdge>> cycles;
	
	public boolean ifNegativeCycles(){
		return !cycles.isEmpty();
	}

	private void DFS(DiGraph g, int s, Stack<Integer> order) {
		marked[s] = true;
		for (Object v : g.adj(s)) {
			if (!marked[(int) v]) {
				DFS(g, (int) v, order);

			}
		}
		// here means has finished one vertex search
		order.push(s);
	}

	public DFS(DiGraph g) {
		// TODO Auto-generated constructor stub
		marked = new boolean[g.size()];
		edgeTo = new int[g.size()];

		for (int i = 0; i < g.size(); i++)
			edgeTo[i] = i;

	}

	// recursive
	private void DFS(DiGraph g, int s) {
		marked[s] = true;
		for (Object v : g.adj(s)) {
			if (!marked[(int) v]) {
				DFS(g, (int) v);
				edgeTo[(int) v] = s;
			}
		}
	}

	// find all possible cycles
	public void findCycle(DiGraph g) {
		Deque<Integer> stack = new ArrayDeque<>();
		cycles = new ArrayList<ArrayList<DiEdge>>();

		for (int i = 0; i < g.size(); i++) {
			stack.clear();
			for (int j = 0; j < g.size(); j++) {
				marked[j] = false;
				edgeTo[j] = j;
			}

			stack.push(i);

			while (!stack.isEmpty()) {
				int c = stack.pop();
				marked[c] = true;

				for (DiEdge e : g.adj(c)) {
					if (!marked[e.to()]) {
						stack.push(e.to());
						edgeTo[e.to()] = e.from();
					} else if (e.to() == i) {
						ArrayList<DiEdge> cyc = new ArrayList<DiEdge>();
						int s = e.from();
						// cyc.add();
						while (edgeTo[s] != s) {
							cyc.add(g.find(edgeTo[s], s));
							s = edgeTo[s];
						}
						if (cyc.size() < 3)
							continue;
						double sum = 0;
						for (DiEdge de : cyc) {
							sum += de.getWeight();
						}
						if (sum >= 0)
							continue; //only check negative cycle
						Collections.sort(cyc);
						if (!cycles.contains(cyc))
							cycles.add(cyc);
					}
				}
			}
		}

//		for (ArrayList<DiEdge> s : cycles) {
//			System.out.println(s.toString());
//		}
		if( !cycles.isEmpty())
			System.out.println("has negative cycle!");

	}
}