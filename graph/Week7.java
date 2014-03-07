import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Week7 {
	public static void main(String[] args) throws IOException {
		Graph g;

		BufferedReader br = new BufferedReader(new FileReader("directedGraph.txt"));
		try {
			String vertices = br.readLine();
			g = new Graph(Integer.valueOf(vertices));

			String line = br.readLine();
			while (line != null) {
				String[] splited = line.split("\\s+");
				g.addDirectedEdge(Integer.valueOf(splited[0]),
						Integer.valueOf(splited[1]));
				line = br.readLine();
			}
		} finally {
			br.close();
		}

		System.out.println("Cycles:");
		DFS dfs = new DFS(g);
		dfs.findCycle(g);
//		
//		System.out.println();
//		
//		System.out.println("bfs:");
//		BFS bfs = new BFS(g);
//		bfs.printPath(g, 1, 2);
		
//		Bipartite bp = new Bipartite(g);
//		bp.bipartiteTest(g);
		
		SCC scc = new SCC(g);
		scc.findSCCs(g);
	}
}