class Solution {
    public int solution(int num) {
        int answer = 0;
        long temp = num;
        while(temp != 1) {
            if(temp%2 == 0) temp /=2;
            else temp = temp*3 +1;
            answer++;
            if(answer > 500) {
                answer = -1;
                break;
            }
        }
        return answer;
    }
}