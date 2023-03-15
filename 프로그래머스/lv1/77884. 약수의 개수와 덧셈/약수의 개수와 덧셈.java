class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for(int i=left; i<= right; i++) {
            if(getDivisorCount(i) %2 ==0) answer += i; 
            else answer -= i;
        }
        return answer;
    }
    
    int getDivisorCount(int n) {
        int answer = 0;
        for(int i=1; i<=Math.sqrt(n); i++) {
            if(n %i ==0) answer += 2;
            if(Math.pow(i,2) == n) --answer;
        }
        return answer;
    }
}