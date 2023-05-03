import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> MyQueue = new PriorityQueue<>((o1,o2)-> {
			int first_abs = Math.abs(o1);
			int second_abs = Math.abs(o2);
			if(first_abs == second_abs) {
				return o1 > o2 ? 1 : -1;
			} else {
				return first_abs - second_abs;
			}
		});
		for(int i =1; i<= N ;i++) {
			int x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(MyQueue.isEmpty()) System.out.println("0");
				else System.out.println(MyQueue.poll());
			} else MyQueue.add(x);
		}
	}
}
