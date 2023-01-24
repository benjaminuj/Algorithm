import java.util.Arrays;
class Solution {
    public int solution(int[] array) {
        int answer = 0;
        Arrays.sort(array);
        int middle = (array.length+1)/2;
        answer = array[middle-1];
        return answer;
    }
}