import java.util.*;

class Solution {
    private static int len = 5;
    private String[] alpha = {"A", "E", "I", "O", "U"};
    private int num = -1;
    private String word = "";
    private boolean found = false;
    
    public int solution(String word) {
        this.word = word;
        
        backtracking(new StringBuilder());
        
        return num;
    }
    
    public void backtracking(StringBuilder str) {
        num++;
        if (word.contentEquals(str)) {
            found = true;
            return;
        }
        
        if (str.length() == 5) return;
            
        for (int i = 0; i < alpha.length; i++) {
            str.append(alpha[i]);
            if (!found) backtracking(str);
            str.deleteCharAt(str.length()-1);
        }
    }
}