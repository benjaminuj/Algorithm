import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 long result = Long.MAX_VALUE;
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 String[] arr = br.readLine().split(" ");
		 int n = Integer.parseInt(arr[0]);
		 int m = Integer.parseInt(arr[1]);
		 int r = Integer.parseInt(arr[2]);
		 
		 Integer[][] graph = new Integer[n][n];
		 
		 String[] itemCnt = br.readLine().split(" ");
		 
		 for (int i = 0; i < n; i++) {
			 Arrays.fill(graph[i], Integer.MAX_VALUE);
		 }
		 
	     for(int i = 0; i < r; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 int a = Integer.parseInt(temp[0]);
	    	 int b = Integer.parseInt(temp[1]);
	    	 int l = Integer.parseInt(temp[2]);
	    	 
    	 	 graph[a-1][b-1] = l;
    	 	 graph[b-1][a-1] = l;
	     }
	     
	     for (int mid = 0; mid < n; mid++) {
	    	 for (int s = 0; s < n; s++) {
	    		 if (graph[s][mid] == Integer.MAX_VALUE) continue;
	    		 
	    		 for (int e = 0; e < n; e++) {
	    			 if (graph[mid][e] == Integer.MAX_VALUE) continue;
	    			 
	    			 graph[s][e] = Math.min(graph[s][e], graph[s][mid] + graph[mid][e]);
	    		 }
	    	 }
	     }
	     
	     int answer = Integer.MIN_VALUE;
	     for (int i =0 ; i< n; i++) {
	    	 int sum = Integer.parseInt(itemCnt[i]);
	    	 
	    	 for (int j =0 ; j < n; j++) {
	    		 if (i == j) continue;
	    		 if (graph[i][j] <= m) sum += Integer.parseInt(itemCnt[j]);
	    	 }
	    	 answer = Math.max(answer, sum);
	     }
	     
	     System.out.println(answer); 
	 }
}