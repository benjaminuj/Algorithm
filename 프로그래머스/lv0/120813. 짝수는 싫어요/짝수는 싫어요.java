class Solution {
    public int[] solution(int n) {
        int[] answer = {};
        answer = new int [(n+1)/2];
        int num = 1;
        for(int i=0; i<(n+1)/2; i++) {
            answer[i] = num;
            num += 2;
        }
        return answer;
    }
}