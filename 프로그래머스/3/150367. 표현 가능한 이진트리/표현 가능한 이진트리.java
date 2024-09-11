import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            if (binary.equals("1")) {
                answer[i] = 1;
                continue;
            }
            if (binary.equals("0")) {
                answer[i] = 0;
                continue;
            }
            
            binary = getFullBinary(binary);
    
            
            boolean valid = check(binary);  
            
            if (valid) {
                answer[i] = 1;
            } 
            if(!valid) {
                answer[i] = 0;
            }
        }
        return answer;
    }
    
    private String getFullBinary(String binary) {
        int level = 0;
        int completionCnt = 0;

        while (binary.length() > completionCnt) {
            completionCnt += Math.pow(2, level++);
        }
        
        int offset = completionCnt - binary.length();
        return "0".repeat(offset) + binary;
    }
    
    private boolean check(String tree) {
        int len = tree.length();    
        int root = len/2;
        
        if (root == 0) return true;
        String leftSubTree = tree.substring(0, root);
        
        if (root + 1 >= len) return true; 
        String rightSubTree = tree.substring(root+1);
        
        if (tree.charAt(root) == '0') {
            return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
        }
        
        return check(leftSubTree) && check(rightSubTree);
    }
    
    private boolean isZeroTree(String tree) {
        int len = tree.length();    
        int root = len/2;
        
        if (tree.charAt(root) == '1') return false;
        
        if (root == 0) return true;
        String leftSubTree = tree.substring(0, root);
        
        if (root + 1 >= len) return true; 
        String rightSubTree = tree.substring(root+1);
        
        return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
    }
}