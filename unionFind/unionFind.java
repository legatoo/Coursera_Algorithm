//weighted union find algorithm with path compression
public class unionFind {
	int[] sz;

	public void uniionfindwithweight(int[] array, int i, int j) {
		sz = new int[array.length];
		union(array, i, j);

	}

	public boolean find(int[] array, int i, int j) {
		if (i == j) {
			System.out.println("self reflexive.");
			return true;
		}
		if (root(array, i) == root(array, j)) {
			System.out.println("they are connected." + String.valueOf(i)
					+ " --> " + String.valueOf(j));
			return true;
		}
		return false;
	}

	public int root(int[] array, int i) {
		while (i != array[i]) {
			array[i] = array[array[i]]; // path compression
			i = array[i];
		}
		return i;
	}

	public void union(int[] array, int i, int j) {

		System.out.println("union operation done." + String.valueOf(i)
				+ " --> " + String.valueOf(j));
		if (sz[i] < sz[j]) {
			array[root(array, i)] = root(array, j);
			sz[j] += sz[i];
		} else {
			array[root(array, j)] = root(array, i);
			sz[i] += sz[j];
		}
	}
}