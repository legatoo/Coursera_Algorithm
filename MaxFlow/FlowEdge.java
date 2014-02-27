public class FlowEdge implements Comparable<FlowEdge> {

	// to
	private int to;
	// from
	private int from;
	// capacity
	private double capacity;
	// flow
	private double flow;

	public FlowEdge(int v, int w, double capacity){
		to = w;
		from = v;
		this.capacity = capacity;
		flow = 0;
	}
	public int to() {
		return to;
	}

	public int from() {
		return from;
	}

	public double capacity() {
		return capacity;
	}
	
	public double flow(){
		return flow;
	}
	
	public int other(int v){
		if (v == to)
			return from;
		else if(v == from)
			return to;
		else return -1;
	}

	public double residualCapacity(int v) {
		assert ((v == to) || (v == from));

		if (v == to)
			return capacity - flow; //forward
		if (v == from)
			return flow; //backward
		return 0;
	}
	
	public void addResidualCpacity(int v, double bottleNeck){
		if(v == to)
			flow += bottleNeck;
		if (v == from)
			flow -= bottleNeck;
	}
	
	public boolean minCutEdge(){
		if(flow < capacity && flow > 0)
			return true;
		else
			return false;
	}

	@Override
	public int compareTo(FlowEdge f) {
		// TODO Auto-generated method stub
		if ((this.to == f.to()) && (this.from == f.from()))
			return 0;
		else
			return -1;
				
	}

	public String toString() {
		return String.format("%d --> %d, %.2f/%.2f", from, to, flow, capacity);
	}

}