import java.util.*;
import java.io.*;

public class Main {
	static int[][] arr;
	static int[][] afterRotation;
	static int R,N,M, count;
	
	static int[] dx = {1,0,-1,0}; 
	static int[] dy = {0,1,0,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		afterRotation = new int[N][M];
		
		count = Math.min(N, M)/2;
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i <R; i++) {
			rotation();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0 ;j<M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}
	
	public static void rotation() {
		
		for(int i=0; i<count; i++) {
			int x = i;
			int y = i;
			
			int temp = arr[y][x];
			
			int idx = 0;
			while(idx < 4) {
				int nextX = x + dx[idx];
				int nextY = y + dy[idx];
				
				if(nextY >=i && nextY < N-i && nextX >= i && nextX < M-i) {
					arr[y][x] = arr[nextY][nextX];
					x = nextX;
					y = nextY;
				} else {
					idx++;
				}
			}
			arr[y+1][x] = temp;
		}
	
	}
}