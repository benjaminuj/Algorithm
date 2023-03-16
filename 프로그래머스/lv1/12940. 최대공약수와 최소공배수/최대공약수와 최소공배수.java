class Solution {
    public int[] solution(int n, int m) {
        int gcd = euclid(Math.max(n,m), Math.min(n,m));
        int lcm = n*m/gcd;
        int[] answer = {gcd,lcm};
        return answer;
    }
    
    public int euclid(int x, int y) {
        int r = x%y;
        if(r== 0) {
            return y;
        } else {
            return euclid(y,r);
        }
    }
}