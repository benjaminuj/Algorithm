class Solution {
    public int solution(int n, int[] tops) {
        long[] a = new long[n];
        long[] b = new long[n];
        
        if (tops[0] == 1) {
            a[0] = 3;
        } else {
            a[0] = 2;
        }
        b[0] = 1;
        
        for (int i = 1; i < n ; i++) {
            if (tops[i] == 1) {
                a[i] = (a[i-1]*3 + b[i-1]*2) % 10007;
                b[i] = (b[i-1] + a[i-1]) % 10007;
            }
            if (tops[i] == 0) {
                a[i] = (a[i-1]*2 + b[i-1]) % 10007;
                b[i] = (b[i-1] + a[i-1]) % 10007;
            }
        }
        
        return (int)((a[n-1] + b[n-1])%10007);
    }
}