import java.util.*;

class Solution {
    static final int AVAILABLE = 1;
    static final int EMPTY = 0;
    int[] dx = {-1, 1, 0, 0}; // 상하좌우
    int[] dy = {0, 0, -1, 1}; // 상하좌우

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        int ar = aloc[0], ac = aloc[1];
        int br = bloc[0], bc = bloc[1];
        Result result = play(board, ar, ac, br, bc);
        return result.movedCnt;
    }

    // 현재 플레이어 좌표: r1, c1
    // 상대 플레이어 좌표: r2, c2
    private Result play(int[][] board, int r1, int c1, int r2, int c2) {
        if (board[r1][c1] == EMPTY) {
            return new Result(0, false);
        }

        board[r1][c1] = EMPTY; // 현재 위치 빈칸으로 변경
        int R = board.length;
        int C = board[0].length;
        int minWin = Integer.MAX_VALUE;
        int maxLose = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r1 + dx[i];
            int nc = c1 + dy[i];
            
            // 범위를 벗어나지 않고 다음 위치가 AVAILABLE이면
            if (0 <= nr && nr < R && 0 <= nc && nc < C && board[nr][nc] == AVAILABLE) {
                Result result = play(board, r2, c2, nr, nc); // 이 play 호출은 상대 관점. 즉, 이 호출의 결과가 true이면 나는 지는 것(false)
                boolean meWin = !result.win;
                if (meWin) {
                    minWin = Math.min(minWin, result.movedCnt + 1);
                } else {
                    maxLose = Math.max(maxLose, result.movedCnt + 1);
                }
            }
        }

        board[r1][c1] = AVAILABLE; // 다시 원상태로 돌리기
        
        // 내가 이길 수 있는 경우가 있으면 
        if (minWin != Integer.MAX_VALUE) {
            return new Result(minWin, true);
        } else {
            return new Result(maxLose, false);
        }
    }
    
    public class Result {
        int movedCnt;
        boolean win;
        
        public Result(int movedCnt, boolean win) {
            this.movedCnt = movedCnt;
            this.win = win;
        }
    }
}
