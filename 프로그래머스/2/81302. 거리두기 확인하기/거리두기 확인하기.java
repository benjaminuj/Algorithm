class Solution {
    static int[][] dr = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    boolean done = false;
    int result = 1;
    
    public int[] solution(String[][] places) {
        boolean[][] visited = new boolean[5][5];
        int[] answer = new int[5];
        
        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                String line = places[i][j];
                result = 1;
                done = false;
                
                for (int t = 0; t < line.length(); t++) {
                    if (line.charAt(t) == 'P') {
                        visited = new boolean[5][5];
                        int[] origin = {j, t};
                        
                        dfs(j, t, 0, visited, places[i], origin);
                        if (result == 0) break;
                    }
                }
                answer[i] = result;
                if (result == 0) break;
            }
        }
        return answer;
    }
    
    public void dfs(int r, int c, int cnt, boolean[][] visited, String[] place, int[] origin) {
        visited[r][c] = true; // !주의: dfs는 어떤 다른 작업전에 들어오자마자 방문 체크!!!
        
        if (cnt == 2) {
            if (place[r].charAt(c) == 'P') {
                int rDiff = Math.abs(origin[0] - r);
                int cDiff = Math.abs(origin[1] - c);
                
                // 대각선 위치 
                if (rDiff != 0 && cDiff != 0) {
                    // System.out.println("origin r :" + origin[0] + ", c :" + origin[1]);
                    // System.out.println("r: " + r + ", c:" + c);
                    // boolean ch = place[r].charAt(origin[1]) != 'X';
                    // System.out.println("place[r].charAt(origin[1]) : " + place[r].charAt(origin[1]));
                    // System.out.println("place[r].charAt(origin[1]) != 'X' : " + ch );
                    if ((place[origin[0]].charAt(c) != 'X') || (place[r].charAt(origin[1]) != 'X')) {
                        result = 0;
                    }
                } else { // 직선거리에 위치 
                    if (rDiff != 0) {
                        if (r > origin[0]) {
                            if (place[r-1].charAt(c) != 'X') result = 0;
                        } else {
                            if (place[r+1].charAt(c) != 'X') result = 0;
                        }
                    } 
                    
                    if (cDiff != 0) {
                        if (c > origin[1]) {
                            if (place[r].charAt(c-1) != 'X') result = 0;
                        } else {
                            if (place[r].charAt(c+1) != 'X') result = 0;
                        }
                    }
                }
            }
            return;
        }
        
        if (cnt == 1 && place[r].charAt(c) == 'P') {
            done = true;
            result = 0;
            return;
        }
        
        
        for (int[] d : dr) {
            int nr = r + d[0];
            int nc = c + d[1];
            
            if (isRange(nr, nc) && !visited[nr][nc]) {
                dfs(nr, nc, cnt + 1, visited, place, origin);
            }
            
            if (done) return;
        }
    }
        
    public boolean isRange(int r, int c) {
        return r >= 0 && c >= 0 && r < 5 && c < 5; 

    }
}