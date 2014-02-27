import java.util.Arrays;

public class UnionFind {
	private int[] indexArray;
	private int[] treeSize;

	public UnionFind(WeightGraph wg) {
		indexArray = new int[wg.size()];
		treeSize = new int[wg.size()];
		Arrays.fill(treeSize, 1);

		for (int i = 0; i < indexArray.length; i++) {
			indexArray[i] = i;
		}
	}

	// union
	public void union(int i, int j) {
		int ri = root(i);
		int rj = root(j);
		if (ri == rj)
			return;
		if (treeSize[ri] < treeSize[rj]) {
			indexArray[ri] = rj;
			treeSize[rj] += treeSize[ri];
		} else {
			indexArray[rj] = ri;
			treeSize[ri] += treeSize[rj];
		}
	}

	// ifConnected
	public boolean ifConnected(int i, int j){
		return root(i) == root(j);
	}
	// root
	private int root(int index) {
		while (index != indexArray[index]) {
			index = indexArray[index];
			indexArray[index] = indexArray[indexArray[index]];
		}
		return index;
	}
}