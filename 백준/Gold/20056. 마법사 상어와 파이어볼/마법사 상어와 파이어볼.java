import java.io.*;
import java.util.*;

public class Main {
	static Map<Point, List<Info>> map = new HashMap<>();
	
	final static int[][] dr = new int[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static int N, K, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(reader.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for (int i= 0 ; i < M; i++) {
			st = new StringTokenizer(reader.readLine());
			
			Point p = new Point(Integer.parseInt(st.nextToken()) -1, Integer.parseInt(st.nextToken())-1);
			map.putIfAbsent(p, new ArrayList<>());
			
			map.get(p).add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int cnt = 0;
		while (true) {
			run();
			cnt++;
			if (cnt == K) {
				for (Point p : map.keySet()) {
					for (Info i : map.get(p)) {
						answer += i.m;
					}
				}
				break;
			}
		}
		 
		 System.out.println(answer);
	}
	
	static void run() {
		int cnt = 0;
		Map<Point, List<Info>> moved = new HashMap<>();

		// 이동 
		// ?? 이동한거 새로 만든 자료구조에 옮기는ㄱ ㅔ좋을까? 아니면 업데이트할까? 
		for (Point p : map.keySet()) {
			for (Info i : map.get(p)) {
				 int distance = i.s % N; // 실제 이동 거리
		         int nx = (p.x + dr[i.d][0] * distance + N) % N; // 새로운 x 좌표
		         int ny = (p.y + dr[i.d][1] * distance + N) % N; // 새로운 y 좌표
				
				Point nP = new Point(nx, ny);
				moved.putIfAbsent(nP, new ArrayList<>());
				moved.get(nP).add(i);
			}
		}
		
		// moved로 덮어쓰기
		map = new HashMap<Point, List<Info>>(moved);

		// 같은 칸에 있는지 체크
		for (Point p : map.keySet()) {
			if (map.get(p).size() > 1) {
				List<Info> temp = map.get(p);
				int sumM = 0;
				int sumS = 0;
				int standD = (temp.get(0).d) % 2;
				boolean allD = true; // 모든 방향이 홀수이거나 짝수인지 
				
				for (Info i : temp) {
					sumM += i.m;
					sumS += i.s;
					
					if (i.d % 2 != standD) allD = false;
				}
				
				int newM = sumM/5;
				int newS = sumS/temp.size();
				
				map.get(p).clear();
				
				// 질량이 0 이면 소멸하고 끝
				if (newM == 0) continue;
				
				int[] newD = new int[4];
				
				if (allD) {
					newD[0] = 0;
					newD[1] = 2;
					newD[2] = 4;
					newD[3] = 6;
				} else {
					newD[0] = 1;
					newD[1] = 3;
					newD[2] = 5;
					newD[3] = 7;
				}
				
				map.putIfAbsent(p, new ArrayList<>());
				map.get(p).add(new Info(newM, newS, newD[0]));
				map.get(p).add(new Info(newM, newS, newD[1]));
				map.get(p).add(new Info(newM, newS, newD[2]));
				map.get(p).add(new Info(newM, newS, newD[3]));
			}
		}

	}
	
	static class Point {
		int x; 
		int y; 
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof Point) {
				Point p = (Point) o;
				return p.x == this.x && p.y == this.y;
			}
			
			return false;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(this.x, this.y);
		}
	}
	
	static class Info {
		int m; // 질량
		int s; // 방향 
		int d; // 속력 
		
		public Info(int m, int s, int d) {
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
}

