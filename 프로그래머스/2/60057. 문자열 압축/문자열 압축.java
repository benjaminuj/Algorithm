import java.util.*;

class Solution {
    Map<String, Integer> m;
        
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        m = new HashMap<>();
        int bundle = 1;

        while (bundle <= s.length()) {
            int idx = 0;
            String before = s.substring(0, 0 + bundle);
            int len = 0;
            while(idx < s.length()) {
                int end = idx+bundle;
                
                if (end > s.length()-1) end = s.length();
                
                String temp = s.substring(idx, end);
                if (!temp.equals(before) && m.containsKey(temp)) {
                    int t = m.get(temp);
                    len += temp.length();
                    if (t != 1) {
                        len++;
                    }
                    m.remove(temp);
                }
                m.putIfAbsent(temp, 0);
                m.put(temp, m.get(temp)+1);

                idx = idx+bundle;
                before = temp;
            }
            len += getLen();

            answer = answer > len ? len : answer;   

            bundle++;
            m.clear();
        }
        
        return answer;
    }
    
    int getLen() {
        int result = 0;
        
        for (String key : m.keySet()) {
            int strLen = m.get(key);
            result += key.length();
            if (strLen != 1) {
                while(strLen >0) {
                    strLen /= 10;
                    result++;
                }
                    
            }
        }
        return result;
    }
}