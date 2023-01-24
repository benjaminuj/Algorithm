class Solution {
    public int solution(int n) {
        int answer = 0;
        int gcdValue = gcd(n,6);
        answer = n*6/gcdValue;
        answer /= 6;
        return answer;
    }
    public int gcd(int a, int b) {
        if(b ==0) return a;
        return gcd(b,a%b);
    }
}