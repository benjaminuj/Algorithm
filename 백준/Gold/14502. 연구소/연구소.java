import java.util.*;
import java.io.*;

public class Main {
	static String[][] grid;
	static boolean[][] visited;
	static int[][]newWall = new int[3][2];
	static int[][] dr = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int virusCnt;
	static int minVirus = Integer.MAX_VALUE;
	static List<int[]> virus = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		grid = new String[N][M];
		visited = new boolean[N][M];
		int baseOneCnt = 0;
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				grid[r][c] = st.nextToken();
				if (grid[r][c].equals("1")) {
					baseOneCnt++;
				}
				if (grid[r][c].equals("2")) {
					virus.add(new int[] {r,c});
				}
			}
		}
		
		// 벽 3개 둘 수 있는 조합구하기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (grid[r][c].equals("0")) {
					backtracking(r, c, 1);
				}
			}
		}
		System.out.println(N*M - (baseOneCnt + 3) - minVirus);
	}
	
	// 감염 로직 bfs
	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		for (int[] vir : virus) {
			q.offer(vir);
			visited[vir[0]][vir[1]] = true;
			virusCnt++;
		}
	
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int[] d : dr) {
				int nextR = now[0] + d[0];
				int nextC = now[1] + d[1];
				
				if (isValid(nextR, nextC) && grid[nextR][nextC].equals("0") && !visited[nextR][nextC] && !isNewWall(nextR, nextC)) {
					q.offer(new int[]{nextR, nextC});
					virusCnt++;
					visited[nextR][nextC] = true;
				}
			}
		}
	}
	
	// 벽 3개 조합 구하기
	public static void backtracking(int r, int c, int cnt) {
		newWall[cnt-1][0] = r;
		newWall[cnt-1][1] = c;
		
		if (cnt == 3) {
			bfs();
			minVirus = Math.min(minVirus, virusCnt);
			for (boolean[] a : visited) {
				Arrays.fill(a, false);
			}
			virusCnt = 0;
			return;
		}
		
		for (int i = r; i < grid.length; i++) {
			for (int j = c; j < grid[0].length; j++) {
				int nextC = (j+1) % grid[0].length; // 
				int nextR = i;
				if (j + 1 >= grid[0].length) {
					nextR++;
				}		
				
				if (isValid(nextR, nextC) && grid[nextR][nextC].equals("0")) {
					backtracking(nextR, nextC, cnt+1);
				}
				c = 0;	
			}
		}
	}
	
	public static boolean isValid(int r, int c) {
		return r < grid.length && r >= 0 && c < grid[0].length && c >= 0;
	}
	
	public static boolean isNewWall(int r, int c) {
		return (r == newWall[0][0] && c == newWall[0][1]) || (r == newWall[1][0] && c == newWall[1][1]) || (r == newWall[2][0] && c == newWall[2][1]);
	}

}
