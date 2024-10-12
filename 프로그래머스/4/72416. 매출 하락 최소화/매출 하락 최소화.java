import java.util.*;

class Solution {
    int personCnt;
    List<List<Integer>> tree = new ArrayList<>();
    int[][] dp = new int[300001][2];
        
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        personCnt = sales.length;
        
        for (int i = 0; i <= personCnt; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < links.length; i++) {
            tree.get(links[i][0]).add(links[i][1]);
        }
        
        dfs(sales, 1);
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    public void dfs(int[] sales, int idx) {
        dp[idx][0] = 0;
        dp[idx][1] = sales[idx-1];
        
        if (tree.get(idx).size() == 0) return;
        int diff = Integer.MAX_VALUE;
        for (int child: tree.get(idx)) {
            dfs(sales, child);
            
            if (dp[child][0] < dp[child][1]) {
                dp[idx][0] += dp[child][0];
                dp[idx][1] += dp[child][0];
                
                diff = Math.min(diff, dp[child][1] - dp[child][0]);
            } else if (dp[child][0] >= dp[child][1]) {
                dp[idx][0] += dp[child][1];
                dp[idx][1] += dp[child][1];
                
                diff = 0;
            }
        }
        
        dp[idx][0] += diff;
        return;
    }
}