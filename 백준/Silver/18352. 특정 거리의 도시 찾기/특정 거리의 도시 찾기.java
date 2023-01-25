import java.util.*;
import java.io.*;

public class Main {
static int[] visited;
static ArrayList<Integer>[] city;
static List<Integer> answer;
static int K;
	public static void main(String[] args) throws IOException {
		answer = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		boolean kExist = false;
		city = new ArrayList[N+1];
		visited = new int[N+1];
		for(int i=0 ;i<N+1; i++) {
			visited[i] = -1;
		}
		for(int i=1; i<N+1; i++) {
			city[i] = new ArrayList<>();
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			city[A].add(B);		
		}
		visited[X] =0;
		BFS(X);
		for(int i=1; i<N+1; i++) {
			if(visited[i] ==K) {
				System.out.println(i);
				kExist = true;
			}
		}
		if(!kExist) System.out.println("-1");
	}
	private static void BFS(int node) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(node);
		while(!queue.isEmpty()) {
			int now_node = queue.poll();
			for(int i : city[now_node]) {
				if(visited[i] == -1) {
					queue.add(i);
					visited[i] = visited[now_node]+1;
				}
			}
			if(visited[now_node]+1 == K+1) {
				break;
			}
		}
	}
}
