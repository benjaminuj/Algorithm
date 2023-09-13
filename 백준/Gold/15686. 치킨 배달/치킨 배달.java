import java.util.*;
import java.io.*;

public class Main {
	
	public static class Location {
		int x;
		int y;
		public Location(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static String[][] city;
	static List<Location> chicken;
	static List<Location> home;
	static boolean[] isChoice;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		city = new String[N][N];
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			city[i] = temp.split(" ");
			for(int j=0;j<N; j++) {
				if(city[i][j].equals("1")) {
					home.add(new Location(i,j));
				}
				if(city[i][j].equals("2")) {
					chicken.add(new Location(i,j));
				}
			}
		}
		isChoice = new boolean[chicken.size()];
		
		comb(chicken.size(), M, isChoice, 0);
		System.out.print(answer);
	
	}
	
	public static void comb(int n, int r, boolean[] visited, int index) {
		if(r ==0) {
			int sumOfDistance = 0;
			for(int i=0; i<home.size(); i++) {
				int dis = getDistanceForOneHome(home.get(i).x, home.get(i).y);
				sumOfDistance += dis;
			}
			if(answer > sumOfDistance) answer= sumOfDistance;
			return;
			
		} else if(index == n) {
			return;
		}
		else {
			visited[index] = true;
			comb(n, r-1, visited, index+1);
			
			visited[index] =false;
			comb(n ,r, visited, index+1);
		}
	}
	
	public static int getDistanceForOneHome(int homeX, int homeY) {
		int distance = Integer.MAX_VALUE;
		for(int i=0; i<isChoice.length; i++) {
			if(isChoice[i]) {
				 int temp = Math.abs(homeX - chicken.get(i).x) + Math.abs(homeY - chicken.get(i).y);
				 if(distance > temp) distance = temp;
			}
		}
		return distance;
	}

}