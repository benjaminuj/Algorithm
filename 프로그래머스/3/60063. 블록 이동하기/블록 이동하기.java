import java.util.*;

class Solution {
    static int[][] dr = {{-1, 0}, {1, 0}, {0, 1},  {0, -1}}; // 상 하 우 좌
    boolean[][][] visited;
    int N;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        visited = new boolean[2][N][N];
        
        return bfs(board);
    }
                       
    public int bfs(int[][] board) {
        Queue<Info> q = new ArrayDeque<>();
        q.add(new Info(0, 0, 0, 1, 0, 0));
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        
        while (!q.isEmpty()) {
            Info cur = q.poll();
            if ((cur.x1 == N-1 && cur.y1 == N-1) || (cur.x2 == N-1 && cur.y2 == N-1)) {
                answer = Math.min(answer, cur.time);
            }
            
            for (int i = 0; i < 4; i ++) {
                // 수평 이동
                int nx1 = cur.x1 + dr[i][0];
                int ny1 = cur.y1 + dr[i][1];
                int nx2 = cur.x2 + dr[i][0];
                int ny2 = cur.y2 + dr[i][1];
                
                if (isValid(nx1, ny1, nx2, ny2, board)) {
                    if (!visited[cur.dir][nx1][ny1] || !visited[cur.dir][nx2][ny2]) {
                        q.add(new Info(nx1, ny1, nx2, ny2, cur.dir, cur.time+1));
                        visited[cur.dir][nx1][ny1] = true;
                        visited[cur.dir][nx2][ny2] = true;
                    }
                    
                    // 회전 이동
                    if ((cur.dir == 0 && (i == 0 || i == 1)) || (cur.dir == 1 && (i == 2 || i == 3))) {
                        if (!visited[(cur.dir + 1)%2][nx2][ny2] || !visited[(cur.dir + 1)%2][cur.x2][cur.y2]) {
                            q.add(new Info(nx2, ny2, cur.x2, cur.y2, (cur.dir + 1)%2, cur.time+1));
                            visited[(cur.dir + 1)%2][nx2][ny2] = true;
                            visited[(cur.dir + 1)%2][cur.x2][cur.y2] = true;
                        }
                        
                        if (!visited[(cur.dir + 1)%2][nx1][ny1] || !visited[(cur.dir + 1)%2][cur.x1][cur.y1]) {
                            q.add(new Info(nx1, ny1, cur.x1, cur.y1, (cur.dir + 1)%2, cur.time+1));
                            visited[(cur.dir + 1)%2][nx1][ny1] = true;
                            visited[(cur.dir + 1)%2][cur.x1][cur.y1] = true;
                        }
                    }
                }
            }       
        }
        return answer;
    }
    
    public boolean isValid(int x1, int y1, int x2, int y2, int[][] board) {
        return x1 >= 0 && y1 >= 0 && x2 >= 0 && y2 >= 0 && x1 < N && y1 < N && x2 < N && y2 < N
            && board[x1][y1] == 0 && board[x2][y2] == 0;
    }
                       
    class Info {
        int x1, y1;
        int x2, y2;
        int dir; // 0 : 가로, 1 : 세로
        int time;
        
        public Info(int x1, int y1, int x2, int y2, int dir, int time) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.dir = dir;
            this.time = time;
        }
    }
}