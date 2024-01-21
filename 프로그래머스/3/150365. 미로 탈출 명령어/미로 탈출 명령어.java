import java.util.*;

class Solution {
    int[][] dr = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}}; // 사전순 dlru
    char[] s = {'d', 'l', 'r', 'u'};
    int[][] grid;
    String answer = "impossible";
    boolean find = false;
    int r, c, k;
    StringBuilder st = new StringBuilder();
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        grid = new int[n][m];   
        
        if (r == x && c == y) {
            return answer;
        }
        
        

        if (Math.abs(x-r) + Math.abs(y-c) > k || (k - Math.abs(x-r) + Math.abs(y-c)) % 2 != 0) {
            return answer;
        }
        
        this.r = r-1;
        this.c = c-1;
        this.k = k;
        
        dfs (x-1, y-1, 0);
        
        return answer;
    }
    
    public void dfs(int x, int y, int cnt) {    
        if (find) {
            return;
        }
        
        int remain = Math.abs(x-r) + Math.abs(y-c) + cnt;
        if (remain > k) {
            return;
        }
        
        if (cnt == k) {
            if (x == r && y == c) {
                answer = st.toString();
                find = true;
            } 
            return;
        }
        
        
        for (int i = 0; i < 4; i++) {
            int nX = x + dr[i][0];
            int nY = y + dr[i][1];
            
            if (isValid(nX, nY)) {
                st.append(s[i]);
                dfs(nX, nY, cnt+1);
                st.deleteCharAt(st.length()-1);
            }
        }
    }
    
    public boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < grid.length && c < grid[0].length;
    }
}

