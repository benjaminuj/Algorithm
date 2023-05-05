class Solution {
    static int min;
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] number = new int[rows][columns];
        int num = 1;
        int answerIndex = 0;
        int[][] direction = {{1,0}, {0,1}, {-1,0}, {0,-1}};
        for(int i =0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                number[i][j] = num++;
            }
        }
        for(int i=0; i<queries.length; i++) {
            int sRow = queries[i][0]-1;
            int sColumn = queries[i][1]-1;
            int eRow = queries[i][2]-1;
            int eColumn = queries[i][3]-1;
            
            int min = number[sRow][sColumn];
            
            int totalRow  = eRow-sRow;
            int totalColumn = eColumn - sColumn;
            int[] movecnt = {totalRow,totalColumn,totalRow,totalColumn-1};
            
            for(int j=0; j<direction.length; j++) {
                int moves = movecnt[j];
                int[] direc = direction[j];
                
                for(int k =0; k<moves; k++) {
                    int nextRow = sRow + direc[0];
                    int nextColumn = sColumn + direc[1];
                    min = getMin(min, number[nextRow][nextColumn]); // 순서 중요! 선
                    swap(number,sRow, sColumn,nextRow, nextColumn); //후
                    sRow = nextRow;
                    sColumn = nextColumn;
                }
            }
            answer[answerIndex++] = min;
        }
        return answer;
    }
    
    public void swap(int[][] number,int sRow, int sColumn, int nextRow, int nextColumn) {
        int temp=number[sRow][sColumn];
        number[sRow][sColumn] = number[nextRow][nextColumn];
        number[nextRow][nextColumn]= temp;
    }
    
    public int getMin(int a, int b) {
        return Math.min(a,b);
    }
}