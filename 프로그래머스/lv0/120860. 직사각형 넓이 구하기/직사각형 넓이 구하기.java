class Solution {
    public int solution(int[][] dots) {
        int zerosSameXCouple = 1;
        int etc = 2;
        if(dots[0][0] == dots[2][0]) {
            zerosSameXCouple = 2;
            etc = 1;
        }
        if(dots[0][0] == dots[3][0]) zerosSameXCouple = 3;
        
        int width = Math.abs(dots[0][0] - dots[etc][0]);
        int height = Math.abs(dots[0][1] - dots[zerosSameXCouple][1]);
        return width*height;
    }
}