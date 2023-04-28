import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
static int[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		result = new int[N+1];
		dp(N);
		System.out.print(result[N]);
	}
	
	public static void dp(int N) {
		for(int i=2; i<=N; i++) {
			result[i] = result[i-1] +1;
			if(i%2 == 0) result[i] = Math.min(result[i], result[i/2]+1);
			if(i%3 == 0) result[i] = Math.min(result[i], result[i/3]+1);
		}
	}
}
