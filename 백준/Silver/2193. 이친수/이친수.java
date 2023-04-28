import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		// 현재 자리 수 이전 자리수의 경우의 수에서 끝자리 수의 0,1 개수
		long zeroCnt = 0; 
		long oneCnt = 1;//가장 앞은 1로 시작
		
		long temp = 0;
		int N = sc.nextInt();
		long[] cnt = new long[N+1]; 
		cnt[1] = 1;
		for(int i=2; i<=N; i++) {
			cnt[i] = zeroCnt * 2 + oneCnt * 1;
			temp = oneCnt;
			oneCnt = zeroCnt; // 1다음은 0만 올 수있음
			zeroCnt = temp + zeroCnt; //0다음은 0,1둘 다 올 수 있음
		}
		System.out.println(cnt[N]);
	}
}