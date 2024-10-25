import java.io.*;
import java.util.*;

public class Main { 
	static int[] parent;
	static int[] rank;
	
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int N = Integer.parseInt(br.readLine());
		 int M = Integer.parseInt(br.readLine());
		 
		 parent = new int[N];
		 rank = new int[N];
		 for (int i =0 ; i < N; i++) {
			 parent[i] = i;
			 rank[i] = 1;
		 }
		 
		 List<Integer[]> graph = new ArrayList<>(); // a, b, c
		 
	     for(int i = 0; i < M; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 int a = Integer.parseInt(temp[0])- 1;
	    	 int b = Integer.parseInt(temp[1])- 1;
	    	 int c = Integer.parseInt(temp[2]);
	    	 
	    	 if (a == b) continue;
	    	 
	    	 graph.add(new Integer[] {a, b, c});
	     }
	     
	     Collections.sort(graph, (o1, o2) -> o1[2] - o2[2]);
	     
	     int cost = 0;
	     for (int i =0; i < graph.size(); i++) {
	    	 int a = graph.get(i)[0];
	    	 int b = graph.get(i)[1];
	    	 
	    	 if (find(a) != find(b)) {
	    		 cost += graph.get(i)[2];
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
			 
			 if (rank[x] < rank[y]) {
				 parent[x] = y;
				 rank[y] += rank[x];
			 } else {
				 parent[y] = x;
				 rank[x] += rank[y];
			 }
		 }
	 }
}