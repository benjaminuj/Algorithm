import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.PriorityQueue;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static ArrayList<Node> list[];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int strat = Integer.parseInt(br.readLine());
		list = new ArrayList[V+1];
		for(int i=0; i<=V; i++) {
			list[i] = new ArrayList<Node>();
		}
		Graph g = new Graph(V+1);
		
		for(int i=0; i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Node(w,v));
		}
		g.dijksra(strat, list);
	}
}

class Graph {
	private int n;
	public Graph(int n) {
		this.n = n;
	}
	public void dijksra(int v, ArrayList<Node>[] list) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		int[] distance = new int[n];
		boolean[] visited = new boolean[n];
		
		for(int i=1; i<n; i++) {
			distance[i] = Integer.MAX_VALUE;		
		}
		queue.add(new Node(0,v));
		distance[v] = 0;
		while(!queue.isEmpty()) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			Node node = queue.poll();
			min = node.weight;
			minIndex = node.index;
			if(visited[minIndex]) continue;
		
			visited[minIndex] = true;
			for(int i=0; i<list[minIndex].size(); i++) {
				Node tmp = list[minIndex].get(i);
				int next = tmp.index;
				int value = tmp.weight;
				if(distance[next] > distance[minIndex] + value) {
					distance[next] = distance[minIndex] + value;
					queue.add(new Node(distance[next], next));
				}
			}
		}
		
		for(int i=1; i<n; i++) {
			if(distance[i] == Integer.MAX_VALUE) System.out.println("INF");
			else System.out.println(distance[i]);
		}
	}
}
class Node implements Comparable<Node> {
	int weight;
	int index;
	
	public Node(int weight, int index) {
		this.weight = weight;
		this.index = index;
	}
	
	@Override
	public int compareTo(Node node) {
		if(this.weight > node.weight) return 1;
		else return -1;
	}
}


