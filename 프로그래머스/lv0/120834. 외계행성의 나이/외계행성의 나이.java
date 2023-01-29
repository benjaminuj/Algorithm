class Solution {
    public String solution(int age) {
        String answer = "";
        while(age>0) {
             char alphabet = (char)(97 + age%10);
             age /= 10;
             answer = alphabet + answer;
        }
        return answer;
    }
}