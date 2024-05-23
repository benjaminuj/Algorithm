import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Deque<Info> stack = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && stack.peekLast().price > prices[i]) {
                Info now = stack.removeLast();
                
                answer[now.idx] = i - now.idx;
            }
            
            stack.add(new Info(i, prices[i]));
        }
        
        while (!stack.isEmpty()) {
            Info now = stack.removeLast();
            answer[now.idx] = answer.length-1 - now.idx;
        }
      
        return answer;
    }
    
    class Info {
        int idx;
        int price;
        
        public Info (int idx, int price) {
            this.idx = idx;
            this.price = price;
        }
    }
}