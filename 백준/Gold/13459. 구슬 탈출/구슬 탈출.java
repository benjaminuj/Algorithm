import java.io.*;
import java.util.*;

class Main {
	static String[][] grid;
	static int N, M;
	static int[] redballIdx = new int[2];
	static int[] blueballIdx = new int[2];
	static int[] hallIdx = new int[2];
	static int[][] dr = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean success = false;
	static Set<String> visited = new HashSet<>();
	static int redR, redC, blueR, blueC;
	static boolean fallBlueHall = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new String[N][M];
		
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			grid[r] = line.split("");
			for (int c = 0; c < M; c++) {
				if (grid[r][c].equals("R")) {
					redballIdx[0] = r;
					redballIdx[1] = c;
				} else if (grid[r][c].equals("B")) {
					blueballIdx[0] = r;
					blueballIdx[1] = c;
				} else if (grid[r][c].equals("O")) {
					hallIdx[0] = r;
					hallIdx[1] = c;
				}
			}
		}
		
		bfs(redballIdx[0], redballIdx[1], blueballIdx[0], blueballIdx[1], 0);
		
		if (success) {
			System.out.print("1");
		} else {
			System.out.print("0");
		}
    }
	
	public static void bfs(int startRedR, int startRedC, int startBlueR, int startBlueC, int cnt) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {startRedR, startRedC, startBlueR, startBlueC, cnt});
		visited.add(new String(startRedR + " " + startRedC + " " + startBlueR + " " + startBlueC));
		

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (cur[4] == 10) {
				continue;
			}
			
			for (int[] d : dr) {
				fallBlueHall = false;
				success = false;
				
				redR = cur[0];
				redC = cur[1];
				blueR = cur[2];
				blueC = cur[3];

				
				boolean redFirst = decideFirstBall(d);
				
				if (redFirst) {
					redMove(d);
					blueMove(d);
				} else {
					blueMove(d);
					redMove(d);
				}

				if (fallBlueHall) {
					continue;
				}
				
				if (success) {
					return;
				}
				
				if (!visited.add(new String(redR + " " + redC + " " + blueR + " " + blueC))) {
					continue;
				}

				q.offer(new int[] {redR, redC, blueR, blueC, cur[4]+1});
				
			}
		}
	}
	
	// 빨간구슬 이동
	public static void redMove(int[] d) {
		boolean canMoveRed = false;
		
		while (true) {
			if (isValid(redR + d[0], redC + d[1]) && !isCrush(redR + d[0], redC + d[1], blueR, blueC)) {
				if (isHall(redR + d[0], redC + d[1])) { // 이동중 구멍을 만나면
					success = true;
					return;
				}
				canMoveRed = true;
				redR += d[0];
				redC += d[1];
			} else {
				break;
			} 
		}
	}
	
	// 파란구슬 이동
	public static void blueMove(int[] d) {
		boolean canMoveBlue = false;
		
		while (true) {
			if (isValid(blueR + d[0], blueC + d[1]) && (success || !isCrush(redR, redC, blueR + d[0], blueC + d[1]))) {
				if (isHall(blueR + d[0], blueC + d[1])) { // 이동중 구멍을 만나면
					fallBlueHall = true;
					break;
				}
				canMoveBlue = true;
				blueR += d[0];
				blueC += d[1];
			} else {
				break;
			}
		}
	}
	
	public static boolean isCrush(int redR, int redC, int blueR, int blueC) {
		return redR == blueR && redC == blueC;
	}
	
	public static boolean isValid(int r, int c) {
		return r > 0 && r < N - 1 && c > 0 && c < M - 1 && !grid[r][c].equals("#");
	}
	
	public static boolean isHall(int r, int c) {
		return r == hallIdx[0] && c == hallIdx[1];
	}
	
	// 어느공이 앞에서 이동하는지 검사. (TRUE : red 먼저 이동)
	public static boolean decideFirstBall(int[] direction) {
        if (direction[0] == 0) { // 좌우 이동
            return (direction[1] == 1) ? (redC > blueC) : (redC < blueC); 
        } else { // 상하 이동
            return (direction[0] == 1) ? (redR > blueR) : (redR < blueR);
        }
    }
}

