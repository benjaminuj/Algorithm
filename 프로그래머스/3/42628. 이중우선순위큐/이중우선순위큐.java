import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        Queue<Integer> minQ = new PriorityQueue<>();
        Queue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String str : operations) {
            char c = str.charAt(0);
            switch(c) {
                case 'I' : 
                    minQ.offer(Integer.parseInt(str.substring(2, str.length()))); 
                    maxQ.offer(Integer.parseInt(str.substring(2, str.length()))); 
                    break;
                    
                case 'D' :
                    if (minQ.size() == 0 && maxQ.size() == 0) break;
                    
                    char i = str.charAt(2);
                    if (i == '1') {
                        minQ.remove(maxQ.remove());
                    }
                    if (i == '-') {
                        maxQ.remove(minQ.remove());
                    }
                    break;
            }
        }

        if (minQ.size() != 0 || maxQ.size() != 0) {
            answer[0] = maxQ.remove();
            answer[1] = minQ.remove();
        }
        
        return answer;
    }
}