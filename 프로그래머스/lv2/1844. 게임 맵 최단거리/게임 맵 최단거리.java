import java.util.*;

class Solution {
    static int[] dy = {0,1,0,-1};
    static int[] dx = {1,0,-1,0};
    static boolean[][] visited;
    
    public class Info {
        int x;
        int y;
        int depth;
        public Info(int x, int y,int depth) {
            this.x =x;
            this.y= y;
            this.depth = depth;
        }
    }
    
    public int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length];
    
        return bfs(maps.length, maps[0].length, maps);
    }
    
    public int bfs(int n, int m, int[][] maps) {
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(0,0,1));
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            Info here = q.poll();
            for(int i=0; i<4; i++) {
                int nextX = here.x + dx[i];
                int nextY = here.y + dy[i];
                
                if(nextX >= 0 && nextY < n && nextY >= 0 && nextX < m) {
                    if(maps[nextY][nextX] == 0) continue;
                    
                    if(nextY == n-1 && nextX == m-1) return here.depth+1;
        
                    if(!visited[nextY][nextX]) {
                        q.add(new Info(nextX, nextY, here.depth+1));
                        visited[nextY][nextX] = true;
                    }
                }
            }  
        }
        return -1;
    }
}