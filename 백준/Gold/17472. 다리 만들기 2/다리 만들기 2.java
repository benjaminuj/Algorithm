import java.io.*;
import java.util.*;

public class Main { 
	static int[] parent;
	static int[] rank;
	static String[][] map;
	static boolean[][] visited;
	static int N, M;
	static List<Integer[]> bridges;
	
	static int[][] dir = {{-1 ,0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우 
	 public static void main(String args[]) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 String[] arr = br.readLine().split(" ");
		 
		 N = Integer.parseInt(arr[0]);
		 M = Integer.parseInt(arr[1]);
		 
		 map = new String[N][M];
		 visited = new boolean[N][M];
		 bridges = new ArrayList<>();

		 
		 List<Integer[]> graph = new ArrayList<>(); // a, b, c
		 
	     for(int i = 0; i < N; i++) {
	    	 String[] temp = br.readLine().split(" ");
	    	 
	    	 map[i] = temp.clone();
	     }
	     
	     int num = 2;
	    for(int i  = 0; i < N; i++) {
	    	for (int j = 0; j < M ; j++) {
	    		if (map[i][j].equals("1") && !visited[i][j]) {
	    			getIsland(i, j, String.valueOf(num));
	    			num++;
	    		}
	    	}
	    }
	    
		 parent = new int[num];
		 rank = new int[num];
		 for (int i = 2; i < num; i++) {
			 parent[i] = i;
			 rank[i] = 1;
		 }

	     
	    getBridge();
	     Collections.sort(bridges, (o1, o2) -> o1[2] - o2[2]);
	     
	     int cost = 0;
	     for (int i =0; i < bridges.size(); i++) {
	    	 int a = bridges.get(i)[0];
	    	 int b = bridges.get(i)[1];
	    	 
	    	 if (find(a) != find(b)) {
	    		 cost += bridges.get(i)[2];
	    		 union(a, b);
	    	 }
	     }

	     boolean cantConnect = false;
	     int root1 = find(parent[2]);
	     for (int i = 3; i < parent.length; i++) {
	    	 if (find(parent[i]) != root1) {
	    		 cantConnect = true;
	    		 break;
	    	 }
	     }
	     
	     if (cost == 0 || cantConnect) System.out.println(-1);
	     else System.out.println(cost);
	 }
	 
	 private static void getIsland(int i, int j, String num) {
		 Queue<Integer[]> q = new ArrayDeque<>();
		 q.offer(new Integer[] {i ,j});
		 visited[i][j] = true;
		 map[i][j] = num;
		 
 		 while (!q.isEmpty()) {
			 Integer[] cur = q.poll();
		 
			 for (int[] d : dir) {
				 int nr = cur[0] + d[0];
				 int nc = cur[1] + d[1];
				 
				 if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]) {
					 if (map[nr][nc].equals("0")) continue;
				 
					 q.offer(new Integer[] {nr, nc});
					 visited[nr][nc] = true;
					 map[nr][nc] = num;
				 }
			 }
 		 }
	 }
	 
	 public static void getBridge() { 
		 for (int r =0 ; r < N; r++) {
			 for (int c = 0; c < M; c++) {
				 if (!map[r][c].equals("0")) {
					 for (int[] d : dir) {
						 Integer[] info = getPathLen(r, c, d);
						 
						 if (info[2] == -1 || info[2] < 2) continue; // 불가능한 경우 
						 
						 bridges.add(info);
					 }
				 }
			 }
		 }
		 
	 }
	 
	 private static Integer[] getPathLen(int r, int c, int[] d) {
		 Integer[] answer = new Integer[3]; // 섬 번호, 섬 번호, 다리 길이 
		 answer[0] = Integer.parseInt(map[r][c]);
		 answer[1] = 0;
		 answer[2] = 0;
	 
		 int cost = 0;
		 String islandNum = map[r][c];
		 int nr = r;
		 int nc = c;
		 
		 while(true) {
			 nr += d[0];
			 nc += d[1];
			 
			 if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
				 if (map[nr][nc].equals("0")) {
					 cost++; // 다리 건설 
				 } else if (map[nr][nc].equals(islandNum)) { // 내 섬이라 불가능 
					 answer[2] = -1;
					 break; 
				 } else if (!map[nr][nc].equals(islandNum)) { // 다른 섬 만남 
					 answer[1] = Integer.parseInt(map[nr][nc]);
					 answer[2] = cost;
					 break;
				 }
			 } else { // 범위 초과할동안 다른 섬 못만남 
				 answer[2] = -1;
				 break;
			 }
		 }
		 return answer;
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