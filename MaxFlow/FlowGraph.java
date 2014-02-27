import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class FlowGraph {
	
	private int size;
	private Bag<FlowEdge>[] fg;

	public FlowGraph(int V) {
		size = V;
		fg = (Bag<FlowEdge>[]) new Bag[size];
		for (int i = 0; i < size; i++) {
			fg[i] = new Bag<>();
		}
	}

	public void addFlowEdge(FlowEdge e) {
		int v = e.from();
		int w = e.to();

		fg[w].add(e);
		fg[v].add(e);
	}

	public Iterable<FlowEdge> edges(){
		ArrayList<FlowEdge> es = new ArrayList<>();
		for (int i = 0; i < fg.length; i++)
			for(FlowEdge e: adj(i)){
				es.add(e);
			}
		return es;
	}
	
	public Iterable<FlowEdge> adj(int v) {
		return fg[v];
	}

	public int size() {
		return size;
	}

}