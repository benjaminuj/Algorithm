import java.util.*;
import java.io.*;

public class Main {
	
	static PriorityQueue<Long> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			long answer = 0;
			pq.clear();
			int K = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<K; j++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
			
			while(!pq.isEmpty()) {
				if(pq.size() == 1) { 
					break;
				}
				
				long sum = pq.poll() + pq.poll();
				answer += sum;
				pq.add(sum);
			}
			System.out.println(answer);
		}	
	}
}