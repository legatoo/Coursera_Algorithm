public class DiEdge implements Comparable<DiEdge> {
	private final int from;
	private final int to;
	private final double weight;

	public DiEdge() {
		this.from = Integer.MAX_VALUE;
		this.to = Integer.MAX_VALUE;
		this.weight = Double.MIN_VALUE;
	}

	public DiEdge(int v, int w, double weight) {
		this.from = v;
		this.to = w;
		this.weight = weight;
	}

	public int from() {
		return from;
	}

	public int to() {
		return to;
	}

	@Override
	public int compareTo(DiEdge that) {
		if (this.weight < that.weight)
			return -1;
		else if (this.weight > that.weight)
			return +1;
		else
			return 0;

	}

	public String toString() {
		return String.format("%d-%d %.5f", from, to, weight);
	}

	public double getWeight() {
		return this.weight;
	}
	
	
}