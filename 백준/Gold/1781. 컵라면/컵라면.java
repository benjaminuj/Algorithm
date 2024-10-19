import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 int N = Integer.parseInt(br.readLine());
		 
		 TreeMap<Integer, List<Integer>> map = new TreeMap<>();
	     Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	     
	     for(int i = 0; i < N; i++) {
	    	 String[] input = br.readLine().split(" ");
	    	 
	    	 int deadLine = Integer.parseInt(input[0]);
	    	 int cupCnt = Integer.parseInt(input[1]);
	    	 map.putIfAbsent(deadLine, new ArrayList<>());
	    	 map.get(deadLine).add(cupCnt);
	     }
	     
	     int maxTime = map.lastKey();
	     long sum = 0L;
	     for (int t = maxTime; t > 0; t--) {
	    	 if (map.containsKey(t)) {
	    		 for (int cnt :map.get(t)) {
	    			 pq.offer(cnt);
	    		 }
	    	 }
	    	 
	    	 if (pq.size() == 0) continue;
	    	 
	    	 sum += pq.poll();
	     }
	     System.out.println(sum);
	 }
}