class Solution {
    public String solution(String s, int n) {
        String answer = "";
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == ' ') answer += " ";
            else {
                int temp = (int)s.charAt(i) +n;
                if((temp >90 && Character.isUpperCase(s.charAt(i))) || (temp >122 && Character.isLowerCase(s.charAt(i)))) temp -= 26;
                answer += (char)temp;
            }  
        }
        return answer;
    }
}