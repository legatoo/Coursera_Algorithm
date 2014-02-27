import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//time proportional to ~N/M
public class BoyerMoore {
	private static int R = 256;
	private int[] right;
	private int N, M;

	/**
	 * read the pattern in(left to right) and update right store the rightest
	 * position in position
	 */
	public BoyerMoore(String pattern) {
		right = new int[R];
		Arrays.fill(right, -1);
		M = pattern.length();

		for (int j = 0; j < M; j++)
			right[pattern.charAt(j)] = j;
	}

	public Iterable<String> searchPattern(String txt, String pattern) {
		N = txt.length();
		int skip = 0;
		Queue<String> matches = new LinkedList<>();

		for (int i = 0; i <= N - M; i += skip) {
			skip = 0;

			// scan pattern from right to left
			for (int j = M - 1; j >= 0; j--) {
				// if mismatch happen
				if (pattern.charAt(j) != txt.charAt(i + j)) {
					/**
					 * if mismatch letter is not in pattern, right[] will return
					 * -1, so the skip will equal to j+1, this means the text
					 * pointer will change to on more character beyond the
					 * current mismatch position; when mismatch is in pattern:
					 * 1. if the right[] > j, means the rightest letter is at
					 * right of current j, but we shouldnt align with this
					 * letter,cuz this involves backup, so, here just increase
					 * one 2, if right[] < j, means the rightest letter is at
					 * left of j, move the text pointer to this pos
					 */
					skip = Math.max(1, j - right[txt.charAt(i + j)]);
					break;
				}
			}

			if (skip == 0) {
				matches.add(txt.substring(i, i + M));
				i += M;
			}
		}

		return matches;
	}

	public static void main(String[] args) {
		String s = "ABACADABRACADABCADAB";
		String p = "ADAB";

		BoyerMoore bm = new BoyerMoore(p);
		for (String m : bm.searchPattern(s, p))
			System.out.println(m);
	}
}