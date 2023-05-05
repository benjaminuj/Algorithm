import java.io.*;
import java.util.*;

class Main{
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] answer = new int[N+1][K+1];
		for(int i=1; i<=N; i++) {
			answer[i][0] = 1;
			for(int j=1; j<=Math.min(i,K); j++) {
				if(i == j)  {
					answer[i][j] = 1;
					continue;
				} 
				answer[i][j] = answer[i-1][j-1] + answer[i-1][j];
			}
		}
		System.out.println(answer[N][K]);
    }
}

