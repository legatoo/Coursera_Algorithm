import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Bellman {
	private double[] distTo;
	private int[] edgeTo;
	Queue<DiEdge> processing;
	Queue<DiEdge> processing2;

	public Bellman(DiGraph dg) {
		distTo = new double[dg.size()];
		edgeTo = new int[dg.size()];

		for (int i = 0; i < dg.size(); i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
			edgeTo[i] = i;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sp.txt"));
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

		Bellman dij = new Bellman(dg);
		dij.bellman(dg, 0);
		for (int i = 0; i < dg.size(); i++) {
			System.out.print("path to " + String.valueOf(i) + " "
					+ String.valueOf(dij.pathLength(i)) + " :  ");
			for (DiEdge e : dij.printPath(dg, i))
				System.out.print(e.toString() + " ");
			System.out.println();
		}
	}

	public double pathLength(int v) {
		return distTo[v];
	}

	private Iterable<DiEdge> printPath(DiGraph dg, int i) {
		Queue<DiEdge> q = new LinkedList<>();

		while (i != edgeTo[i]) {
			q.add(dg.find(edgeTo[i], i));
			i = edgeTo[i];
		}
		return q;
	}

	public void bellman(DiGraph dg, int s) {
		distTo[s] = 0;
		// processing = new LinkedList<>();
		// processing2 = new LinkedList<>();

//		for (DiEdge e : dg.edges())
//			processing.add(e);

		for (int i = 0; i < dg.size(); i++) {
			for (int v = 0; v < dg.size(); v++)
				for (DiEdge e : dg.adj(v)) {
					relax(e);
				}
			//processing.addAll(processing2);
		}
	}

	private void relax(DiEdge e) {
		// processing.poll();
		int w = e.to();
		if (distTo[w] > distTo[e.from()] + e.getWeight()) {
			distTo[w] = distTo[e.from()] + e.getWeight();
			edgeTo[w] = e.from();
			//processing2.add(e);
		}
	}
}