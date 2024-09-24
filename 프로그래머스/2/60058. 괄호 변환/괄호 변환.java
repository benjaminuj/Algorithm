import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        
        // 예외처리
        if (p.isBlank()) {
            return "";
        }
        
        // u를 가장 작은 균형잡힌 괄호 문자열로 분리
        int openCnt = 0;
        int closeCnt = 0;
        int idx = 0;
        while (true) {
            char c = p.charAt(idx);
            if (c == '(') openCnt++;
            if (c == ')') closeCnt++;
            
            if (openCnt == closeCnt) break;
            idx++;
        }
        
        // u와 v로 나누기
        String u = p.substring(0, idx+1);
        String v = p.substring(idx+1);
        
        
        // 올바른 괄호 문자열인지 확인
        boolean isRight = checkRight(u);
        StringBuilder sb = new StringBuilder();
        if (isRight) {
            
            sb.append(u);
        
            // v에 대해 다시 수행
            if (!v.isBlank()) {
                sb.append(solution(v));
            }
        }
        if (!isRight) {
            
            sb.append("(");
            if (!v.isBlank()) {
                sb.append(solution(v)); // v에 대해 재귀적으로 수행한 결과 붙이기
            }
            sb.append(")");
            // u의 첫번째 문자와 마지막 문자를 제와한 나머지 문자
            String uTemp = u.substring(1, u.length()-1);
            StringBuilder temp = new StringBuilder();
            for (int i = 0 ; i < uTemp.length(); i++) {
                if (uTemp.charAt(i) == '(') {
                    temp.append(")");
                } else {
                    temp.append("(");
                }
            }
            sb.append(temp);
        }
        
        return sb.toString();
    }
    
    public boolean checkRight(String u) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        stack.push(u.charAt(0));
        int idx = 0;
        
        while (!stack.isEmpty()) {
            idx++;
            if (idx == u.length()) break;
            char next = u.charAt(idx);
            char before = stack.peekLast();
            
            if (next == ')' && before == '(') {
                stack.pop();
                continue;
            }
            
            stack.push(next);
        }
        
        if (!stack.isEmpty()) return false;
        return true;
        
    }
}