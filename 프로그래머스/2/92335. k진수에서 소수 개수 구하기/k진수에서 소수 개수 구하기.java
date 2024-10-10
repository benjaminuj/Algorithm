import java.util.*;

class Solution {
    Set<String> nums = new HashSet<>();
    
    public int solution(int n, int k) {
        int answer = 0;
        
        // k 진수로 변환
        String number = transOfK(n, k);
        // 연속되는0을 기준으로 문자열 분리
        String[] arr = number.split("0+"); // *로 하면 묶음 0을 기준으로 값이 다 하나씩 분리됨. 그리고 0이 있는 자리는 빈 배열값이 들어감
        
        // 가능한 모든 수 조합 구하기 
        for (int i = 0; i < arr.length; i++) {
//             nums.clear();
//             combination(arr[i], 0, new StringBuilder());    
            
//             Iterator iter = nums.iterator();
//             while(iter.hasNext()) {
//                 // System.out.println("num: " + nums.get(i));
//                 Long num = Long.parseLong(iter.next().toString());
//                 if (checkDecimal(num)) {
//                     System.out.println(num);
//                     answer++;
//                 }
//             }   
            if (checkDecimal(Long.parseLong(arr[i]))) {
                    answer++;
            }
        }

        return answer;
    }
    
    public boolean checkDecimal(Long num) {
        if (num == 1) return false;
        
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num%i == 0) return false;
        }
        return true;
    }
    
    public void combination(String str, int idx, StringBuilder sb) {
        if (sb.length() > 0) nums.add(new String(sb));
        
        for (int i = idx; i < str.length(); i++) {
            sb.append(str.charAt(i));
            combination(str, i+1, sb);
            sb.deleteCharAt(sb.length()-1);
            
            if (idx != 0) return;
        }
    }
    
    public String transOfK(int n, int k) {
        Deque<Integer> stack = new ArrayDeque<>(); 
        
        while (n > 0) {
            stack.push(n%k);
            n/=k;
        }
        
        StringBuilder sb = new StringBuilder();
        int len = stack.size();
        for (int i = 0; i < len; i++) {
            sb.append(stack.pop());
        }
        return sb.toString();
        
    }
}