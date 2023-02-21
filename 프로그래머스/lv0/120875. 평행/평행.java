class Solution {
    public int solution(int[][] dots) {
        int x1 = dots[3][0];
        int y1 = dots[3][1];
        for (int i = 0; i < 3; i++) {
            int x2 = dots[i][0];
            int y2 = dots[i][1];
            int x3 = dots[(i+1)%3][0];
            int y3 = dots[(i+1)%3][1];
            int x4 = dots[(i+2)%3][0];
            int y4 = dots[(i+2)%3][1];
            if((y2-y1)/(double)(x2-x1)==(y4-y3)/(double)(x4-x3)){
                return 1;
            }
        }
        return 0;
    }
}
