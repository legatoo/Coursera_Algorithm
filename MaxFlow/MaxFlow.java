import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class MaxFlow {
	private boolean[] marked;
	private FlowEdge[] edgeTo;
	private FlowEdge[] edgeToForMincut;
	private double flow;
	private static FlowGraph fg;
	private Set<FlowEdge> minCut;

	public MaxFlow(FlowGraph fg) {
		marked = new boolean[fg.size()];
		edgeTo = new FlowEdge[fg.size()];
		flow = 0.0;

		for (int i = 0; i < fg.size(); i++)
			edgeTo[i] = null;
	}

	// hasShortest path
	public boolean hasShortestPath(FlowGraph fg, int s, int t) {
		Arrays.fill(marked, false);
		Arrays.fill(edgeTo, null);

		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		marked[s] = true;

		while (!q.isEmpty()) {
			int v = q.poll();

			for (FlowEdge e : fg.adj(v)) {
				int w = e.other(v);
				if (e.residualCapacity(w) > 0 && !marked[w]) {
					marked[w] = true;
					edgeTo[w] = e;
					q.add(w);
				}
			}
		}

		return marked[t];
	}

	// ford-fulkerson algorithm
	public void ffMaxFlow(FlowGraph fg, int s, int t) {
		while (hasShortestPath(fg, s, t)) {
			double bottleNeck = Double.POSITIVE_INFINITY;

			for (int v = t; v != s; v = edgeTo[v].other(v))
				bottleNeck = Math
						.min(bottleNeck, edgeTo[v].residualCapacity(v));

			for (int v = t; v != s; v = edgeTo[v].other(v))
				edgeTo[v].addResidualCpacity(v, bottleNeck);

			flow += bottleNeck;
		}
	}

	public Iterable<FlowEdge> minCut(FlowGraph fg, int source) {
		Arrays.fill(marked, false);

		Deque<Integer> s = new ArrayDeque<>();
		Set<Integer> set = new TreeSet<>();
		minCut = new TreeSet<>();
		s.push(source);

		// after this dfs search, will get a set of vertices on one side of
		// minCut
		while (!s.isEmpty()) {
			int v = s.pop();
			marked[v] = true;

			for (FlowEdge e : fg.adj(v)) {
				int w = e.other(v);
				if (!marked[w]) {
					if (e.residualCapacity(w) > 0 && e.residualCapacity(v) > 0) {
						s.push(w);
						set.add(e.to());
						set.add(e.from());
					}
				}

			}
		}

		//find edges that one end is in subset A, and the other end is in subset B
		for (FlowEdge i : fg.edges()) {
			if (set.contains(i.to()) && set.contains(i.from()))
				continue;
			if (!set.contains(i.to()) && !set.contains(i.from()))
				continue;
			if (set.contains(i.from()))
				minCut.add(i);
		}

		return minCut;

	}

	public double maxFlow() {
		return flow;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("maxflow.txt"));
		// FlowGrpah fg;
		try {
			String line = br.readLine();
			fg = new FlowGraph(Integer.valueOf(line));
			line = br.readLine();

			while (line != null) {
				String[] splited = line.split("\\s+");
				FlowEdge e = new FlowEdge(Integer.valueOf(splited[0]),
						Integer.valueOf(splited[1]), Double.valueOf(splited[2]));
				fg.addFlowEdge(e);
				line = br.readLine();
			}
		} finally {
			br.close();
		}

		
		/**
		 * fg is actually a undirected graph, but still has concept like to and
		 * from according to current vertex is weather to or from, different
		 * residual capacity will be returned.
		 */
		MaxFlow mf = new MaxFlow(fg);

		System.out.print("Max Flow Value: ");
		mf.ffMaxFlow(fg, 0, fg.size() - 1);
		System.out.println(mf.flow);

		System.out.println("\nThe minimum cut edges are: ");
		for (FlowEdge e : mf.minCut(fg, 0))
			System.out.println(e.toString());
	}
}