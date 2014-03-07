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

	// reverse the graph first
	// then do dfs on this reversed graph --> reversed topo order
	public Iterable<Integer> reverseTopoOrder(Graph g) {

		Stack<Integer> s = new Stack<>();
		Graph rg = new Graph(g.size());
		rg.reverseGraph(g);

		for (int i = 0; i < rg.size(); i++) {
			if (!marked[i]) {
				DFS(rg, i, s);
			}
		}

		//
		ArrayList<Integer> order = new ArrayList<Integer>();
		for (ListIterator<Integer> lr = s.listIterator(s.size()); lr
				.hasPrevious();)
			order.add(lr.previous());

		return order;
	}

	private void DFS(Graph g, int s, Stack<Integer> order) {
		marked[s] = true;
		for (Object v : g.adj(s)) {
			if (!marked[(int) v]) {
				DFS(g, (int) v, order);

			}
		}
		// here means has finished one vertex search
		order.push(s);
	}

	public DFS(Graph g) {
		// TODO Auto-generated constructor stub
		marked = new boolean[g.size()];
		edgeTo = new int[g.size()];

		for (int i = 0; i < g.size(); i++)
			edgeTo[i] = i;

	}

	// recursive
	private void DFS(Graph g, int s) {
		marked[s] = true;
		for (Object v : g.adj(s)) {
			if (!marked[(int) v]) {
				DFS(g, (int) v);
				edgeTo[(int) v] = s;
			}
		}
	}

	public void printPath(Graph g, int a, int b) {
		DFS(g, a);
		if (marked[b]) {
			System.out.print(String.valueOf(b) + "-->");
			while (b != edgeTo[b]) {
				System.out.print(String.valueOf(edgeTo[b]) + "-->");
				b = edgeTo[b];
			}
			System.out.print("end");
		} else
			System.out.println("no connection.");
	}

	// find all possible cycles
	public void findCycle(Graph g) {
		Deque<Integer> stack = new ArrayDeque<>();
		ArrayList<ArrayList<Integer>> cycles = new ArrayList<ArrayList<Integer>>();

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

				for (Object n : g.adj(c)) {
					if (!marked[(int) n]) {
						stack.push((int) n);
						edgeTo[(int) n] = c;
					} else if ((int) n == i) {
						ArrayList<Integer> cyc = new ArrayList<Integer>();
						int s = (int) c;
						cyc.add(i);
						while (edgeTo[s] != s) {
							cyc.add(s);
							s = edgeTo[s];
						}
						if (cyc.size() < 3)
							continue;
						Collections.sort(cyc);
						if (!cycles.contains(cyc)){
							cycles.add(cyc);
							System.out.println(cyc);
						}
					}
				}
			}
		}
	}

//	// find all possible cycles
//	public void findCycle(DiGraph g) {
//		Deque<Integer> stack = new ArrayDeque<>();
//		ArrayList<ArrayList<Integer>> cycles = new ArrayList<ArrayList<Integer>>();
//
//		for (int i = 0; i < g.size(); i++) {
//			stack.clear();
//			for (int j = 0; j < g.size(); j++) {
//				marked[j] = false;
//				edgeTo[j] = j;
//			}
//
//			stack.push(i);
//
//			while (!stack.isEmpty()) {
//				int c = stack.pop();
//				marked[c] = true;
//
//				for (DiEdge e : g.adj(c)) {
//					if (!marked[e.to()]) {
//						stack.push(e.to());
//						edgeTo[e.to()] = e.from();
//					} else if (e.to() == i) {
//						ArrayList<DiEdge> cyc = new ArrayList<DiEdge>();
//						int s = e.to();
//						// cyc.add();
//						while (edgeTo[s] != s) {
//							cyc.add(g.find(edge[s], s));
//							s = edgeTo[s];
//						}
//						if (cyc.size() < 3)
//							continue;
//						Collections.sort(cyc);
//						if (!cycles.contains(cyc))
//							cycles.add(cyc);
//					}
//				}
//			}
//		}
//
//		for (ArrayList<Integer> s : cycles) {
//			System.out.println(s.toString());
//		}
//
//	}
}