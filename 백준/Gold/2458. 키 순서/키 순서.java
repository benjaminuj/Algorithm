import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 String[] arr = br.readLine().split(" ");
		 int N = Integer.parseInt(arr[0]);
		 int M = Integer.parseInt(arr[1]);
		 
		 Integer[][] graph = new Integer[N][N];
		 
		 for (int i = 0; i < N; i++) {
			 Arrays.fill(graph[i], Integer.MAX_VALUE);
		 }
		 
	     for(int i = 0; i < M; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 int a = Integer.parseInt(temp[0]);
	    	 int b = Integer.parseInt(temp[1]);
	    	 
    	 	 graph[a-1][b-1] = 1;
	     }
	     
	     for (int mid = 0; mid < N; mid++) {
	    	 for (int s = 0; s < N; s++) {
	    		 if (graph[s][mid] == Integer.MAX_VALUE) continue;
	    		 
	    		 for (int e = 0; e < N; e++) {
	    			 if (graph[mid][e] == Integer.MAX_VALUE) continue;
	    			 
	    			 graph[s][e] = 1;
	    		 }
	    	 }
	     }
	     
	     int answer = 0;
	     for (int i = 0; i < N; i++) {
	    	 int result = 0;
	    	 for (int j = 0; j < N; j++) {
	    		 if (i == j) continue;
	    		 
	    		 if (graph[i][j] == 1 || graph[j][i] == 1) result++;
	    	 }
	    	 
	    	 if (result == N-1) answer++;
	     }
	     
	     System.out.println(answer);
	 }
}