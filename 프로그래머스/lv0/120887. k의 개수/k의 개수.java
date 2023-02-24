class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        for(int p=i; p<=j; p++) {
            int num = p;
            while(num>0) {
                if(k == num%10) answer++;
                num/=10;
            }
        }
        return answer;
    }
}