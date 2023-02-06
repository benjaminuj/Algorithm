import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1; i<=Math.sqrt(n); i++) {
            if(n%i == 0) {
                list.add(i);
                if(i != (n/i)) list.add(n/i);
            }
        }
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}