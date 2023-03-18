import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	public static ArrayList<sNode> list;
	public static long[] distance;
	public static int[] availableMoney;
	public static int N,M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int here = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		distance = new long[N];
		availableMoney = new int[N];
		list = new ArrayList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start =Integer.parseInt(st.nextToken());
			int end =Integer.parseInt(st.nextToken());
			int value =Integer.parseInt(st.nextToken());
			list.add(new sNode(start,end,value));
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			availableMoney[i] = Integer.parseInt(st.nextToken());
			distance[i] = Long.MIN_VALUE;
		}
		distance[here] = availableMoney[here];
		bellanFord(here);
		if(distance[goal] == Long.MIN_VALUE) System.out.println("gg");
		else if(distance[goal] == Long.MAX_VALUE) System.out.println("Gee");
		else System.out.println(distance[goal]);
	}
	
	public static void bellanFord(int v) {
		for(int i=1; i<=2*N; i++) {
			for(int j=0; j<list.size(); j++) {
				int start = list.get(j).start;
				int end = list.get(j).end;
				int value = list.get(j).value;
				if(distance[start] == Long.MAX_VALUE) distance[end] = Long.MAX_VALUE;
				else if(distance[start] != Long.MIN_VALUE && (distance[end] < distance[start] - value + availableMoney[end])) {
					if(i >= N) {
						distance[end] = Long.MAX_VALUE;
					}
					else distance[end] = distance[start] - value + availableMoney[end];
				} 
			}
		}
	}
}

class sNode{
	int start,end,value;
	
	public sNode(int start, int end, int value) {
		this.start = start;
		this.end = end;
		this.value = value;
	}
}
