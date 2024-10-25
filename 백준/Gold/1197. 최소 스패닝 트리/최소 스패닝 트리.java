import java.io.*;
import java.util.*;

public class Main { 
	static int[] parent;
	
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 String[] arr = br.readLine().split(" ");
		 
		 int V = Integer.parseInt(arr[0]);
		 int E = Integer.parseInt(arr[1]);
		 
		 parent = new int[V];
		 for (int i =0 ; i < V; i++) {
			 parent[i] = i;
		 }
		 
		 int[][] graph = new int[E][3];
		 
	     for(int i = 0; i < E; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 int a = Integer.parseInt(temp[0])- 1;
	    	 int b = Integer.parseInt(temp[1])- 1;
	    	 int c = Integer.parseInt(temp[2]);
	    	 
	    	 graph[i][0] = a;
	    	 graph[i][1] = b;
	    	 graph[i][2] = c;
	     }
	     
	     Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);
	     
	     int cost = 0;
	     for (int i =0 ; i < E; i++) {
	    	 int a = graph[i][0];
	    	 int b = graph[i][1];
	    	 
	    	 if (find(a) != find(b)) {
	    		 cost += graph[i][2];
	    		 union(a, b);
	    	 }
	     }

	     System.out.println(cost);
	 }
	 
	 public static int find(int x) {
		 if (parent[x] == x) {
			 return x;
		 }
		 
		 int y = find(parent[x]);
		 parent[x] = y;
		 return y;
	 }
	 
	 public static void union(int x, int y) {
		 x = find(x);
		 y = find(y);
		 
		 if (x != y) {
			 parent[y] = x;
		 }
	 }
}