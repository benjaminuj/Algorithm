import java.util.*;
import java.io.*;

public class Main {
	static String[][] grid;
	static boolean[][] visited;
	static int[][] dr = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int M, N;
	static int answer = Integer.MAX_VALUE;
	static List<int[]> virus = new ArrayList<>();
	static Set<String> hash = new HashSet<>(); // 빈 공간 (빈 칸만)이 남았는지 체크 위한 해시
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new String[N][N];
		visited = new boolean[N][N];
		List<Integer> choiceVirusIdx = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			
			for (int c = 0; c < N; c++) {
				grid[r][c] = st.nextToken();
				
				if (grid[r][c].equals("2")) {
					virus.add(new int[] {r,c});
				}
				
				if (grid[r][c].equals("0")) {
					hash.add(r + "," + c);
				}
			}
		}
		
		if (hash.size() == 0) { 
			System.out.println("0");
			return;
		}
		
		// 비활성 바이러스에서 M개 선택 조합 
		for (int i = 0; i < virus.size(); i++) {
			backtracking(i, choiceVirusIdx, 1);
		}
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(answer);
		}
	}
	
	// 감염 로직 bfs
	public static void bfs(List<Integer> choiceVirusIdx, Set<String> hash) {
		Queue<int[]> q = new ArrayDeque<>(); // {r, c, time} 행, 열, 현재 소요시간
		int tookTime = 0;
		
		for (int i : choiceVirusIdx) {
			int[] temp = virus.get(i);
			q.offer(new int[]{temp[0], temp[1], 0});
			visited[virus.get(i)[0]][virus.get(i)[1]] = true;
		}
	
		while (!q.isEmpty()) {
			int[] now = q.poll();
			hash.remove(now[0] + "," + now[1]);
			
			if (hash.size() == 0) {
				answer = Math.min(answer, now[2]);
				break;
			}
			
			for (int[] d : dr) {
				int nextR = now[0] + d[0];
				int nextC = now[1] + d[1];
				
				if (isValid(nextR, nextC)) {
					q.offer(new int[]{nextR, nextC, now[2]+1});
					visited[nextR][nextC] = true;
					tookTime = now[2] + 1;
				}
				
			}
			
		}
		if (isAllVisited()) {
			answer = Math.min(answer, tookTime);
		}
	}
	
	public static boolean isAllVisited() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j].equals("0")) {
					if (!visited[i][j]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	// 바이러스 감염 M개 조합
	public static void backtracking(int start, List<Integer> choiceVirusIdx, int cnt) {
		choiceVirusIdx.add(start);
		
		if (cnt == M) {
			Set<String> copy = new HashSet<>(hash);
			bfs(choiceVirusIdx, copy);

			for (boolean[] a : visited) {
				Arrays.fill(a, false);
			}
			choiceVirusIdx.remove(choiceVirusIdx.size() - 1);
			return; 
		}
		
		for (int i = start; i < virus.size(); i++) {
			if (i+1 == virus.size()) {
				continue;
			}
			backtracking(i+1, choiceVirusIdx, cnt+1);
			
		}
		choiceVirusIdx.remove(choiceVirusIdx.size() - 1);
	}
	
	public static boolean isValid(int r, int c) {
		return r < N && r >= 0 && c < N && c >= 0 && !visited[r][c] && (grid[r][c].equals("0") || grid[r][c].equals("2"));
	}
}