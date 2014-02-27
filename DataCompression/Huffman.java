import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Huffman{
	private int R;
	private Map<Character, String> code = new HashMap<>();
	private int[] count;
	private Node root;
	
	public Huffman(String source, int radix){
		R = radix;
		count = new int[R];
		
		for(int i = 0; i < source.length(); i++){
			count[source.charAt(i)]++;
		}
		
		//Arrays.sort(count);
	}
	
	public Node huffmanTire(){
		PriQueue pq = new PriQueue();
		
		//start with every char corresponding to one node
		for(int i = 0; i < count.length; i++ )
			if(count[i] > 0)
				pq.insert(new Node((char)i, count[i], null, null));
		
		while(pq.size() > 1){
			//fetch two node with minimum frequency
			Node x = pq.deleteMin();
			Node y = pq.deleteMin();
			
			pq.insert(new Node('\0', x.frequency()+y.frequency(), x, y));
			
		}
		
		return pq.deleteMin();
	}
	
	public void huffmanCode(){
		root = huffmanTire();
		huffmanCode(root.left, "0");
		huffmanCode(root.right, "1");
		
	}
	
	private void huffmanCode(Node x, String prefix){
		if(x == null)
			return ;
		if(x.isLeaf()){
			code.put(x.ch, prefix);
		}
	}
}