import java.io.*;
import java.util.*;

public class Main { 

	
	 public static void main(String args[]) throws IOException {
		 long result = Long.MAX_VALUE;
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 String[] arr = br.readLine().split(" ");
		 int V = Integer.parseInt(arr[0]);
		 int E = Integer.parseInt(arr[1]);
		 
		 Integer[][] graph = new Integer[V][V];
		 
		 for (int i = 0; i < V; i++) {
			 Arrays.fill(graph[i], Integer.MAX_VALUE);
		 }
		 
	     for(int i = 0; i < E; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 int a = Integer.parseInt(temp[0]);
	    	 int b = Integer.parseInt(temp[1]);
	    	 int c = Integer.parseInt(temp[2]);
	    	 
    	 	 graph[a-1][b-1] = c;
	     }
	     
	     for (int m = 0; m < V; m++) {
	    	 for (int s = 0; s < V; s++) {
	    		 if (graph[s][m] == Integer.MAX_VALUE) continue;
	    		 
	    		 for (int e = 0; e < V; e++) {
	    			 if (graph[m][e] == Integer.MAX_VALUE) continue;
	    			 
	    			 graph[s][e] = Math.min(graph[s][e], graph[s][m] + graph[m][e]);
	    			 if (s == e && s != m) {
	    				 result = Math.min(result, graph[s][e]);
	    			 }

	    		 }
	    	 }
	     }
	     
	     if (result == Long.MAX_VALUE) System.out.println(-1); 
	     else System.out.println(result); 
	 }
}