import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 Queue<Integer> minHeap = new PriorityQueue<>();
		 Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		 
		 int N = Integer.parseInt(br.readLine());
		 StringBuilder sb = new StringBuilder();
		 
	     for(int i = 0; i < N; i++) {
	    	 int num = Integer.parseInt(br.readLine());
	    	 
	    	 if (maxHeap.isEmpty() || maxHeap.peek() > num) {
	    		 maxHeap.offer(num);
	    	 } else {
	    		 minHeap.offer(num);
	    	 }
	    	 
	    	 if (maxHeap.size() < minHeap.size()) {
	    		 maxHeap.offer(minHeap.poll());
	    	 } else if (minHeap.size() + 1 < maxHeap.size()) {
	    		 minHeap.offer(maxHeap.poll());
	    	 }
	    	 
	    	 sb.append(maxHeap.peek() + "\n");
	     }
	     
	     System.out.println(sb.toString());
	 }
	 
	 
}