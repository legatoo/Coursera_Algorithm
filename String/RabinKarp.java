import java.util.LinkedList;
import java.util.Queue;

public class RabinKarp {
	//can search for multiple patterns
	//just recompute for each hashing value
	private static int R = 256;
	private long patternHash;
	private int N, M;
	private static int Q = 997;

	// recompute RM
	private int RM;

	public RabinKarp(String pattern) {
		M = pattern.length();
		patternHash = hashing(pattern);
		RM = 1;
		for (int i = 1; i <= M - 1; i++) {
			RM = (R * RM) % Q;
		}
	}

	// modular hashing
	private long hashing(String pattern) {
		long hash = 0;
		for (int j = 0; j < M; j++) {
			hash = (R * hash + pattern.charAt(j)) % Q;
		}
		return hash;
	}

	// search
	public Iterable<String> searchPattern(String txt) {
		N = txt.length();
		Queue<String> matches = new LinkedList<>();

		long txtHash = hashing(txt);
		if (patternHash == txtHash)
			matches.add(txt.substring(0, M));
		for (int i = M; i < N; i++) {
			txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;
			if (patternHash == txtHash)
				matches.add(txt.substring(i - M + 1, i+1));
		}

		return matches;

	}

	public static void main(String[] args) {
		String s = "ABACADABRACADABCADAB";
		String p = "ADAB";

		RabinKarp rk = new RabinKarp(p);
		for (String m : rk.searchPattern(s))
			System.out.println(m);
	}
}