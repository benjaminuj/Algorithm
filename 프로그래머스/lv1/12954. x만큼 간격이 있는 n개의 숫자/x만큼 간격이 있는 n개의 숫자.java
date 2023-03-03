class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        int index = 0;
        long temp =x;
        while(index <n) {
            answer[index] = temp;
            temp += x;
            index++;    
        }
        return answer;
    }
}