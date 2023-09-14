import java.util.*;
import java.io.*;

public class Main {
	
	public static class Location {
		int x;
		int y;
		public Location(int y, int x) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static class MoveInfo {
		int time;
		String direc;
		public MoveInfo(int time, String direc) {
			this.time = time;
			this.direc = direc;
		}
	}
	
	public static class Rotation {
		int x;
		int y;
		String direc;
		public Rotation(int x,int y, String direc) {
			this.x =x;
			this.y = y;
			this.direc = direc;
		}
	}
	
	static Location[] appleLocation;
	static Queue<MoveInfo> directLocation = new LinkedList<>();
	static Queue<Rotation> directTailInfo = new LinkedList<>();
	static Location head = new Location(0,0);
	static Location tail = new Location(0,0);
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	static int headDirec, tailDirec, time = 0;
	static boolean isCrush = false;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		visited[0][0] = true;
		int K = Integer.parseInt(br.readLine());
		appleLocation = new Location[K];
		for(int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			appleLocation[i] = new Location(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		}
		int L = Integer.parseInt(br.readLine());
		
		for(int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			directLocation.add(new MoveInfo(Integer.parseInt(st.nextToken()), st.nextToken()));
		}
		
		while(!isCrush) {
			Boolean isBeApple = false;
			time++;
			setHeadDirection();
			moveHead();
			checkCrush(N);
			if(isCrush) {
				System.out.println(time);
				break;
			}
			isBeApple = checkBeAppleForHead();
			if(!isBeApple) {
				setTailDirection();
				moveTail();
			}
		}		
	}
	
	public static void setHeadDirection() {
		if(directLocation.size() == 0) return;  // 예외 처리!!! 
		if(time == directLocation.peek().time+1) {
			String rotationInfo = directLocation.poll().direc;
			
			switch(rotationInfo) {
			case "L" :
				headDirec = (headDirec -1) <0 ? 4+(headDirec -1) : (headDirec -1);
				directTailInfo.add(new Rotation(head.x, head.y, "L"));
				break;
				
			case "D" :
				headDirec = (headDirec+1)%4;
				directTailInfo.add(new Rotation(head.x, head.y, "D"));
				break;
			}
		}
	}
	
	public static void moveHead() {
		head.x += dx[headDirec];
		head.y += dy[headDirec];
	}
	
	public static void checkCrush(int N) {
		// 선 
		if(head.x < 0 || head.x >= N || head.y <0 || head.y >=N) {
			isCrush = true;
			return;
		}
		// 후 
		if(visited[head.y][head.x]) {
			isCrush = true;
			return;
		}	
		visited[head.y][head.x] = true;
	}
	
	public static boolean checkBeAppleForHead() {
		for(int i=0; i<appleLocation.length; i++) {
			if(appleLocation[i].x == head.x && appleLocation[i].y == head.y) {
				appleLocation[i].x = -1;
				appleLocation[i].y = -1;
				return true;
			}
		}
		return false;
	}
	
	public static void setTailDirection() {
		if(directTailInfo.size() ==0) return; // 예외 처리!!! 
		if(directTailInfo.peek().x == tail.x && directTailInfo.peek().y == tail.y) {
			String rotationInfo = directTailInfo.poll().direc;
			
			switch(rotationInfo) {
			case "L" :
				tailDirec = (tailDirec -1) <0 ? 4+(tailDirec -1) : (tailDirec -1);
				break;
				
			case "D" :
				tailDirec = (tailDirec+1)%4;
				break;
			}
		}
	}
	
	public static void moveTail() {
		visited[tail.y][tail.x] = false; //먼저 ! 
		tail.x += dx[tailDirec];
		tail.y += dy[tailDirec];
	}

}
