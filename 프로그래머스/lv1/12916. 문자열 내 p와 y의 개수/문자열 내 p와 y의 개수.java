class Solution {
    boolean solution(String s) {
        boolean answer = true;
        String sCopy = s;
        int pBefore = s.length();
        s= s.replaceAll("P","");
        s =s.replaceAll("p","");
        int pAfter = s.length();
        int yBefore = sCopy.length();
        sCopy = sCopy.replaceAll("Y","");
        sCopy = sCopy.replaceAll("y","");
        int yAfter = sCopy.length();
        if( pBefore - pAfter != yBefore - yAfter) answer = false;
        return answer;
        
    }
}