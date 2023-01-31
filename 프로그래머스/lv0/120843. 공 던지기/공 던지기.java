class Solution {
    public int solution(int[] numbers, int k) {
        int answer = 1;
        for(int i=0; i<k-1; i++) {
            answer += 2;
            answer %= numbers.length;
        }
        if(answer == 0) answer = numbers.length;
        return answer;
    }
}