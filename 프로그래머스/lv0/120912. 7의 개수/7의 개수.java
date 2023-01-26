import java.util.*;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;


class Solution {
    public int solution(int[] array) {
        String[] arr = new String[array.length];
        int answer = 0;
        for(int i=0; i<array.length; i++) {
            arr[i] = String.valueOf(array[i]);
        }
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length(); j++) {
                char alpha = arr[i].charAt(j);
                if(alpha == '7') answer++;
            }
        }
        return answer;
    }
}