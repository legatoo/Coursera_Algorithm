import java.util.LinkedList;

public class BFS {
	private boolean[] marked;
	private int[] edgeTo;
	private int[] dist;
	private LinkedList<Integer> q;

	public BFS(Graph g) {
		// TODO Auto-generated constructor stub
		marked = new boolean[g.size()];
		edgeTo = new int[g.size()];
		dist = new int[g.size()];
		
		q = new LinkedList<Integer>();

		for (int i = 0; i < g.size(); i++){
			dist[i] = 0;
			edgeTo[i] = i;
		}

	}

	private void BFS(Graph g, int s) {
		marked[s] = true;
		q.add(s);

		while (!q.isEmpty()) {
			int current = q.poll();
			for (Object v : g.adj(current)) {
				if (!marked[(int) v]) {
					marked[(int) v] = true;
					dist[(int)v] = dist[current]+1;
					edgeTo[(int) v] = current;
					q.add((int) v);
				}
			}

		}
	}

	public void printPath(Graph g, int a, int b) {
		BFS(g, a);
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
}