import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 long result = Long.MAX_VALUE;
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 String[] arr = br.readLine().split(" ");
		 int n = Integer.parseInt(arr[0]);
		 int k = Integer.parseInt(arr[1]);
		 
		 Integer[][] graph = new Integer[n][n];
		 
		 for (int i = 0; i < n; i++) {
			 Arrays.fill(graph[i], Integer.MAX_VALUE);
		 }
		 
	     for(int i = 0; i < k; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 int a = Integer.parseInt(temp[0]);
	    	 int b = Integer.parseInt(temp[1]);
	    	 
    	 	 graph[a-1][b-1] = 1;
	     }
	     
	     for (int mid = 0; mid < n; mid++) {
	    	 for (int s = 0; s < n; s++) {
	    		 if (graph[s][mid] == Integer.MAX_VALUE) continue;
	    		 
	    		 for (int e = 0; e < n; e++) {
	    			 if (graph[mid][e] == Integer.MAX_VALUE) continue;
	    			 
	    			 graph[s][e] = 1;
	    		 }
	    	 }
	     }
	     
	     int s = Integer.parseInt(br.readLine());
	     for (int i = 0; i < s; i++) {
    	 	String[] temp = br.readLine().split(" ");
	    	 
	    	 int a = Integer.parseInt(temp[0]);
	    	 int b = Integer.parseInt(temp[1]);
	    	 a--;
	    	 b--;
	    	 
	    	 boolean know = false;
	    	 for (int mid = 0; mid < n; mid++) {
	    		 if (graph[a][b] == 1) {
	    			 System.out.println(-1);
	    			 know = true;
	    			 break;
	    		 }
	    		 if (graph[b][a] == 1) {
	    			 System.out.println(1);
	    			 know = true;
	    			 break;
	    		 }
	    	 }
	    	 if (!know) System.out.println(0);
	     }
	 }
}