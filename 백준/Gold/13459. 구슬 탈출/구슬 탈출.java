
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        final int N = Integer.parseInt(line[0]);
        final int M = Integer.parseInt(line[1]);
        char[][] board = new char[N][M];

				// 빨간 구슬과 파란 구슬의 시작 위치를 구한다.
        int srr = 0, src = 0, sbr = 0, sbc = 0;
        for (int r = 0; r < N; r++) {
            char[] chars = reader.readLine().toCharArray();
            for (int c = 0; c < M; c++) {
                board[r][c] = chars[c];
                switch (board[r][c]) {
                    case 'R': srr = r; src = c; break;
                    case 'B': sbr = r; sbc = c; break;
                }
            }
        }

				// 큐에는 빨간 구슬의 위치, 파란 구슬의 위치, 움직인 횟수를 저장한다.
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{ srr, src, sbr, sbc, 0 });

        final int[] dr = {  0, -1,  0,  1 };
        final int[] dc = { -1,  0,  1,  0 };
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
						// 빨간 구슬 위치(rr, rc), 파란 구슬 위치(br, bc), 움직인 횟수(cnt)
            int rr = cur[0], rc = cur[1], br = cur[2], bc = cur[3], cnt = cur[4];
            if (cnt == 10) continue;

						// 두 구슬을 움직일 방향으로 벽에 닿을 때까지 움직인다.
            for (int d = 0; d < 4; d++) {
                int nrr = rr + dr[d], nrc = rc + dc[d];
                int redMove = 0;
                boolean redOut = false;
                while (board[nrr][nrc] != '#') {
                    redMove++;
                    if (board[nrr][nrc] == 'O') {
                        nrr += dr[d];
                        nrc += dc[d];
                        redOut = true;
                        break;
                    }
                    nrr += dr[d];
                    nrc += dc[d];
                }

                int nbr = br + dr[d], nbc = bc + dc[d];
                int blueMove = 0;
                boolean blueOut = false;
                while (board[nbr][nbc] != '#') {
                    blueMove++;
                    if (board[nbr][nbc] == 'O') {
                        nbr += dr[d];
                        nbc += dc[d];
                        blueOut = true;
                        break;
                    }
                    nbr += dr[d];
                    nbc += dc[d];
                }

								// 움직인 방향이 수직 방향이고 두 구슬의 위치가 같다면,
								// 이동한 거리에 따라 더 많이 이동한 구슬을 한칸 뒤로 물린다.
                if (dc[d] == 0 && nrc == nbc && nrr == nbr) {
                    if (redMove > blueMove) {
                        nrr -= dr[d];
                    } else {
                        nbr -= dr[d];
                    }
                }
								// 수평 방향일 때도 마찬가지이다.
                if (dr[d] == 0 && nrr == nbr && nrc == nbc) {
                    if (redMove > blueMove) {
                        nrc -= dc[d];
                    } else {
                        nbc -= dc[d];
                    }
                }

								// while문을 끝냈을 때 구슬의 위치가 벽과 겹치게 되므로 한칸씩 뒤로 물린다.
                nrr -= dr[d]; nrc -= dc[d]; nbr -= dr[d]; nbc -= dc[d];
								// 움직인 후 위치가 변하지 않았다면 다음 탐색을 하지 않는다.
                if (rr == nrr && rc == nrc && br == nbr && bc == nbc) continue;

								// 빨간 구슬만 나가고 파란 구슬은 나가지 않았을 때 1을 출력
                if (redOut && !blueOut) {
                    System.out.println(1);
                    return;
                }
								// 파란 구슬이 나가지 않았을 때만 다음 탐색을 이어간다.
                if (!blueOut) {
                    queue.add(new int[]{ nrr, nrc, nbr, nbc, cnt + 1 });
                }
            }
        }
        System.out.println(0);
    }
}