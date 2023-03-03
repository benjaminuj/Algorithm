import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(long n) {
        List<Integer> answerList = new ArrayList<>();
        while(n>0) {
            answerList.add((int)(n%10));
            n /= 10;
        }
        int[] answer = answerList.stream().mapToInt(i->i).toArray();
        return answer;
    }
}