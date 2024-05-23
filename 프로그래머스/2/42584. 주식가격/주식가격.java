import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peekLast()] > prices[i]) {
                int idx = stack.removeLast();
                
                answer[idx] = i - idx;
            }
            
            stack.add(i);
        }
        
        while (!stack.isEmpty()) {
            int idx = stack.removeLast();
            answer[idx] = answer.length-1 - idx;
        }
      
        return answer;
    }
}