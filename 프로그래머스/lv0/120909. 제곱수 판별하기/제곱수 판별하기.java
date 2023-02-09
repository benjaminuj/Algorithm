class Solution {
    public int solution(int n) {
        int root = (int)Math.sqrt(n);
        return  root*root == n ? 1 : 2 ;
    }
}