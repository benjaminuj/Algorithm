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
	    	 
	    	 int a = Integer.parseInt(temp[0])- 1;
	    	 int b = Integer.parseInt(temp[1])- 1;
	    	 
	    	 graph[b][a] = 1;
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
	     int midIdx = (N+1)/2;
	     
	     for (int i =0 ; i < N; i++) {
	    	 int heavyCnt = 0;
	    	 int lightCnt = 0;
	    
	    	 for (int j = 0; j < N; j++) {
	    		 if (graph[i][j] == 1) heavyCnt ++;
	    		 if (graph[j][i] == 1) lightCnt++;
	    	 }
	    	 if (lightCnt >= midIdx || heavyCnt >= midIdx) answer++;
	     }
	     
	     System.out.println(answer);
	 }
}