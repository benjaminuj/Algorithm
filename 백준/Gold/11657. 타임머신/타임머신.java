import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
public static ArrayList<tNode> list = new ArrayList<>();;
public static long[] distance;
public static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		distance = new long[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			makeDirected(A,B,C);
		}
		
		distance[1]=0;
		boolean isInfinite = bellmanFord(1);
		if(isInfinite) bw.write("-1");
		else {
			for(int i=2; i<=N; i++) {
				if(distance[i] == Integer.MAX_VALUE) bw.write("-1 \n"); 
				else bw.write(distance[i]+ "\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
	public static void makeDirected(int A, int B, int C) {
		list.add(new tNode(A,B,C));
	}
	public static boolean bellmanFord(int v) {
		boolean isInfinite = false;
		for(int i=1; i<=N; i++) {
			for(int k=0; k<list.size(); k++) {
				int from = list.get(k).start;
				int to = list.get(k).end;
				int time = list.get(k).time;
				if(distance[from] == Integer.MAX_VALUE) continue;
				if(distance[to] > distance[from] + time) {
					if(i==N) {
						isInfinite = true;
						return isInfinite;
					}
					distance[to] = distance[from] + time;
				}
			}
		}
		return isInfinite;
	}
}
class tNode {
	int start;
	int end;
	int time;
	tNode(int start, int end, int time) {
		this.start = start;
		this.end = end;
		this.time = time;
	}
}
