import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
static int N;
static boolean visited[][];
static int cave[][];
static int distance[][];
static int[] dx = {0,1,0,-1};
static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int cnt = 1;
		while(N != 0) {
			cave = new int[N][N];
			visited = new boolean[N][N];
			distance = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
					distance[i][j] =Integer.MAX_VALUE;
				}
			}
			
			distance[0][0] = cave[0][0];
			dijkstra(0,0);
			System.out.println("Problem " + cnt++ +": "+ distance[N-1][N-1]);
			N = Integer.parseInt(br.readLine());
		}
		

	}
	public static void dijkstra(int x, int y) {
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.add(new Edge(x,y,cave[x][y]));
		distance[x][y] = cave[x][y];
		
		while(!q.isEmpty()) {
			Edge here = q.poll();
			int minWeight = here.w;
			int minX = here.x;
			int minY = here.y;
			if(visited[minX][minY]) continue;
			visited[minX][minY] = true;
			for(int i=0; i<4; i++) {
				if(minX+dx[i] < N && minX+dx[i] >= 0 && minY+dy[i] < N && minY+dy[i] >=0) {
					if(distance[minX+dx[i]][minY+dy[i]]> cave[minX+dx[i]][minY+dy[i]] + distance[minX][minY]) {
						distance[minX+dx[i]][minY+dy[i]] = cave[minX+dx[i]][minY+dy[i]] + distance[minX][minY];
						q.add(new Edge(minX+dx[i], minY+dy[i], distance[minX+dx[i]][minY+dy[i]]));
					}
				}
			}
		}		
	}
}
class Edge implements Comparable<Edge> {
	int x;
	int y;
	int w;
	
	public Edge(int x,int y, int w) {
		this.x= x;
		this.y = y;
		this.w = w;
	}
	
	@Override
	public int compareTo(Edge edge) {
		if(this.w > edge.w) return  1;
		else return -1;
	}
}

