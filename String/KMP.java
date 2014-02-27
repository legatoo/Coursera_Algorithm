import java.util.LinkedList;
import java.util.Queue;

public class KMP {
	private static int R = 256;
	private int[][] dfa;
	private int N, M;
	public Queue<String> matches;

	public KMP(String pattern) {
		M = pattern.length();
		dfa = new int[R][M];
	}

	public void buildDFA(String pattern) {
		dfa[pattern.charAt(0)][0] = 1; // start the state machine
		int x = 0;

		for (int j = 1; j < M; j++) {
			// copy mismatch from state X
			for (int r = 0; r < R; r++)
				dfa[r][j] = dfa[r][x];

			// set the match value to j+1
			dfa[pattern.charAt(j)][j] = j + 1;

			// update state x
			x = dfa[pattern.charAt(j)][x];
		}
	}

	public Iterable<String> searchPattern(String txt, String pattern) {
		buildDFA(pattern);
		matches = new LinkedList<>();
		
		N = txt.length();
		int i, j;

		for (i = 0, j = 0; i <= N; i++) {
			if (j == M) {
				matches.add(txt.substring(i-M, i));
				j = 0;
				if( i == N)
					break;
			}
			
			j = dfa[txt.charAt(i)][j];
		}

		return matches;
	}
	
	public static void main(String[] args){
		String s = "ABACADABRACADABCADAB";
		String p = "ADAB";
		
		KMP kmp = new KMP(p);
		for(String m: kmp.searchPattern(s, p))
			System.out.println(m);
	}
}