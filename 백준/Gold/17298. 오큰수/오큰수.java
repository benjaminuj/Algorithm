import java.io.*;
import java.util.*;

class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		int[] A = new int[N+1];
		int[] answer = new int[N+1];
		for(int i =1; i<= N ;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		int index = 2;
		stack.add(1);
		while(index <= N) {
			while(!stack.isEmpty() && A[index] > A[stack.peek()]) {
				answer[stack.pop()] = A[index];
			} 
			stack.push(index++);
		}
		while(!stack.isEmpty()) {
			answer[stack.pop()] = -1;
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=1; i<=N; i++) {
			bw.write(answer[i] + " ");
		}
		bw.flush();
	}
	
}