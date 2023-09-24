import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        Map<String, Integer> count = new HashMap<>();
        
        for(int i=0; i<clothes.length; i++) {
            count.put(clothes[i][1], count.getOrDefault(clothes[i][1], 0) +1);
        }
        
        //DP
        Integer[] cnt = new Integer[count.size()];

        count.values().toArray(cnt);

        long[][] dp = new long[count.size()][2];
        dp[0][0] = 1;
        dp[0][1] = cnt[0];
        
        for(int i=1;i <dp.length; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0]*cnt[i] + dp[i-1][1]*cnt[i];
        }
        
        answer = (int) (dp[dp.length-1][0] + dp[dp.length-1][1] -1);
    
        return answer;
    }
}