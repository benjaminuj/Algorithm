import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int answer = binarySearch(distance,rocks,n);
        return answer;
    }
    private static final int MAX =  1_000_000_000;
    private static int binarySearch(int distance, int[] rocks, int n){
        int start = 0;
        int end = MAX;
        int result = 0;
        while(start <= end){
            int mid = (start + end) / 2;
            if(cal(mid,distance,rocks,n)){
                start = mid + 1;
                result = mid;
            }else{
                end = mid - 1;
            }
        }
        return result;
    }
    private static boolean cal(int mid, int distance , int[] rocks, int n){
        int prev = 0;
        int cnt = 0;
        for(int rock : rocks){
            if(rock - prev < mid){
                cnt++;
            }else{
                prev = rock;
            }
        }
        if(distance - prev < mid){
            cnt++;
        }
        if(cnt <= n){
            return true;
        }else{
            return false;
        }
    }
}