import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
static ArrayList<Integer>[] family;
static boolean[] visited;
static int answer=-1;
static int parent,child, person1, person2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		person1 = Integer.parseInt(st.nextToken());
		person2 = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		family = new ArrayList[n+1];
		visited = new boolean[n+1];
		for(int i=1; i<n+1; i++) {
			family[i]= new ArrayList<Integer>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			parent = Integer.parseInt(st.nextToken());
			child = Integer.parseInt(st.nextToken());
			family[parent].add(child);
			family[child].add(parent);
		}
		
		DFS(person1,0);
		System.out.println(answer);
	}
	public static void DFS(int node, int chon) {
		if(visited[node]) return;
		visited[node] = true;
		for(int person : family[node]) {
			if(!visited[person]) {
				if(person == person2) {
					answer = chon+1;
					return;
				}
				DFS(person, chon+1);
			}
		}
	}
}
