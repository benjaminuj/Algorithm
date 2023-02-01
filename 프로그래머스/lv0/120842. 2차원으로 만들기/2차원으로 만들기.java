class Solution {
    public int[][] solution(int[] num_list, int n) {
        int[][] answer = new int[num_list.length/n][n];
        int row = 0;
        for(int i=0; i<=num_list.length-n; i += n) {
            for(int j=0; j<n; j++) {
                answer[row][j] = num_list[j+i];
            }
            row++;
        }
        return answer;
    }
}