import java.util.LinkedList;
import java.util.Queue;

public class MST {
	private Queue<Edge> mstPath = new LinkedList<>();
	private double sum = 0;
	private boolean[] marked;
	private double[] distTo;
	private int[] edgeTo;
	int size;

	/**
	 * kruskal mst method #1: order all edges in ascending order #2: add min
	 * weight edge to mst if there is no cycle be generated
	 */
	public void kruskalMST(WeightGraph wg) {
		minQueue<Edge> mq = new minQueue<>();
		for (Edge e : wg.edges())
			mq.insert(e);

		UnionFind uf = new UnionFind(wg);

		while (!mq.isEmpty() && mstPath.size() < wg.size() - 1) {
			Edge e = mq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (!uf.ifConnected(v, w)) {
				mstPath.add(e);
				uf.union(v, w);
			}
		}

	}

	/**
	 * Lazy Prim MST method #1: initialize stage, start from vertex 0, put all
	 * vertices points to 0 to minQueue #2: put the min weight edge points to 0
	 * into tree, if the other vertex(w) is not in tree yet #3: put all the edge
	 * points to w (with endpoints are not in tree) to mq #4: repeat above until
	 * there are V-1 edges are in tree or minQue is empty.
	 * 
	 * @param wg
	 */
	public void lazyPrimMST(WeightGraph wg) {
		size = wg.size();
		marked = new boolean[wg.size()];
		minQueue<Edge> mq = new minQueue<>();

		pointsToMe(mq, wg, 0);

		while (!mq.isEmpty()) {
			Edge min = mq.delMin();
			int v = min.either();
			int w = min.other(v);
			if (marked[v] && marked[w])
				continue;

			mstPath.add(min); // this is a crossing with min weight, so must be
								// in mst
			if (!marked[v])
				pointsToMe(mq, wg, v);
			if (!marked[w])
				pointsToMe(mq, wg, w);
		}
	}

	private void pointsToMe(minQueue<Edge> mq, WeightGraph wg, int v) {
		marked[v] = true;
		for (Edge e : wg.adj(v))
			if (!marked[e.other(v)])
				mq.insert(e);
	}

	public void eagerPrimMST(WeightGraph wg) {
		size = wg.size();
		marked = new boolean[wg.size()];
		distTo = new double[wg.size()];
		edgeTo = new int[wg.size()];
		
		for (int i = 0; i < wg.size(); i++){
			distTo[i] = Double.POSITIVE_INFINITY;
			edgeTo[i] = i;
		}

		indexMinQ<Double> imq = new indexMinQ<>(wg.size());
		pointsToMe(imq, wg, 0);

		while (!imq.isEmpty()) {
			int v = imq.delMin();
			pointsToMe(imq, wg, v);
		}

	}

	private void pointsToMe(indexMinQ<Double> imq, WeightGraph wg, int v) {
		//System.out.println(v);
		marked[v] = true;
		for (Edge e : wg.adj(v)) {
			int w = e.other(v);
			if (marked[v] && marked[w])
				continue;
			if (e.getWeight() < distTo[w]) {
				distTo[w] = e.getWeight();
				edgeTo[w] = v;
				if (imq.contains(w))
					imq.decreaseKey(w, distTo[w]);
				else
					imq.insert(w, distTo[w]);
			}
		}
	}

	public Iterable<Edge> printMST() {
		return mstPath;
	}
	
	public void printEagerPrimMST(){
		double sum = 0;
		for(int i = 0; i < size; i++)
			if(i != edgeTo[i]){
				System.out.println(String.format("%d -> %d, %.5f", edgeTo[i], i, distTo[i]));
				sum += distTo[i];
			}
		System.out.println(sum);
	}

	public double weightSum() {
		for (Edge e : mstPath)
			sum += e.getWeight();
		return sum;
	}
}