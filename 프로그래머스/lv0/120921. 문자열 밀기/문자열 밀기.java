class Solution {
    public int solution(String A, String B) {
        String tempB = B.repeat(2);
        return tempB.indexOf(A);
    }
}