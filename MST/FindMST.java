import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindMST {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("mst.txt"));
		WeightGraph wg;
		try {
			String line = br.readLine();
			wg = new WeightGraph(Integer.valueOf(line));
			line = br.readLine();

			while (line != null) {
				String[] splited = line.split("\\s+");
				Edge e = new Edge(Integer.valueOf(splited[0]),
						Integer.valueOf(splited[1]), Double.valueOf(splited[2]));
				wg.addEdge(e);
				line = br.readLine();
			}
		} finally {
			br.close();
		}
		
		MST mst = new MST();
		MST mst2 = new MST();
		MST mst3 = new MST();
		
		mst.kruskalMST(wg);
		mst2.lazyPrimMST(wg);
		
		for(Edge e: mst.printMST())
			System.out.println(e.toString());
		System.out.println(mst.weightSum());
		
		for(Edge e: mst2.printMST())
			System.out.println(e.toString());
		System.out.println(mst2.weightSum());
		
		
		mst3.eagerPrimMST(wg);
		mst3.printEagerPrimMST();
		
		
	}
}