import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int N = Integer.parseInt(br.readLine());
		 Integer[][] graph = new Integer[N][N];
		 int[][] answer = new int[N][N];
		 
	     for(int i = 0; i < N; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 for (int j = 0 ; j < N; j++) {
	    		 graph[i][j] = Integer.parseInt(temp[j]);
	    	 }
	     }
	     
	     for (int m = 0; m < N; m++) {
	    	 for (int s = 0; s < N; s++) {
	    		 if (graph[s][m] != 1) continue;
	    		 
	    		 for (int e = 0; e < N; e++) {
	    			 if (graph[m][e] != 1) continue;
	    			 
	    			 graph[s][e] = 1;
	    		 }
	    	 }
	     }
	     
	     for (int i = 0; i < N; i++) {
	    	 for (int j = 0; j < N; j++) {
	    		 System.out.print(graph[i][j] + " ");
	    	 }
	    	 System.out.println();
	     }
	 }
}