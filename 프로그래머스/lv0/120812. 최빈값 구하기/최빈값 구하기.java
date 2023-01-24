import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int solution(int[] array) {
        int answer = 0;
        boolean check = true;
        int max =0;
        int maxValue = 1;
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<array.length; i++) {
            list.add(array[i]);
        }
        while(list.size() > 0) {
            int count = Collections.frequency(list, list.get(0));
            if(max == count) {
                check = false;
            }
            if(max < count) {
                max = count;
                maxValue = list.get(0);
                check = true;
            }
            list.removeIf(item -> item == list.get(0));
        }
        if(check) answer = maxValue;
        else answer = -1;
        return answer;
    }
}