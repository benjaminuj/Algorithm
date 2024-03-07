import java.io.*;
import java.util.*;

public class Main {
	static int[][] dr = {{0,1}, {1,0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] line = reader.readLine().split(" ");
		final int N = Integer.parseInt(line[0]);
		final int M = Integer.parseInt(line[1]);
		
		char[][] graph = new char[N][M];
		
        int srr = 0, src = 0, sbr = 0, sbc = 0;
        for (int r = 0; r < N; r++) {
            char[] chars = reader.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                graph[r][c] = chars[c];
                switch (graph[r][c]) {
                    case 'R': srr = r; src = c; break;
                    case 'B': sbr = r; sbc = c; break;
                }
            }
        }
		
        // 빨간 구슬의 위치, 파란 구슬의 위치, 움직인 횟수
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{ srr, src, sbr, sbc, 0 });
		
		while (!q.isEmpty()) {
			int[] cur = q.remove();
			int rr = cur[0];
			int rc = cur[1];
			int br = cur[2];
			int bc = cur[3];
			int cnt = cur[4];
			
			if (cnt == 10) continue;
			
			for (int[] d : dr) {
				int nrr = rr + d[0];
				int nrc = rc + d[1];
				int redMove = 0;
				boolean redOut = false;
				
				while(graph[nrr][nrc] != '#') {
					redMove++;
					
					if (graph[nrr][nrc] == 'O') {
	                    nrr += d[0];
					    nrc += d[1];
						redOut = true;
						break;
					}
					nrr += d[0];
					nrc += d[1];
				}
				
				int nbr = br + d[0];
				int nbc = bc + d[1];
				int blueMove = 0;
				boolean blueOut = false;
				
				while (graph[nbr][nbc] != '#') {
					blueMove++;
		
					if (graph[nbr][nbc] == 'O') {
                        nbr += d[0];
					    nbc += d[1];
						blueOut = true;
						break;
					}
					nbr += d[0];
					nbc += d[1];
					
				}
				
				// 수직 움직임
				if (d[1] == 0 && nrc == nbc && nrr == nbr) {
					if (redMove > blueMove) {
						nrr -= d[0];
					} else {
						nbr -= d[0];
					}
				}
				
				// 수평 움직임
				if (d[0] == 0 && nrc == nbc && nrr == nbr) {
					if (redMove > blueMove) {
						nrc -= d[1];
					} else {
						nbc -= d[1];
					}
				}
				
				nrr -= d[0]; nbr -= d[0]; nrc -= d[1]; nbc -= d[1];

				if (rr == nrr && rc == nrc && br == nbr && bc == nbc) {
					continue;
				}
				
                if (redOut && !blueOut) {
                    System.out.println(1);
                    return;
                }
								
                if (!blueOut) {
                    q.add(new int[]{ nrr, nrc, nbr, nbc, cnt + 1 });
                }
			}
		}
		System.out.println(0);
	}
	
}

