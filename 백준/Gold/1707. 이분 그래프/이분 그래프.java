import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
static List<Integer>[] A;
static boolean[] visited;
static int[] check;
static boolean isEven;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		String answer = "YES";
		for(int t=0; t<K; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			A = new ArrayList[V+1];
			visited = new boolean[V+1];
			check = new int[V+1];
			isEven = true;
			for(int k=1; k<=V; k++) {
				A[k] = new ArrayList<>();
			}
			for(int j=0; j<E; j++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				A[u].add(v);
				A[v].add(u);
			}
			for(int i=1; i<=V; i++) {
				if(isEven) DFS(i);
				else break;
			}
			if(isEven) System.out.println("YES");
			else System.out.println("NO");
		}
	}
	public static void DFS(int node) {
		visited[node] =  true;
		for(int adjacency : A[node]) {
			if(!visited[adjacency]) {
				check[adjacency] = (check[node]+1)%2;
				DFS(adjacency);
			}
			else {
				if(check[adjacency] == check[node]) isEven = false;
			}
		}
	}
}
