import java.util.*;
import java.io.*;

class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        List<String> partList = getPartArrayAboutP(t, p);
    
        
        for(int i=0; i<partList.size(); i++) {
            if(isSmallerOrEqualThanP(partList.get(i), p)) answer++;
        }
        return answer;
    }
    
    public List<String> getPartArrayAboutP(String t, String p) {
        List<String> partList = new ArrayList<>();
        int lenOfP = p.length();
        
        for(int i=0; i<t.length()-lenOfP+1; i++) {
            partList.add(t.substring(i,i+lenOfP));
        }
        return partList;
    }
    
    public Boolean isSmallerOrEqualThanP(String part, String p) {
        if(Long.parseLong(part) <= Long.parseLong(p)) {
            return true;
        }
        return false;
    }
}