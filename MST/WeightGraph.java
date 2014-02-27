import java.util.Set;
import java.util.TreeSet;

public class WeightGraph{
	private int size;
	private int edgeNum;
	private Bag<Edge>[] wg;
	
	public int size(){
		return size;
	}
	public WeightGraph(int v) {
		this.size = v;
		edgeNum = 0;
		wg = (Bag<Edge>[]) new Bag[size];

		for (int i = 0; i < size; i++){
			wg[i] = new Bag<Edge>();
		}
	}
	
	public void addEdge(Edge e){
		wg[e.either()].add(e);
		wg[e.other(e.either())].add(e);
		edgeNum++;
	}
	
	public Iterable<Edge> adj(int v){
		return wg[v];
	}
	
	public Iterable<Edge> edges(){
		Set<Edge> es = new TreeSet<Edge>();
		for (int i = 0; i < wg.length; i++)
			for(Edge e: adj(i)){
				es.add(e);
			}
		return es;
	}

	
}