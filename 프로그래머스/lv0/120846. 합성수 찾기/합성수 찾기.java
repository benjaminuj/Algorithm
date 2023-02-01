class Solution {
static int[] A;
    public int solution(int n) {
        int answer = 0;
        prime(n);
        for(int i=2; i<A.length; i++) {
            if(A[i] == 0) answer++;
        }
        return answer;
    }
    public void prime(int m) {
        A = new int[m+1];
        for(int i=2; i<A.length; i++) {
            A[i] = i;
        }
        for(int i=2; i<=Math.sqrt(m); i++) {
            for(int j=2; j <= m/i; j++) {
                A[i*j] = 0;
            }
        }
    }
}