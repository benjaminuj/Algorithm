import java.util.*;

class Solution {
    public int solution(int[][] lines) {
        int answer = 0;
        int[] count = new int[200];
        for(int[] line : lines) {
            int a = line[0]+100;
            int b = line[1]+100;
            while(a<b) {
                if(++count[a++] == 2) answer++;
            }
        }
        return answer;
    }
}