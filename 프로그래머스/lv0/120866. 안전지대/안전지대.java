class Solution {
    public int solution(int[][] board) {
        int[] dy = {1,0,-1,0,1,1,-1,-1};
        int[] dx = {0,1,0,-1,1,-1,1,-1};
        
        int len = board.length;
        boolean[][] isDnager = new boolean[len][len];
        int answer = 0;
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                if(board[i][j] ==1) {
                    isDnager[i][j] = true;
                    for(int k=0; k<8; k++) {
                        if(0<=i+dx[k] && i+dx[k]<len && 0<=j+dy[k] && j+dy[k]<len) {
                            int nextX = i+dx[k];
                            int nextY = j+dy[k];
                            isDnager[nextX][nextY] = true;
                        }
                    }
                }
            }
        }
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                if(isDnager[i][j] == false) answer++;
            }
        }
        return answer;
    }
}