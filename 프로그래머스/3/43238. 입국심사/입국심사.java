import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long r = times[times.length-1]*(long)n;
        
        long l = 1;
        
        while(l <= r) {
            long mid = (l + r) /2;
            long can = 0;
            for (int i = 0; i < times.length; i++) {
                can += mid/times[i];   
            }

            if (can >= n) {
                answer = mid; 
                r = mid-1;
            }
            if (can < n) {
                l = mid+1; 
            }
        }

        return answer;
    }
}