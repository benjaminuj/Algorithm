import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        Map<String, Integer> count = new HashMap<>();
        
        for(int i=0; i<clothes.length; i++) {
            count.put(clothes[i][1], count.getOrDefault(clothes[i][1], 1) +1);
        }
        long temp = 1;
        
        for(Integer cnt : count.values()) {    
            temp *= cnt; 
        }
        answer = (int) (temp-1);
        return answer;
    }
}