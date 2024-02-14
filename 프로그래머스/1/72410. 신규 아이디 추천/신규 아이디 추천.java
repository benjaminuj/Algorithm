import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        String temp = "";
        StringBuilder sb = new StringBuilder();
        Set<Character> can = new HashSet<>();
        can.add('-');
        can.add('_');
        can.add('.');
        for (int i =0 ; i< 26; i++) {
            can.add((char)('a' + i));
        }
        for( int i =0 ; i <10;i++ ) {
            can.add((char)(i+'0'));
        }

        
        // 1단계
        new_id = new_id.toLowerCase();
        
        // 2단계
        for (int i =0; i < new_id.length(); i++) {
            char c  = new_id.charAt(i);
            if(can.contains(c)) {
               temp += Character.toString(c); 
            }
        }
        new_id = new String(temp);

        // 3단계
        Deque<String> q = new ArrayDeque<>();
        q.add(Character.toString(new_id.charAt(0)));

        for (int i =1; i < new_id.length(); i++) {
            if (new_id.charAt(i)=='.' && q.peekLast().equals(".")) continue;
            q.add(Character.toString(new_id.charAt(i)));
        }
        
        while(!q.isEmpty()) {
            sb.append(q.poll());
        }
        
        // 4단계
        if (sb.length() != 0) {
            if (sb.indexOf(".") == 0) sb.deleteCharAt(0);
            if (sb.length() != 0) {
                if (sb.lastIndexOf(".") == sb.length()-1) sb.deleteCharAt(sb.length()-1);
            }
        }
        
        //5단계
        if (sb.length() == 0) sb.append("a");

        //6단계
        if (sb.length() >= 16) {
            sb.delete(15, sb.length());
            if (sb.lastIndexOf(".") == sb.length()-1) {
                sb.deleteCharAt(sb.length()-1);
            }
        }
        
        // 7단계
        String te = sb.toString();
        String ne = Character.toString(te.charAt(te.length()-1));
        
        while (sb.length() < 3) {
            sb.append(ne);
        }
        return sb.toString();
    }
}