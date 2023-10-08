import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited;
	static char[][] arr;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = Integer.MIN_VALUE;
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				for (int d = 0; d < 4; d++) {
					int nextX = i + dx[d];
					int nextY = j + dy[d];
					
					if (nextX > 0 && nextX < N && nextY > 0 && nextY < N) {
						if (isPossibleChange(i, j, nextX, nextY)) {
							char temp = arr[i][j];
							arr[i][j] = arr[nextX][nextY];
							arr[nextX][nextY] = temp;
						}
						
						// 최대 빙고수 
						int max = bingo();
						
						// 최대값 갱신
						if (answer < max) {
							answer = max;
						}
						
						// 되돌리기 
						char temp = arr[i][j];
						arr[i][j] = arr[nextX][nextY];
						arr[nextX][nextY] = temp;
						
					}
					
					
				}
			}
		}
		System.out.println(answer);
		
	}
	
	public static boolean isPossibleChange(int x, int y, int nextX, int nextY) {
		return (!visited[x][y] || !visited[nextX][nextY]);
	}
	
	public static int bingo() {	
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				char stand = arr[i][j];
				
				// 오른쪽 
				int count = 1;
				int x = i;
				while(++x < N) {
					if(arr[x][j] == stand) count++;
					else break;
				}
				
				if(max < count) max = count;
				
				// 아래 
				count = 1;
				int y = j;
				while(++y < N) {
					if(arr[i][y] == stand) count++;
					else break;
				}
				
				if(max < count) max = count;
				
				// 왼
				count = 1;
				x = i;
				while(--x > 0) {
					if(arr[x][j] == stand) count++;
					else break;
				}
				
				if(max < count) max = count;
				
				// 위 
				count = 1;
				y = j;
				while(--y > 0) {
					if(arr[i][y] == stand) count++;
					else break;
				}
				if(max < count) max = count;
			}
		}
		return max;
	}
	

}
