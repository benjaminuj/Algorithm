import java.util.*;

class Solution {
    public int[] solution(int[] emergency) {
        int[] answer = new int[emergency.length];
        Integer[] temp = new Integer[emergency.length];
        for(int i=0; i<emergency.length; i++) {
            temp[i] = emergency[i];
        }
        List<Integer> list = Arrays.asList(temp);
        Arrays.sort(emergency);
        for(int i=0; i < temp.length; i++) {
            int index = list.indexOf(emergency[i]);
            answer[index] = temp.length-i;
        }
        return answer;
    }
}