import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] allSum = new int[triangle.length][triangle[triangle.length-1].length];
        
        allSum[0][0] = triangle[0][0];
        
        for (int i = 1; i < triangle.length; i++) {
            for (int k = 0; k < triangle[i].length; k++) {
                if (k-1 < 0) {
                    allSum[i][k] = allSum[i-1][k] + triangle[i][k];
                    continue;
                }
                
                if (k == triangle[i].length-1) {
                    allSum[i][k] = allSum[i-1][k-1] + triangle[i][k];
                    continue;
                }
                
                int sum1 = allSum[i-1][k-1] + triangle[i][k];
                int sum2 = allSum[i-1][k] + triangle[i][k];  
                
                if (sum1 > sum2) {
                    allSum[i][k] = sum1;
                    continue;
                }
                allSum[i][k] = sum2; 
            }       
        }
        
        answer = Arrays.stream(allSum[triangle.length-1]).max().getAsInt();
        return answer;
    }
}