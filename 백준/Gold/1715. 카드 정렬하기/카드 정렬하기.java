import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		long answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<Long>(N);
		for(int i=0; i<N; i++) {
			pq.offer(Long.parseLong(br.readLine()));
		}
		
		if(pq.size() ==1) {
			answer = 0;
		} else {
			while(!pq.isEmpty()) {
				long sum = 0;
				for(int i=0; i<2; i++) {
					sum += pq.poll();
				}
				pq.offer(sum);
				answer += sum;
				if(pq.size() == 1) break;
			}
		}
		
		
	
		System.out.println(answer);
	}

}
