class Solution {
    public String solution(String my_string, String letter) {
        String answer = "";
        char let = letter.charAt(0);
        for(int i=0; i<my_string.length(); i++) {
            char A = my_string.charAt(i);
            if(A != let) {
                answer += A;
            }
        }
        return answer;
    }
}