import java.util.Arrays;
class Solution {
    public int solution(int[] array, int n) {
        int answer = 0;
        Arrays.sort(array);
        for(int i : array) {
            if(Math.abs(i-n) < Math.abs(answer-n)) answer = i;
        }
        return answer;
    }
}