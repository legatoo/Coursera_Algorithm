public class week2{
	public static void main(String[] args){
//		LStack<String> lstack = new LStack<>();
//		lstack.push("a");
//		lstack.push("b");
//		lstack.push("c");
//		lstack.push("d");
//		
//		for(String s : lstack)
//			System.out.println(s);
		
//		AStack<String> astack = new AStack();
//		astack.push("a");
//		astack.push("b");
//		astack.push("c");
//		astack.push("d");
//		astack.push("e");
//		astack.push("f");
//		astack.push("g");
//		astack.push("h");
//		
//		for (String s: astack)
//			System.out.println(s);
		
//		LQueue<String> lqueue = new LQueue();
//		lqueue.enqueue("a");
//		lqueue.enqueue("b");
//		lqueue.enqueue("c");
//		lqueue.enqueue("d");
//		
//		for (String s: lqueue)
//			System.out.println(s);
		
		AQueue<String> aqueue = new AQueue<>();
		aqueue.enqueue("a");
		aqueue.enqueue("b");
		aqueue.enqueue("c");
		aqueue.enqueue("d");
		aqueue.enqueue("e");
		aqueue.enqueue("f");
		aqueue.enqueue("g");
		aqueue.enqueue("h");
		
		
		for (String s: aqueue)
			System.out.println(s);

	}
}