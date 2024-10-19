import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String[] arr = br.readLine().split(" ");
		 int N = Integer.parseInt(arr[0]);
		 int M = Integer.parseInt(arr[1]);
		 
		 Map<Integer, List<Integer>> map = new HashMap<>();
		 Queue<Integer> pq = new PriorityQueue<>();
	     int[] indegree = new int[N+1];
	     
	     for(int i = 0; i < M; i++) {
	    	 String[] input = br.readLine().split(" ");
	    	 
	    	 int A = Integer.parseInt(input[0]);
	    	 int B = Integer.parseInt(input[1]);
	    	 
	    	 map.putIfAbsent(A, new ArrayList<>());
	    	 map.get(A).add(B);
	    	 
	    	 indegree[B]++;
	     }
	     
	     for (int i = 1; i <= N; i++) {
	    	 if (indegree[i] == 0) pq.offer(i);
	     }
	     
	     StringBuilder sb = new StringBuilder();
	     while(!pq.isEmpty()) {
	    	 int cur = pq.poll();
	    	 sb.append(cur + " ");
	    	 
	    	 if (!map.containsKey(cur)) continue;
	    	 
	    	 for (int next: map.get(cur)) {
	    		 indegree[next]--;
	    		 
	    		 if (indegree[next] == 0) {
	    			 pq.offer(next);
	    		 }
	    	 }
	     }
	     
	     System.out.println(sb.toString());
	 }
}