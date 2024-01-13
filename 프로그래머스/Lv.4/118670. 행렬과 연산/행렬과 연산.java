import java.util.*;

class Solution {
    Deque<Integer> leftColumn = new LinkedList<>();
    Deque<Integer> rightColumn = new LinkedList<>();
    Deque<Deque<Integer>> rows = new LinkedList<>();
    boolean beRows = true;
    public int[][] solution(int[][] rc, String[] operations) {
        int[][] answer = new int[rc.length][rc[0].length];

        // 초기화
        for (int row = 0; row < rc.length; row++) {
            leftColumn.add(rc[row][0]);
            rightColumn.add(rc[row][rc[0].length-1]);
            
            if (rc.length == 2 && rc[0].length == 2) {
                beRows = false;
                continue;
            }
            
            Deque<Integer> q = new LinkedList<>();
            for (int c = 1; c < rc[0].length-1; c++) {
                q.add(rc[row][c]);
            }
            rows.add(q);
        }
        
        for (int i = 0; i < operations.length; i++) {
            switch(operations[i]) {
                case "Rotate" :
                    setRotate();
                    break;
                case "ShiftRow" :
                    setShiftRow();
                    break;
            }
        }
        for (int i = 0; i < rc.length; i++) {
            answer[i][0] = leftColumn.poll();
            answer[i][rc[0].length-1] = rightColumn.poll();

            if (beRows) {
                for (int j = 1; j < rc[0].length-1; j++) {
                    answer[i][j] = rows.peek().poll();
                }
                rows.poll();
            }
        }
        return answer;
    }
    
    public void setRotate() {
        rows.peekFirst().offerFirst(leftColumn.pollFirst()); // n*m 행렬일 경우, 0행 0의 값 0행 1열로 이동
        rightColumn.offerFirst(rows.peekFirst().pollLast()); // n*m 행렬일 경우, 0행 m-2의 값 0행 m-1열로 이동
        rows.peekLast().offerLast(rightColumn.pollLast()); // n*m 행렬일 경우, n-1행 m-1의 값 0행 m-2열로 이동
        leftColumn.offerLast(rows.peekLast().pollFirst()); // n*m 행렬일 경우, n-1행 1의 값 n-1행 0열로 이동
//         if (!beRows) {
//             rightColumn.offerFirst(leftColumn.pollFirst());
//             leftColumn.offerLast(rightColumn.pollLast());
//         } else {
            
            
            
            
//         }
    }
    
    public void setShiftRow() {
        rightColumn.offerFirst(rightColumn.pollLast());
        leftColumn.offerFirst(leftColumn.pollLast());
        if (beRows) { 
            rows.offerFirst(rows.pollLast());
        }
    }
}