import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
public static ArrayList<Integer>[] graph;
public static int[] indegree;
public static int N;
public static Queue<Integer> q = new LinkedList<>();
public static Queue<Integer> result = new LinkedList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M  = sc.nextInt();
		indegree = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for(int i=0; i<M; i++) {
			int front = sc.nextInt();
			int back = sc.nextInt();
			graph[front].add(back);
			indegree[back]++;
		}
		topologicalSort(indegree, graph);
		while(!result.isEmpty()) System.out.print(result.poll() + " ");
	}
	public static void topologicalSort(int[] indegree, ArrayList<Integer>[] graph) {
		for(int i=1; i<N+1; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		while(!q.isEmpty()) {
			int node = q.poll();
			result.offer(node);
			for(int n : graph[node]) {
				indegree[n]--;
				if(indegree[n] ==0 ) q.offer(n);
			}
		}
	}
}
