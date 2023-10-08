import java.util.*;
import java.io.*;

public class Main {
	
	static class Info implements Comparable<Info> {
		int priority;
		int index;
		
		public Info(int priority, int index) {
			this.priority = priority;
			this.index = index;
		}
		
		@Override
		public int compareTo(Info o) {
			return o.priority - this.priority;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		PriorityQueue<Info> pq = new PriorityQueue<Info>();
		Queue<Info> q = new LinkedList<>();
		
		for (int i = 0; i < tc; i++) {
			String[] arr = br.readLine().split(" ");
			int N = Integer.parseInt(arr[0]);
			int M = Integer.parseInt(arr[1]);
			
			String[] arr1 = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				pq.add(new Info(Integer.parseInt(arr1[j]), j));
				q.add(new Info(Integer.parseInt(arr1[j]), j));
			}
			int count = 0;
			boolean is = true;
			while(is) {
				while(pq.peek().priority != q.peek().priority) {
					Info temp = q.poll();
					q.add(temp);
				}
				count++;
				pq.poll();
				if(M == q.poll().index) {
					is= false;
				}
			}
			
			System.out.println(count);
			q.clear();
			pq.clear();
		}
		
	}
}
