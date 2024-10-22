import java.io.*;
import java.util.*;

public class Main { 
	 public static void main(String args[]) throws IOException {
		 Queue<Integer[]> jewelHeap = new PriorityQueue<>(new Comparator<Integer[]>() {// 무게, 가격
			@Override
			public int compare(Integer[] o1, Integer[] o2) {
				if(o1[0] == o2[0]) {
					return o1[1] - o2[1]; 
				}
				return o1[0] - o2[0];
			}
		 });
		 
		 Queue<Integer> bagHeap = new PriorityQueue<>();
		 
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 String[] arr = br.readLine().split(" ");
		 
		 int N = Integer.parseInt(arr[0]);
		 int K = Integer.parseInt(arr[1]);
		 
	     for(int i = 0; i < N; i++) {
	    	 String[] arr1 = br.readLine().split(" ");
			 
			 Integer[] temp = new Integer[2];
			 temp[0] = Integer.parseInt(arr1[0]);
			 temp[1] = Integer.parseInt(arr1[1]); 
			 
			 jewelHeap.offer(temp);
	     }
	     
	     for (int i =0 ; i < K; i++) {
	    	 int maxW = Integer.parseInt(br.readLine());
	    	 bagHeap.offer(maxW);
	     }
	     
	     long answer = 0L;
	     Queue<Integer> candidates = new PriorityQueue<>(Collections.reverseOrder());
	     for (int i =0 ; i < K; i++) {
	    	 int maxWeight = bagHeap.poll();
	    	 
	    	 while (!jewelHeap.isEmpty() && jewelHeap.peek()[0] <= maxWeight) {
	    		 candidates.offer(jewelHeap.poll()[1]);
	    	 }
	    	 
	    	 if (!candidates.isEmpty()) {
	    		 answer += candidates.poll();
	    	 }
	     }
	     
	     System.out.println(answer);
	 }	
}