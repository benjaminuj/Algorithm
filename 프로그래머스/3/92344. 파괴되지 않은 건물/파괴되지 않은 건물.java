class Solution {
    static int N, M;
    static int ENEMY = 1;
    static int ALLEY = 2;
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
    
        N = board.length;
        M = board[0].length;
        
        int[][] sum = new int[N+1][M+1];
        for (int i = 0; i < skill.length; i++) {
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            // r1, c1 : +degree
            // r1, c2+1 : -degree
            // r2+1, c1 : -degree
            // r2+1, c2+1 : +degree
            
            // 누적합 만들기 
            if (skill[i][0] == ENEMY) {
                sum[r1][c1] -= degree;
                sum[r1][c2+1] -= -degree;
                sum[r2+1][c1] -= -degree;
                sum[r2+1][c2+1] -= degree;
            }
            
            if (skill[i][0] == ALLEY) {
                sum[r1][c1] += degree;
                sum[r1][c2+1] += -degree;
                sum[r2+1][c1] += -degree;
                sum[r2+1][c2+1] += degree;
            }
        }
        
        // 왼 -> 오
        for (int r = 0; r < N+1; r++) {
            for (int c = 1; c < M+1; c++) {
                sum[r][c] += sum[r][c-1];
            }
        }
        // 위 -> 아래
        for (int c = 0; c < M+1; c++) {
            for (int r = 1; r < N+1; r++) {
                sum[r][c] += sum[r-1][c];
            }
        }

        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] + sum[r][c] >= 1) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}