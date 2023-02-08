class Solution {
    public int solution(int num, int k) {
        String str = String.valueOf(num);
        int answer = str.indexOf(String.valueOf(k));
        if(answer != -1) answer++;
        return answer;
    }
}