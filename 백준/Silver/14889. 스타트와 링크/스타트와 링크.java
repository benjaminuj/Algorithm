import java.util.*;
import java.io.*;

public class Main {
	
	public static class Score {
		int start =0;
		int link =0;
		public Score(int start, int link) {
			this.start = start;
			this.link = link;
		}
	}
	
	public static String[][] power;
	public static boolean[] visited;
	public static int[][] scores;
	public static int startScore, linkScore;
	public static List<Score> scoreList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		power = new String[N][N];
		visited = new boolean[N];
		scores = new int[N][2];
		int answer = Integer.MAX_VALUE;
		for(int i=0; i<N; i++) {
			power[i] = br.readLine().split(" ");
		}
		
		comb(N, visited, 0, N/2);
		
		for(int i=0; i<scoreList.size(); i++) {
			answer = Math.min(Math.abs(scoreList.get(i).start - scoreList.get(i).link), answer);
		}
		System.out.println(answer);
		
	}
	
	public static void comb(int N, boolean[] visited, int index, int r) {
		if(r==0) {
			startScore=0;
			linkScore =0;
			for(int i=0; i<N; i++) {
				if(visited[i]) {
					for(int j=i+1; j<N; j++) {
						if(visited[j]) {
							startScore += Integer.parseInt(power[i][j]) + Integer.parseInt(power[j][i]);
						} 
					}
				}
				
				if(!visited[i]) {
					for(int j=i+1; j<N; j++) {
						if(!visited[j]) {
							linkScore += Integer.parseInt(power[i][j]) + Integer.parseInt(power[j][i]);
						} 
					}
				}
			}
			scoreList.add(new Score(startScore, linkScore));
			return;
		}
		if(index == N) return;
		
		visited[index] = true;
		comb(N, visited, index+1, r-1);
		
		visited[index] = false;
		comb(N, visited, index+1, r);
	}
}