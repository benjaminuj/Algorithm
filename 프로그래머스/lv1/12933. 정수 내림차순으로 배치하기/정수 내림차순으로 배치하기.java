import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        String str = "" + n;
        Integer[] arr = new Integer[str.length()];
        int index =0;
        while(n>0) {
            arr[index] = (int) (n%10);
            n /= 10;
            index++;
        }
        Arrays.sort(arr, Collections.reverseOrder());
        str ="";
        for(int i=0; i<arr.length; i++) {
            str += arr[i];
        }
        answer = Long.parseLong(str);
        return answer;
    }
}