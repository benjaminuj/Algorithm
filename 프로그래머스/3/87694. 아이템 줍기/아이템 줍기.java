import java.util.*;

class Solution {
    static int map[][] = new int[101][101];
    int[][] dr = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int i =0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0];
            int y1 = rectangle[i][1];
            int x2 = rectangle[i][2];
            int y2 = rectangle[i][3];
            
            draw(2*x1, 2*y1, 2*x2, 2*y2);
        }
        
        int result = bfs(2*characterX, 2*characterY, 2*itemX, 2*itemY);
        return result/2;
    }
    
    public int bfs(int fx, int fy, int tx, int ty) {
        boolean[][] visited = new boolean[101][101];
        Queue<Integer[]> q = new ArrayDeque<>();
        
        visited[fx][fy] = true;
        q.add(new Integer[]{fx, fy, 0}); // x, y, cnt
        
        while(!q.isEmpty()) {
            Integer[] now = q.poll();
            
            for (int[] d : dr) {
                int nx = now[0] + d[0];
                int ny = now[1] + d[1];
                
                if (nx == tx && ny == ty) return now[2]+1;
                
                if (nx > 0 && nx <=100 && ny > 0 && ny <= 100) {
                    if (!visited[nx][ny] && map[nx][ny] == 1) {
                        q.add(new Integer[]{nx, ny, now[2]+1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
    
    public void draw(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (map[i][j] == 2) continue;
                map[i][j] = 2;
                if (i == x1 || i == x2 || j == y1 || j == y2) {
                    map[i][j] = 1;
                }
            }
        }
    }
}