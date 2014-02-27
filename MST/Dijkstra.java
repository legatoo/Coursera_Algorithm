import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Dijkstra {
	private double[] distTo;
	private DiEdge[] edgeTo;
	indexMinQ<Double> imq;

	public Dijkstra(DiGraph dg) {
		distTo = new double[dg.size()];
		edgeTo = new DiEdge[dg.size()];
		imq = new indexMinQ<Double>(dg.size());

		for (int i = 0; i < dg.size(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
			edgeTo[i] = null;
		}
	}

	public void sp(DiGraph dg, int source) {
		distTo[source] = 0.0;
		imq.insert(source, distTo[source]);
		while (!imq.isEmpty()) {
			int v = imq.delMin();
			relax(dg, v);
		}

	}

	public void relax(DiGraph dg, int v) {
		for (DiEdge e : dg.adj(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.getWeight()) {
				distTo[w] = distTo[v] + e.getWeight();
				edgeTo[w] = e;
				if (imq.contains(w)) {
					imq.decreaseKey(w, distTo[w]);

				} else
					imq.insert(w, distTo[w]);
			}
		}
	}

	public boolean hasPath(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	public double pathLength(int v) {
		return distTo[v];
	}

	public Iterable<DiEdge> printPath(int v) {
		assert hasPath(v);

		Stack<DiEdge> path = new Stack<>();
		while (edgeTo[v] != null) {
			path.add(edgeTo[v]);
			v = edgeTo[v].from();
		}

		return path;
	}

	public static void ifNegativeCycle(DiGraph dg) {
		DFS dfs = new DFS(dg);
		dfs.findCycle(dg); // return flase if has negative cycle

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new FileReader("sp.txt"));
		DiGraph dg;
		try {
			String line = br.readLine();
			dg = new DiGraph(Integer.valueOf(line));
			line = br.readLine();

			while (line != null) {

				String[] splited = line.split("\\s+");
				DiEdge e = new DiEdge(Integer.valueOf(splited[0]),
						Integer.valueOf(splited[1]), Double.valueOf(splited[2]));
				dg.addEdge(e);
				line = br.readLine();
			}
		} finally {
			br.close();
		}

		//ifNegativeCycle(dg);
		
		Dijkstra dij = new Dijkstra(dg);
		dij.sp(dg, 0);
		for (int i = 0; i < dg.size(); i++) {
			System.out.print("path to " + String.valueOf(i) + " "
					+ String.valueOf(dij.pathLength(i)) + " :  ");
			for (DiEdge e : dij.printPath(i))
				System.out.print(e.toString() + " ");
			System.out.println();
		}
		

	}
}