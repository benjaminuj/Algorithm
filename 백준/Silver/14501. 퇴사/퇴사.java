import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
static int[][] info;
static int[] payment;
static int[] finishDay;
static boolean[] isFinish;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		info = new int[N+1][2];
		payment = new int[N+2]; //예외 : 마지막날의 다음날까지 계산필요 (마지막날에끝나는 일 체크위해)
		finishDay = new int[N+1];
		isFinish = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			info[i][0] = T;
			info[i][1] = P;
			payment[i] = P;
			finishDay[i] = i+T;
			if(finishDay[i] > N+1) finishDay[i] = Integer.MAX_VALUE;
		}
		dp(N);
		System.out.println(payment[N+1]);
	}
	public static void dp(int N) {
		for(int i=2; i<=N ; i++) {
			if(finishDay[i] == Integer.MAX_VALUE) {
				payment[i] = Integer.MAX_VALUE;
				continue;
			}
			for(int j=1; j<i; j++) {
				if(finishDay[j] <= i && !isFinish[j]) {
					isFinish[j] = true;
				}
				if(isFinish[j]) {
					payment[i] = Math.max(payment[i], payment[j] + info[i][1]);
				}
			}
		}
		
		//예외 코드
		for(int i=1; i<=N; i++) {
			if(finishDay[i] <= N+1 && !isFinish[i]) {
				isFinish[i] = true;
			}
			if(isFinish[i]) {
				payment[N+1] = Math.max(payment[N+1], payment[i]);
			}
		}
	}
}
