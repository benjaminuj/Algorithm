import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		 PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 int N = Integer.parseInt(br.readLine());
		 StringTokenizer st = null;
		 for(int i=0 ;i<N; i++) {
			 st = new StringTokenizer(br.readLine());
			 for(int j =0; j<N; j++) {
				 pq.offer(Integer.parseInt(st.nextToken()));
			 }
		 }
		 
		 for(int i=1; i<N; i++) {
			 pq.poll();
		 }
		 
		 System.out.println(pq.peek());
	}
}
