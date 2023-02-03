import java.util.Arrays;

class Solution {
    public int[] solution(String my_string) {
        char[] charArr = my_string.replaceAll("[a-z]","").toCharArray();
        int[] answer = new int[charArr.length];
        for(int i=0; i<charArr.length; i++) {
            answer[i] = (int)charArr[i] -'0';
        }
        Arrays.sort(answer);
        return answer;
    }
}