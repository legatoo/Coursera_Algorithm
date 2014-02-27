import java.util.Set;
import java.util.TreeSet;

public class DiGraph{
	private int size;
	private int edgeNum;
	private Bag<DiEdge>[] wg;
	
	public int size(){
		return size;
	}
	public DiGraph(int v) {
		this.size = v;
		edgeNum = 0;
		wg = (Bag<DiEdge>[]) new Bag[size];

		for (int i = 0; i < size; i++){
			wg[i] = new Bag<DiEdge>();
		}
	}
	
	public void addEdge(DiEdge e){
		wg[e.from()].add(e);
		edgeNum++;
	}
	
	public Iterable<DiEdge> adj(int v){
		return wg[v];
	}
	
	public Iterable<DiEdge> edges(){
		Set<DiEdge> es = new TreeSet<DiEdge>();
		for (int i = 0; i < wg.length; i++)
			for(DiEdge e: adj(i)){
				es.add(e);
			}
		return es;
	}
	
	public DiEdge find(int from, int to){
		for(DiEdge e: adj(from)){
			if(e.to() == to)
				return e;
		}
		return null;
	}

	
}