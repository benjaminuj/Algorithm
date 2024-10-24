import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int N = Integer.parseInt(br.readLine());
		 int M = Integer.parseInt(br.readLine());
		 
		 Long[][] graph = new Long[N][N];
		 List<Integer>[][] path = new ArrayList[N][N];
		 
		 for (int i = 0; i < N; i++) {
			 Arrays.fill(graph[i], Long.MAX_VALUE);
		 }
		 for (int i =0 ; i < N; i++) {
			 for (int j = 0; j < N; j++) {
				 path[i][j] = new ArrayList<>();
			 }
		 }
		 
	     for(int i = 0; i < M; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 int a = Integer.parseInt(temp[0])- 1;
	    	 int b = Integer.parseInt(temp[1])- 1;
	    	 int c = Integer.parseInt(temp[2]);
	    	 
	    	 if (graph[a][b] > c) {
	    		 graph[a][b] = Long.valueOf(c); // int -> Long
	    		 
	    		 path[a][b].clear();
	    		 path[a][b].add(a);
	    		 path[a][b].add(b);
	    	 }
    	 	  
	     }
	     
	     for (int mid = 0; mid < N; mid++) {
	    	 for (int s = 0; s < N; s++) {
	    		 if (graph[s][mid] == Long.MAX_VALUE) continue;
	    		 
	    		 for (int e = 0; e < N; e++) {
	    			 if (graph[mid][e] == Long.MAX_VALUE) continue;
	    			 
	    			 long newCost = graph[s][mid] + graph[mid][e];
	    			 if (graph[s][e] > newCost) {
	    				 graph[s][e] = newCost;
	    				 
	    				 path[s][e].clear();
	    				 for (int node : path[s][mid]) {
	    					 path[s][e].add(node);
	    				 }
	    				 for (int node : path[mid][e]) {
	    					 if (node == mid) continue;
	    					 path[s][e].add(node);
	    				 }
	    				 
	    			 }
	    		
	    		 }
	    	 }
	     }
	     
	     for (int i = 0; i < N; i++) {
	    	 for (int j = 0; j < N; j++) {
	    		 if (i == j || graph[i][j] == Long.MAX_VALUE) System.out.print(0 + " ");
	    		 else System.out.print(graph[i][j] + " ");	 
	    	 }
	    	 System.out.println();
	     }
	     
	     for (int i = 0 ; i < N*N; i++) {
	    	 int r = i/N;
	    	 int c = i%N;
	    	 
	    	 if (r == c || graph[r][c] == Long.MAX_VALUE) System.out.print(0);
	    	 else {
	    		 System.out.print(path[r][c].size() + " ");
	    		 
	    		 for (int node : path[r][c]) {
	    			 System.out.print((node+1) + " ");
	    		 }
	    	 }
	    	 System.out.println();
	     }
	 }
}