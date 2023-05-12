import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		long[][] d = new long[N+1][K+1];
		for(int i=1; i<=N; i++) {
			for(int j=0; j<=Math.min(i, K); j++) {
				if((j == 0) || (i == j)){
					d[i][j] = 1;
				}
				if(i >= 2 && j >= 1) {
					d[i][j] = (d[i-1][j] + d[i-1][j-1])%10007;
				}
			}
		}
		System.out.println(d[N][K]%10007);
    }
}