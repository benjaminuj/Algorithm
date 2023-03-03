class Solution {
    public int solution(String s) {
        int answer = 0;
        if(s.charAt(0) == '-') {
            s = s.substring(1,s.length());
            answer = -Integer.parseInt(s);
        }
        else answer= Integer.parseInt(s);
        return answer;
    }
}