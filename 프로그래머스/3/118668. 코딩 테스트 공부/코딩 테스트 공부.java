import java.util.*;

class Solution {
    int INF = 100000;
    int[][] dp = new int[151][151]; // i번째 알고력,코딩력에 도달하기 위한 최소 시간

    int maxAlp = 0;
    int maxCop = 0;
    
    public int solution(int alp, int cop, int[][] problems) {        
        // 초기값 세팅
        for (int i =0 ; i < 151; i++) {
            Arrays.fill(dp[i], INF);
        }
        for (int i = 0; i <= alp; i++) {
            for (int j = 0; j <= cop; j++) {
                dp[i][j] = 0;
            }
        }
        
        // 알고력과 코딩력 최대값 구하기
        for(int i = 0; i < problems.length; i++) {
            if (maxAlp < problems[i][0]) {
                maxAlp = problems[i][0];
            }
            if (maxCop < problems[i][1]) {
                maxCop = problems[i][1];
            }
        }
        
        // ***
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        for (int i = alp; i < maxAlp+1; i++) { // 0 ~ 150 x
            for (int j = cop; j < maxCop+1; j++) {
                // 문제 안풀고 파워업
                if (j+1 <= 150) {
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                }
                if (i+1 <= 150) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                }

                // 문제풀어서 파워업
                for(int k = 0; k < problems.length; k++) {
                    // 풀 수 있는 문제
                    if (i >= problems[k][0] && j >= problems[k][1]) {
                        int alpRwd = problems[k][2];
                        int copRwd = problems[k][3];
                        
                        int newAlp = Math.min(i + alpRwd, maxAlp);
                        int newCop = Math.min(j+copRwd, maxCop);

                        dp[newAlp][newCop] = Math.min(dp[i][j] + problems[k][4], dp[newAlp][newCop]);
                    
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}