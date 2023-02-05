class Solution {
    public int solution(int angle) {
        int answer = angle/45;
        if(angle<45) answer =1;
        if(angle < 135 && angle >90) answer = 3;
        return answer;
    }
}