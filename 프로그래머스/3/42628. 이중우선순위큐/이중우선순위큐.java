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
                    if (minQ.size() != 0) minQ.offer(Integer.parseInt(str.substring(2, str.length()))); 
                    else if (maxQ.size() != 0) maxQ.offer(Integer.parseInt(str.substring(2, str.length()))); 
                    else minQ.offer(Integer.parseInt(str.substring(2, str.length()))); 
                    
                    break;
                case 'D' :
                    if (minQ.size() == 0 && maxQ.size() == 0) break;
                    char i = str.charAt(2);
                    
                    if (i == '1') {
                        if (minQ.size() != 0) {
                            maxQ.clear();
                            
                            Iterator iter = minQ.iterator();
                            while (iter.hasNext()) {
                                maxQ.offer((Integer)(iter.next()));
                            }
                        }
                        minQ.clear();
                        maxQ.remove();
                    }
                    
                    if (i == '-') {
                        if (maxQ.size() != 0) {
                            minQ.clear();
                            
                            Iterator iter = maxQ.iterator();
                            while (iter.hasNext()) {
                                minQ.offer((Integer)(iter.next()));
                            }
                        }
                        maxQ.clear();
                        minQ.remove();
                    }
                    
                    break;
            }
        }

        if (minQ.size() != 0) {
            answer[1] = minQ.remove();
            
            while (minQ.size() >= 2) {
                minQ.remove();
            }
            answer[0] = minQ.remove();
        }
        
        if (maxQ.size() != 0) {
            answer[0] = maxQ.remove();
            
            while (maxQ.size() >= 2) {
                maxQ.remove();
            }
            answer[1] = maxQ.remove();
        }
        
        return answer;
    }
}