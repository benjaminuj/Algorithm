class Solution {
    public int solution(int[][] sizes) {
        int widthMax = 0;
        int heightMax = 0;
        for(int i=0; i<sizes.length; i++) {
            int width = Math.max(sizes[i][0], sizes[i][1]);
            int height = Math.min(sizes[i][0], sizes[i][1]);
            sizes[i][0] = width;
            sizes[i][1] = height;
            if(widthMax < sizes[i][0]) widthMax = sizes[i][0];
            if(heightMax < sizes[i][1] ) heightMax = sizes[i][1];
        }
        int answer= widthMax*heightMax;
        return answer;
    }
}