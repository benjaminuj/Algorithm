import java.util.Arrays;
import java.util.Collections;

class Solution {
    public String solution(String s) {
        String answer = "";
        Integer[] ascii = new Integer[s.length()];
        for(int i=0; i<s.length(); i++) {
            ascii[i] = (int)s.charAt(i);
        }
        Arrays.sort(ascii, Collections.reverseOrder());
        for(int i=0; i<ascii.length; i++) {
            answer += (char)(int)ascii[i];
        }
        
        return answer;
    }
}