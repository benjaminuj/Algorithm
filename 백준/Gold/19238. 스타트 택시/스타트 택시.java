import java.io.*;
import java.util.*;

public class Main {
	static int N, M, fuel;
	static int driveR, driveC; // 차 출발 행과 열  
	static int[][] dr =  {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
	static HashMap<String, String> customer = new HashMap<>();; // 손님 출발지(key) 목적지(value) 정보 담음 
	static int answer = -1;
	static boolean withCustomer = false;
	static boolean[][] visited;
	static boolean[][] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		boolean[][] wall = new boolean[N+1][N+1];
		
		visited = new boolean[N][N];
		selected = new boolean[N][N];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				wall[i][j] = (st.nextToken().equals("1")); // boolean타입인데, 1이면 뭐가 들어가지?
			}
		}
		st = new StringTokenizer(br.readLine());
		Point taxi = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Map<Point, Passenger> passengers = new HashMap<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			Point from = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Point to = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			passengers.put(from, new Passenger(from, to));
		}
		
		Queue<Entry> queue = new ArrayDeque<>();
		queue.add(new Entry(taxi, 0));
		
		for (int i =0 ; i<M; i++) {
			Passenger passenger = null;
			
			Point initialPoint = queue.peek().p;
			
			if (passengers.containsKey(initialPoint)) {
				passenger = passengers.get(initialPoint);
				queue.clear();
			}
			
			int usage = 0;
			boolean[][] visited = prepareVisited(wall);
			
			while(!queue.isEmpty()) {
				Entry cur = queue.remove();
				
				if (cur.dist == fuel) break; // 이해 x
				if (passenger != null && usage <= cur.dist) break;
				
				for (int d = 0; d <4;d++) {
					Point next = new Point(cur.p.r + dr[d][0], cur.p.c + dr[d][1]);
					if (next.inRange(N, N) && !visited[next.r][next.c]) {
						visited[next.r][next.c] = true;
						
						if (passengers.containsKey(next)) {
							Passenger newPassenger = passengers.get(next);
							
							if (passenger == null) {
								passenger = newPassenger;
								usage = cur.dist+1;
							} else if (newPassenger.from.compareTo(passenger.from) < 0 && usage >= cur.dist + 1) { // == 이 아니라 >= 아닌가?
								passenger = newPassenger;
							}
						} else {
							queue.add(new Entry(next, cur.dist + 1));
						}
					}
				}
			}
			
			if (passenger == null) {
				System.out.println(-1); // "-1"문자열로 안해도 되나?
				return;
			}
			
			fuel -= usage;
			
			usage = 0;
			queue.clear();
			queue.add(new Entry(passenger.from, 0));
			boolean arrived = false;
			visited = prepareVisited(wall);
			
			outer:
			while(!queue.isEmpty()) {
				Entry cur = queue.remove();
				if (cur.dist == fuel) break; // 이거 진짜 왜 지워? 현재 얼마나 이동했는지랑 남은 연료량이 같으면 왜 끝내?
				
				for (int d = 0; d < 4; d++) {
					Point next = new Point(cur.p.r + dr[d][0] , cur.p.c + dr[d][1]);
					
					if (next.inRange(N,  N) && !visited[next.r][next.c]) {
						visited[next.r][next.c] = true;
						
						if (passenger.to.equals(next)) {
							arrived = true;
							usage = cur.dist+1;
							break outer;
						} else {
							queue.add(new Entry(next, cur.dist +1));
						}
					}
				}
			}
			
			if (arrived) {
				fuel += usage; // 도착하면 2배 더해야하지 않나?
				queue.clear();
				queue.add(new Entry(passenger.to, 0));
				passengers.remove(passenger.from);
			} else {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(fuel);
	}
	
	static boolean[][] prepareVisited(boolean[][] wall) {
		boolean[][] result = new boolean[wall.length][wall.length];
		for (int r =0; r < wall.length; r++) {
			System.arraycopy(wall[r], 0, result[r] , 0, result[r].length);
		}
		return result;
	}
	
	static class Entry {
		Point p;
		int dist;
		
		public Entry(Point p, int dist) {
			this.p = p;
			this.dist = dist;
		}
		
		@Override
		public String toString() {
			return "E" + p + ":" + dist;
		}
	}
	
	static class Passenger {
		static int seq = 1;
		int id;
		Point from;
		Point to;
		
		public Passenger(Point from, Point to) {
			this.id = seq++;
			this.from = from;
			this.to = to;
		}
		
		@Override
		public String toString() {
			return "P" + id + ":" + from + "->" + to;
		}
	}
	
	static class Point implements Comparable<Point> {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public boolean inRange(int n, int m) {
			return r > 0 && r <= n&& c > 0 && c <=m;
		}
		
		@Override
		public String toString() {
			return "(" + r + "," + c + ")";
		}
		
		@Override
		public boolean equals(Object o) {
			if (o instanceof Point) {
				Point p = (Point)o;
				return this.r == p.r && this.c == p.c;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(this.r ,this.c);
		}
		
		@Override
		public int compareTo(Point p) {
			if (this.r == p.r) {
				return this.c - p.c;
			} else {
				return this.r - p.r;
			}
		}
	}
}

