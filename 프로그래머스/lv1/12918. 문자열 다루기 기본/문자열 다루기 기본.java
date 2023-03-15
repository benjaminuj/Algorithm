class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        char[] temp = s.toCharArray();
        if(s.length() != 4 && s.length() != 6) return false;
        for(int i=0; i<temp.length; i++) {
            if(Character.isLetter(temp[i])) {
                answer= false;
                break;
            }
        }
        return answer;
    }
}