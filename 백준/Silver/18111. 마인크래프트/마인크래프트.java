import java.util.*;
import java.io.*;

public class Main {
	public static int[][] ground;
	public static int highest = Integer.MIN_VALUE;
	public static int N,M,B, tempB;

	public static void main(String[] args) throws IOException {
		int minTime = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		tempB = B;
		
		ground = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<M; j++) {
				ground[i][j] = Integer.parseInt(st.nextToken());
				if(highest < ground[i][j]) highest = ground[i][j];
			}
		}
		
		int answerFloor = highest;
		
		while(highest>=0) {
			int mesureTime = bruteForce();
			if(mesureTime == -1) {
				highest--;
				continue;
			}
			if(mesureTime < minTime) {
				minTime = mesureTime;
				answerFloor = highest;
			}
			highest--;
		}
		
		System.out.print(minTime + " " + answerFloor);
	}
	
	public static int bruteForce() {
		int time = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(ground[i][j] > highest) {
					time += 2*(ground[i][j] - highest);
					tempB += ground[i][j] - highest;
				} else if (ground[i][j] < highest) {
					time += highest - ground[i][j];
					tempB -= highest - ground[i][j];
				}
			}
		}
		if(tempB <0) time = -1;
		tempB = B;
		return time;
	}
}