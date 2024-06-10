import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        Arrays.sort(rocks);
        
        long l = 0, r = distance;
        while (l <= r) {
            long mid = (l+r) / 2;
            int rmCnt = getResult(rocks, mid, distance);
            
            if (rmCnt > n) r = mid -1;
            else if (rmCnt <= n) {
                l = mid + 1;
                answer = answer < (int) mid ? (int)mid : answer; 
            }

        }
        
        return answer;
    }
    
    public int getResult(int[] rocks, long mid, int distance) {
        int prev = 0;
        int rmCnt = 0;
        
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] - prev < mid) rmCnt++;
            else prev = rocks[i];
        }
        
        if (distance - prev < mid) {
            rmCnt++;
        }
        
        return rmCnt;
    }
}