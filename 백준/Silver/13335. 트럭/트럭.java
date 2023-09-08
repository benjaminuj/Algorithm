import java.util.*;
import java.io.*;

public class Main {
	
	public class Truck {
		int weight;
		int moveCnt;
		
		public void truck(int weight) {
			this.weight  = weight;
			this.moveCnt = 0;
		}
		
		public void setMoveCnt(int moveCnt) {
			this.moveCnt = moveCnt;
		}
	}

	static Queue<Truck> truck = new LinkedList<>();
	static int[] weight;
	static int[] moveCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		weight = new int[n];
		moveCnt = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		int startIdx = 0, endIdx = 0;
		int sum = weight[0];
		moveCnt[0] = 1;
		int totalTime = 1;
		while(endIdx < n) {
			totalTime++;
			for(int i=startIdx; i<=endIdx; i++) {
				if(++moveCnt[i] > w) {
					startIdx = i+1;
					sum -= weight[i];
				}
			}
			if(startIdx >=n) break;
			if(startIdx > endIdx)  {
				endIdx = startIdx;
			}
			if(endIdx >= n) break;
			// 새로운 트럭 유입 
			if(moveCnt[endIdx] == 0) {
				if(sum + weight[endIdx] <= L) {
					moveCnt[endIdx]++;
					sum += weight[endIdx];
				}
			} else if(endIdx +1 <n) {
				if(sum + weight[endIdx+1] <= L) {
					endIdx++;
					moveCnt[endIdx]++;
					sum += weight[endIdx];
				}
			}
		}
		totalTime += (w-moveCnt[endIdx]+1);
		System.out.print(totalTime);
	}
}