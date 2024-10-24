import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int N = Integer.parseInt(br.readLine());
		 int M = Integer.parseInt(br.readLine());
		 
		 Long[][] graph = new Long[N][N];
		 
		 for (int i = 0; i < N; i++) {
			 Arrays.fill(graph[i], Long.MAX_VALUE);
		 }
		 
	     for(int i = 0; i < M; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 int a = Integer.parseInt(temp[0])- 1;
	    	 int b = Integer.parseInt(temp[1])- 1;
	    	 int c = Integer.parseInt(temp[2]);
	    	 
    	 	 graph[a][b] = Math.min(graph[a][b], Long.valueOf(c)); // int -> Long
	     }
	     
	     for (int mid = 0; mid < N; mid++) {
	    	 for (int s = 0; s < N; s++) {
	    		 if (graph[s][mid] == Long.MAX_VALUE) continue;
	    		 
	    		 for (int e = 0; e < N; e++) {
	    			 if (graph[mid][e] == Long.MAX_VALUE) continue;
	    			 
	    			 graph[s][e] = Math.min(graph[s][e], graph[s][mid] + graph[mid][e]);
	    		 }
	    	 }
	     }
	     
	     for (int i = 0; i < N; i++) {
	    	 for (int j = 0; j < N; j++) {
	    		 if (i == j || graph[i][j] == Long.MAX_VALUE) System.out.print(0 + " ");
	    		 else System.out.print(graph[i][j] + " ");	 
	    	 }
	    	 if (i+1 != N) System.out.println();
	     }
	 }
}