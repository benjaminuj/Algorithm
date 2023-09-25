import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for(char c : arr) {
            if(c == '(') {
                stack.push('(');
            }
            else {
                if(stack.size() == 0) {
                    answer = false;
                    break;
                }
                stack.pop();
            }
        }
        if(stack.size() != 0) answer = false;

        return answer;
    }
}