public class SubstringSearch {
	public int bruteForce(String s, String p) {
		assert (p.length() <= s.length());
		
		int j, M = p.length();
		int i, N = s.length();

		for ( i = 0, j = 0; i < N && j < M; i++) {
			if (s.charAt(i) == p.charAt(j))
				j++;
			else {
				i -= j;
				j = 0;
			}
		}
		
		if(j == M)
			return  i-M;
		else
			return N;
	}
	
	
	
	
	public static void main(String[] args){
		String s = "ABACADABRAC";
		String p = "ADAB";
		
		SubstringSearch ss = new SubstringSearch();
		int pos = ss.bruteForce(s, p);
		System.out.println(s.substring(pos, pos + p.length()));
	}
}