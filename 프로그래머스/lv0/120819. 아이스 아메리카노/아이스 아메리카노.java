class Solution {
    public int[] solution(int money) {
        int[] answer = new int[2];
        int cup = money /5500;
        int remainder = money - (5500*cup);
        answer[0] = cup;
        answer[1] = remainder;
        return answer;
    }
}