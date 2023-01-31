class Solution {
    public long solution(int balls, int share) {
        long answer = 1;
        for(int i=1; i<= share; i++) {
            answer *= balls;
            answer /= i;
            balls--;
        }
        return answer;
    }
}