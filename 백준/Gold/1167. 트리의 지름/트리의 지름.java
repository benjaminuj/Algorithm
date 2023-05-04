import java.io.*;
import java.util.*;

class Main {
	static List<graph>[] list;
	static boolean[] visited, finished;
	static int v;
	static boolean cycle;
	static long[] sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		v = Integer.parseInt(br.readLine());
		list = new ArrayList[v+1];
		visited = new boolean[v+1];
		finished = new boolean[v+1];
		sum = new long[v+1];
		long max = 0;
		int maxIndex = 0;
		for(int i=1; i<=v; i++) {
			list[i] = new ArrayList<>();
			finished[i] = false;
		}
		StringTokenizer st;
		for(int i=1; i<=v; i++) {
			st = new StringTokenizer(br.readLine());
			int here = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			while(next != -1) {
				list[here].add(new graph(next, Integer.parseInt(st.nextToken())));
				next = Integer.parseInt(st.nextToken());
			}
		}
		dfs((int)Math.round(Math.random()+(v-1)));
		for(int i=1; i<v+1; i++) {
			if(max < sum[i]) {
				max = sum[i];
				maxIndex = i;
			}
		}
		visited = new boolean[v+1];
		sum = new long[v+1];
		dfs(maxIndex);
		long answer = 0;
		for(int i=1; i<v+1; i++) {
			if(answer < sum[i]) {
				answer = sum[i];
			}
		}
		System.out.println(answer);
	} 
	
	public static void dfs(int start) {
		visited[start] = true;
		
		for(graph g : list[start]) {
			if(!visited[g.v]) {
				sum[g.v] = sum[start] + g.distance;
				dfs(g.v);
			}
		}
	}
}
class graph {
	int v;
	int distance;
	
	public graph(int v, int distance) {
		this.v = v;
		this.distance = distance;
	}
}
