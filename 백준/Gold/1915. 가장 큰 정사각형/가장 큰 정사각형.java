import java.io.IOException;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws IOException {
		long[][] D = new long[1001][1001];
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		long max = 0;
		for(int i=0; i<n ;i++) {
			String mline = sc.next();
			for(int j=0; j<m; j++) {
				D[i][j] = Long.parseLong(String.valueOf(mline.charAt(j)));
			}
		}
		max = dp(n,m, D);
		System.out.println(max*max);
	}
	
	public static long dp(int n, int m, long[][] D) {
		long max = 1;
		boolean isOneExistence = false;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(D[i][j] != 0) {
					isOneExistence = true;
					if(i >0 && j>0) {
						if(D[i-1][j] != 0 && D[i][j-1] != 0 && D[i-1][j-1] != 0) {
//							if(D[i-1][j] == D[i][j-1] && D[i-1][j] == D[i-1][j-1] && D[i][j-1] == D[i-1][j-1]) {
//								D[i][j] = ++max;
//								continue;
//							}
							D[i][j] = Math.min(D[i-1][j-1] , Math.min(D[i-1][j],D[i][j-1])) + 1;
							if(max < D[i][j]) {
								max = D[i][j];
							}
						}
					}
				}
			}
		}
		if(!isOneExistence)max = 0;
		return max;
	}
}
