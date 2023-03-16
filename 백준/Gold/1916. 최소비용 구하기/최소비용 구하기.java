import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
public static ArrayList<Edge> list[];
public static boolean[] visited;
public static int[] distance;
public static PriorityQueue<Edge> q = new PriorityQueue<Edge>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		distance = new int[N+1];
		visited = new boolean[N+1];
		for(int i=0; i<N+1; i++) {
			list[i] = new ArrayList<Edge>();
			distance[i] = Integer.MAX_VALUE;
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			list[start].add(new Edge(end, value));
		}
		st = new StringTokenizer(br.readLine());
		int here = Integer.parseInt(st.nextToken());
		int goal= Integer.parseInt(st.nextToken());
		
		distance[here] =0;
		q.add(new Edge(here, 0));
		dijkstra(here, goal);
		System.out.println(distance[goal]);
	}
	
	public static void dijkstra(int here, int goal) {	
		while(!q.isEmpty()) {
			Edge e = q.poll();
			int now = e.vertex;
			if(visited[now]) continue;
			visited[now] = true;
			for(int k=0; k<list[now].size(); k++) {
				Edge temp = list[now].get(k);
				int next = temp.vertex;
				int value = temp.value;
				if(distance[next] > value + distance[now]) {
					distance[next] = value + distance[now];
					q.add(new Edge(next, distance[next]));
				}
			}
			if(visited[goal]) return;
		}	
	}
}

class Edge implements Comparable<Edge> {
	int vertex, value;
	
	public Edge(int vertex, int value) {
		this.vertex = vertex;
		this.value = value;
	}
	
	@Override
	public int compareTo(Edge e) {
		if(this.value > e.value) return 1;
		else return -1;
	}
}
