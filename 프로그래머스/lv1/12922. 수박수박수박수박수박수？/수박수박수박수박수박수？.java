class Solution {
    public String solution(int n) {
        String answer = "";
        String temp = "수박";
        if(n%2 == 0) answer = temp.repeat(n/2);
        else {
            answer = temp.repeat(n/2);
            answer += '수';
        }
        return answer;
    }
}