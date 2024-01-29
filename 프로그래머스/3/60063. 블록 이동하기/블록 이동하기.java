import java.util.*;

class Solution {
    int N;
    int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // 하 상 우 좌
    boolean[][][] visited;
    int answer;
    
    public int solution(int[][] board) {
        N = board.length;
        visited = new boolean[2][N][N]; 
        
        bfs(board);
        return answer;
    }
    
    public void bfs(int[][] board) {
        Queue<Info> q = new ArrayDeque<>();
        q.offer(new Info(0,0, 0,1, 0, 0));
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        
        while (!q.isEmpty()) {
            Info cur = q.poll();
            
            if ((cur.r1 == N-1 && cur.c1 == N-1) || (cur.r2 == N-1 && cur.c2 == N-1)) {
                answer = cur.cnt;
                return;
            }
            
            for (int i =0 ; i < 4; i++) {
                int nR1 = cur.r1 + dr[i][0];
                int nC1 = cur.c1 + dr[i][1];
                int nR2 = cur.r2 + dr[i][0];
                int nC2 = cur.c2 + dr[i][1];
                
                if (isRange(nR1, nC1, nR2, nC2) && board[nR1][nC1] != 1 && board[nR2][nC2] != 1) {
                    if (!visited[cur.type][nR1][nC1] || !visited[cur.type][nR2][nC2]) {
                        visited[cur.type][nR1][nC1] = true;
                        visited[cur.type][nR2][nC2] = true;
                        q.offer(new Info(nR1, nC1, nR2, nC2, cur.type, cur.cnt+1));
                    }
                    
                    int changeType = (cur.type == 0 ? 1 : 0);
                    // 가로일 때 세로 방향으로 회전
                    if ((cur.type == 0 && i < 2) || (cur.type == 1 && i > 1)) {
                        if (!visited[changeType][nR1][nC1] || !visited[changeType][cur.r1][cur.c1]) {
                            visited[changeType][nR1][nC1] = true;
                            visited[changeType][cur.r1][cur.c1] = true;
                            q.offer(new Info(nR1, nC1, cur.r1, cur.c1 , changeType, cur.cnt+1));
                        }
                        
                        if (!visited[changeType][nR2][nC2] || !visited[changeType][cur.r2][cur.c2]) {
                            visited[changeType][nR2][nC2] = true;
                            visited[changeType][cur.r2][cur.c2] = true;
                            q.offer(new Info(nR2, nC2, cur.r2, cur.c2, changeType, cur.cnt+1));
                        }
                    }
                }
            }
        }
    }
    
    boolean isRange(int r1, int c1, int r2, int c2) {
        return r1 >= 0 && r1 < N && c1 >= 0 && c1 < N && r2 >= 0 && r2 < N && c2 >= 0 && c2 < N;
    }
    
    class Info {
        int r1, c1;
        int r2 ,c2;
        int type; // 방향 (0 = 가로, 1 = 세로)
        int cnt;
        
        Info(int r1, int c1, int r2, int c2, int type, int cnt) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.type = type;
            this.cnt = cnt;
        }
    }
}