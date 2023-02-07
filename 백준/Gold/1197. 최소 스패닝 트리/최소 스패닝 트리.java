import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
static int[] parent;
static int[][] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		parent = new int[V+1];
		for(int i=1; i<=V;i ++) {
			parent[i] = i;
		}
		graph = new int[E][3];
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(graph, (o1,o2)-> o1[2] - o2[2]);
		System.out.println(kruskal(graph));
	}
	public static int find(int x) {
		if(parent[x] == x) return x;
		else return find(parent[x]);
	}
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return;
		else parent[y]= x;
	}
	public static int kruskal(int[][] graph) {
		int cost = 0;
		for(int i=0; i<graph.length; i++) {
			if(find(graph[i][0]) != find(graph[i][1])) {
				cost += graph[i][2];
				union(graph[i][0], graph[i][1]);
			}
		}
		return cost;
	}

}
