import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        String answerString ="";
        for(int i=0; i<arr.length; i++) {
            if(arr[i] %divisor ==0) answerString += arr[i] + ",";
        }
        if(answerString.equals("")) {
            int[] answer = new int[1];
            answer[0] = -1;
            return answer;
        }
        String[] temp = answerString.split(",");
        int[] answer = new int[temp.length];
        for(int i=0; i<temp.length; i++) {
            answer[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(answer);
        return answer;
    }
}