import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
public static int[][] list;
public static PriorityQueue<Integer>[] distQueue;
public static int k,n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n =  Integer.parseInt(st.nextToken());
		int m =  Integer.parseInt(st.nextToken());
		k =  Integer.parseInt(st.nextToken());
		distQueue = new PriorityQueue[n+1];
		Comparator<Integer> cp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1<o2 ? 1 : -1;
			}
		};
		for(int i=1; i<n+1; i++) {
			distQueue[i] = new PriorityQueue<Integer>(k,cp);
		}
		list = new int[n+1][n+1];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a][b] = c;
		}
		dijkstra(list, distQueue);
		for(int i=1; i<n+1; i++) {
			if(distQueue[i].size() == k) bw.write(distQueue[i].peek() + "\n");
			else bw.write("-1" + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dijkstra(int[][] list,PriorityQueue<Integer>[] distQueue) {
		PriorityQueue<kNode> pq = new PriorityQueue<>();
		pq.offer(new kNode(1,0));
		distQueue[1].add(0);
		while(!pq.isEmpty()) {
			kNode now = pq.poll();
			for(int i=1; i<n+1; i++) {
				if(list[now.node][i] != 0) {
					if(distQueue[i].size() < k) {
						distQueue[i].offer(now.time + list[now.node][i]);
						pq.offer(new kNode(i, now.time + list[now.node][i]));
					} else {
						if(distQueue[i].peek() > now.time + list[now.node][i]) {
							distQueue[i].poll();
							distQueue[i].add(now.time + list[now.node][i]);
							pq.add(new kNode(i, now.time + list[now.node][i]));
						}
					}
				}
			}
		}
	}

}

class kNode implements Comparable<kNode>{
	int node;
	int time;
	
	kNode(int targetNode, int time) {
		this.node = targetNode;
		this.time = time;
	}
	
	@Override
	public int compareTo(kNode n) {
		if(this.time> n.time) return 1;
		else return -1;
	}
	
}
