class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = {0,0};
        int bottom = denom1 * denom2;
        int top = numer1*denom2 + numer2*denom1;
        int gcd = gcd(bottom, top);
        bottom /= gcd;
        top /= gcd;
        answer[0] = top; 
        answer[1] = bottom;
        return answer;
    }
    
    public int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b,a%b);
    }
}