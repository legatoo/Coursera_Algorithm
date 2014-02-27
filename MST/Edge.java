import java.util.Iterator;

public class Edge implements Comparable<Edge> {

	private final int v;
	private final int w;
	private int from;
	private int to;
	private final double weight;

	public Edge(){
		this.v = Integer.MIN_VALUE;
		this.w = Integer.MIN_VALUE;
		this.from = Integer.MAX_VALUE;
		this.to = Integer.MAX_VALUE;
		this.weight = Double.MIN_VALUE;
	}
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.from = Integer.MAX_VALUE;
		this.to = Integer.MAX_VALUE;
		this.weight = weight;
	}
	

	public int either() {
		return v;
	}

	public int other(int v) {
		if (v == this.v)
			return w;
		else if (v == this.w)
			return this.v;
		else
			throw new IllegalArgumentException("Illegal endpoint");

	}

	@Override
	public int compareTo(Edge that) {
		if (this.weight < that.weight)
			return -1;
		else if (this.weight > that.weight)
			return +1;
		else
			return 0;

	}

	public String toString() {
		return String.format("%d-%d %.5f", v, w, weight);
	}
	
	public double getWeight(){
		return this.weight;
	}
}