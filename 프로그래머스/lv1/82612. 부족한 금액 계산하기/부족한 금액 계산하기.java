class Solution {
    public long solution(int price, int money, int count) {
        long answer = money;
        for(int i=1; i<=count; i++) {
            answer -= price * i;
        }
        answer = answer<0 ? answer*(-1) : 0;
        return answer;
    }
}