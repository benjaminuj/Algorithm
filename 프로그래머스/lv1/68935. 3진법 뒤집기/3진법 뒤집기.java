class Solution {
    public int solution(int n) {
        int answer = 0;
        String temp="";
        while(n/3>=1) {
            temp += n%3;
            n /= 3;
        }
        temp += n;
        answer = Integer.parseInt(temp,3);
        return answer;
    }
}