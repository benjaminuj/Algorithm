import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] answer = new long[n+1][10];
		long result = 0;
		for(int i=1; i<10; i++) {
			answer[1][i] = 1;
			if(n == 1) {
				result += answer[1][i];
			}
		}
		
		for(int i=2; i<=n ;i++) {
			for(int j=0; j<10; j++) {
				if(j == 0) {
					answer[i][j] = answer[i-1][1];
				} else if(j == 9) {
					answer[i][j] = answer[i-1][8];
				} else {
					answer[i][j] = (answer[i-1][j-1] + answer[i-1][j+1])% 1000000000;
				}
				
				if(i == n) {
					result =  (result + answer[i][j])% 1000000000;
				}
			}
		}
		System.out.print(result);
		
		
	}
}
