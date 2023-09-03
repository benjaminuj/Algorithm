import java.util.*;
import java.io.*;

public class Main {
	public static int[] dy = {-1,0,1,0};
	public static int[] dx = {0,1,0,-1};
	public static int N, M;
	public static int[][] visited;
	public static int[][] map;
	public static int[][] virusMap;
    public static int answer = Integer.MIN_VALUE;
    public static List<Info> wall = new ArrayList<>();
    public static Queue<Info> viruses = new LinkedList<>();
	
	public static class Info {
		int x;
		int y;
		
		public Info(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		
		for(int i=0; i<N; i++) {
			st =  new StringTokenizer(br.readLine());
			for(int j=0; j<M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) wall.add(new Info(i,j));
			}
		}
		
		insertWall(map);
		
		System.out.print(answer);
		
	}
	
	public static void insertWall(int[][] map) {
		for(int i=0; i<wall.size()-2; i++) {
			for(int j=i+1; j<wall.size()-1; j++) {
				for(int k=j+1; k<wall.size(); k++) {
					map[wall.get(i).y][wall.get(i).x] = 1;
					map[wall.get(j).y][wall.get(j).x] = 1;
					map[wall.get(k).y][wall.get(k).x] = 1;
					answer = Math.max(answer, bfs());
					
					map[wall.get(i).y][wall.get(i).x] = 0;
					map[wall.get(j).y][wall.get(j).x] = 0;
					map[wall.get(k).y][wall.get(k).x] = 0;
					
				}
			}
		}
	}
	
	public static int bfs() {
		int result = 0;
		virusMap = new int[N][M];
		visited = new int[N][M];
		viruses.clear();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				virusMap[i][j] = map[i][j];
				if(virusMap[i][j] == 2) viruses.add(new Info(i,j));
			}
		}
		
		
		while(!viruses.isEmpty()) {
			
			Info virus = viruses.poll();
			int x = virus.x;
			int y = virus.y;
			visited[y][x] = 1;
			
			
			for(int i=0; i<4; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				
				if(nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) continue;
				if(virusMap[nextY][nextX] == 0 && visited[nextY][nextX] != 1) {
					
					visited[nextY][nextX] = 1;
					virusMap[nextY][nextX] = 2;
					viruses.add(new Info(nextY,nextX));
					
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(virusMap[i][j] == 0) result++;
			}
		}
		return result;
		
	}
		

}


