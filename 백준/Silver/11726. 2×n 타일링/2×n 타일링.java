import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] answer = new int[n+1];
		if(n == 1) {
			System.out.println("1");
			return;
		}
		if(n == 2) {
			System.out.println("2");
			return;
		}
		answer[1] = 1;
		answer[2] = 2;
		
		for(int i=3;i<=n; i++) {
			answer[i] = (answer[i-1] + answer[i-2]) %10007;
		}
		System.out.println(answer[n]);
	}
}
