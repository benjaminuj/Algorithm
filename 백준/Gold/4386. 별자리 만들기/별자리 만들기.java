import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Edges implements Comparable<Edges> {
	int w;
	double cost;
	
	Edges(int w, double cost) {
		this.w = w;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edges o) {
		if (this.cost > o.cost) {
            return 1;
        } else if(this.cost < o.cost)  {
            return -1;
        } else {
            return 0;
        }
	}
}

public class Main {
static List<Edges>[] graph;
	public static void prim(int start, int n) {
		boolean[] visited = new boolean[n+1];
		PriorityQueue<Edges> pq = new PriorityQueue<>();
		double total=0;
		pq.offer(new Edges(start,0.0));
		
		while(!pq.isEmpty()) {
			Edges edge = pq.poll();
			int v = edge.w;
			double cost = edge.cost;
			if(visited[v]) continue;
			visited[v] = true;
			total += cost;
			for(Edges e : graph[v]) {
				pq.offer(e);
			}
		}
		System.out.printf("%.2f",total);
	}
	
	public static double getDistance(double x, double y) {
		return Math.sqrt(x + y);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		double[][] location = new double[n+1][2];
		graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			location[i][0] = x;
			location[i][1] = y;
		}
		for(int i=1; i<n ;i++) {
			for(int j=i+1; j<=n; j++) {
				double xPow = Math.pow(location[i][0]-location[j][0],2);
				double yPow = Math.pow(location[i][1]-location[j][1],2);
				double dis = getDistance(xPow,yPow);
				graph[i].add(new Edges(j,dis));
				graph[j].add(new Edges(i,dis));
			}
		}
		prim(1,n);
	}

}
