import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        int len = prices.length-1;
        
        Deque<Info> stack = new ArrayDeque<>();
        for (int i = 0; i <= len; i++) {
            while (!stack.isEmpty() && stack.peek().cost > prices[i]) {
                answer[stack.peek().idx] = i - stack.peek().idx;
                stack.pop();
            }
            
            stack.push(new Info(prices[i], i));
        }
        
        
        while (!stack.isEmpty()) {
            answer[stack.peek().idx] = len - stack.peek().idx;
            stack.pop();
        }

        return answer;
    }
    
    class Info {
        int cost;
        int idx;
        
        public Info(int cost, int idx) {
            this.cost = cost;
            this.idx = idx;
        }
    }
}