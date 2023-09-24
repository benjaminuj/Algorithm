import java.util.*;
import java.util.Map.Entry;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> count = new HashMap<>();
        
        for(int i =0; i<participant.length; i++) {
            count.put(participant[i], count.getOrDefault(participant[i], 0) +1);
        }
        
        for(int i=0; i<completion.length; i++) {
            count.put(completion[i], count.get(completion[i])-1);
        }
         
        for(String key : count.keySet()) {
            if(count.get(key) == 1) {
                answer = key;
            }
        }
        return answer;
    }
}