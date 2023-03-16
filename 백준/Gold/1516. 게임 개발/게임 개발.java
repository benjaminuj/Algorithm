import java.util.Scanner;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class  Main {
public static ArrayList<Integer> list[];
public static int time[];
public static int answer[];
public static int indegree[];
public static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		time = new int[N+1];
		list = new ArrayList[N+1];
		indegree = new int[N+1];
		answer = new int[N+1];
		for(int i=0; i<N+1; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i=1; i<N+1; i++) {
			time[i] = sc.nextInt();
			int before = sc.nextInt();
			while(before != -1) {
				list[before].add(i);
				indegree[i]++;
				before = sc.nextInt();
			}
		}
		topologicalSort(time,list);
		for(int i=1; i<answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
	
	public static void topologicalSort(int[] time, ArrayList<Integer>[] list) {
		Queue<Integer> q = new LinkedList<>();
		for(int i=1; i<N+1; i++) {
			if(indegree[i] ==0) {
				q.add(i);
			}
		}
		while(!q.isEmpty()) {
			int here = q.poll();
			for(int n : list[here]) {
				if(--indegree[n] ==0) {
					q.add(n);
				}
				answer[n] = Math.max(answer[n], answer[here] + time[here]);
			}
		}
		for(int i=1; i<N+1; i++) {
			answer[i] += time[i];
		}
	}

}
