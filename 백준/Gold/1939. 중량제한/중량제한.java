import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
static int[] parent;
static int island1;
static int island2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i=1; i<= N; i++) {
			parent[i] = -1;
		}
		int[][] graph = new int[M][3];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		island1 = Integer.parseInt(st.nextToken());
		island2 = Integer.parseInt(st.nextToken());
		Arrays.sort(graph, (o1,o2) -> o2[2] - o1[2]);
		System.out.println(kruskal(graph, parent));
	}
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x==y) return;
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
	public static int find(int x) {
		if(parent[x] <0) return x;
		else {
			int y = find(parent[x]);
			parent[x] = y;
			return y;
		}
	}
	public static int kruskal(int[][] graph, int[] parent) {
		int max =graph[0][2];
		boolean is = false;
		for(int i=0; i<graph.length; i++) {
			if(find(graph[i][0]) != find(graph[i][1])) {
				if((graph[i][0] == island1 && graph[i][1] == island2) || (graph[i][0] == island2 && graph[i][1] == island1)) {
					max = graph[i][2];
					is = true;
				}
				if(!is) {
					if(max > graph[i][2]) max = graph[i][2];
				}
				union(graph[i][0], graph[i][1]);
			}
			if(find(island1) == find(island2)) {
				break;
			}
		}
		return max;
	}
}
