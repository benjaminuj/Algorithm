class Solution {
    public int solution(int balls, int share) {
        int answer = 1;
        share = Math.min(balls - share, share);
        long result = 1L;
        for(int i=1; i<= share; i++) {
            result *= balls;
            result /= i;
            balls--;
        }
        answer = (int)result;
        return answer;
    }
}