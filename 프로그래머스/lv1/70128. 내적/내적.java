class Solution {
    public int solution(int[] a, int[] b) {
        int answer = 1234567890;
        long sum= 0;
        for(int i=0; i<a.length; i++) {
            sum += a[i]*b[i];
        }
        answer = (int)sum;
        return answer;
    }
}