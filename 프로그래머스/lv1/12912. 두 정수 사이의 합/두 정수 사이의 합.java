class Solution {

    public long solution(int a, int b) {
        return sumAtoB(Math.min(a, b), Math.max(a, b));
    }

    private long sumAtoB(long a, long b) {
        return (b - a + 1) * (a + b) / 2;
    }
}