import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        Deque<int[]> stack = new ArrayDeque<>(); //{초, 주식가격}
        
        for (int i = 0; i < prices.length; i++) {
            // 가격 떨어짐
            while (!stack.isEmpty() && stack.peek()[1] > prices[i]) {
                int[] info = stack.pop();
                answer[info[0]-1] = i+1 - info[0];
            }
            
            stack.push(new int[]{i+1, prices[i]});
        }
        
        while(!stack.isEmpty()) {
            int second = prices.length;
            int[] info = stack.pop();
            answer[info[0]-1] = second - info[0];
        }
        
        return answer;
    }
}