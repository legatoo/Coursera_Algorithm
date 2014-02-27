import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Bipartite {

	private boolean[] marked;
	private boolean[] color;
	private int[] edgeTo;
	private boolean bipartiieable;

	public Bipartite(Graph g) {
		marked = new boolean[g.size()];
		color = new boolean[g.size()];
		edgeTo = new int[g.size()];
		bipartiieable = true;
		for (int i = 0; i < g.size(); i++)
			edgeTo[i] = i;
	}

	public void bipartiteTest(Graph g) {
		if( dfs_nr(g, 0)){
			int i = 0;
			System.out.println("Yes, thi is a bipartite graph. \nGroup one:");
			while(i<g.size()){
				if(color[i])
					System.out.print(String.valueOf(i) + " ");
				i++;
			}
			i=0;

			System.out.println("\nGroup two:");
			while(i<g.size()){
				if(!color[i])
					System.out.print(String.valueOf(i) + " ");
				i++;
			}
		}
		else
			System.out.println("Not a bipartite graph.");
	}

	private void dfs(Graph g, int v) {
		marked[v] = true;

		for (Object n : g.adj(v)) {
			if (!marked[(int) n]) {
				color[(int) n] = !color[v];
				dfs(g, (int) n);
			} else if(!(color[v] ^ color[(int) n])) {
				bipartiieable =  false;
			}
		}
	}
	
	private boolean dfs_nr(Graph g, int v){
		Deque<Integer> stack = new ArrayDeque<Integer>();
		stack.add(v);
		
		while(!stack.isEmpty()){
			
			int c = stack.pop();
			marked[c] = true;
			
			for(Object n: g.adj(c)){
				if(!marked[(int)n]){
					color[(int)n] = !color[(int)c];
					stack.push((int)n);
				}
				else if(!(color[(int)n]^color[c]))
					return false;
			}
		}
		
		return true;
	}
	
}