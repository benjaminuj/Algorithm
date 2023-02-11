import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;


class Edge implements Comparable<Edge> {
	int w;
	int cost;
	
	Edge(int w, int cost) {
		this.w = w;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
}

public class Main {
	static List<Edge>[] graph; 
	
	public static void prim(int start, int n) {
		boolean[] visited = new boolean[n+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start,0));
		int total = 0;
		int max = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int v = edge.w;
			int cost = edge.cost;
			if(visited[v]) continue;
			visited[v] = true;
			total += cost;
			max = Math.max(cost, max);
			for(Edge e : graph[v]) {
				if(!visited[e.w]) {
					pq.add(e);
				}
			}
		}
		System.out.println(total-max);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			graph[A].add(new Edge(B,C));
			graph[B].add(new Edge(A,C));
		}
		prim(1,N);

	}
}



