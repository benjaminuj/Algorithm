import java.util.*;

class Solution {
    int n, m;
    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        
        // key 인덱스 지정 
        for (int offsetRow = m-1; offsetRow > -n; offsetRow--) {
            for (int offsetCol = m-1; offsetCol > -n; offsetCol--) {
                if (matchKey(offsetCol, offsetRow, key, lock)) return true;
            }
        }
        
        return false;
    }
    
    boolean matchKey(int offsetCol, int offsetRow, int[][] key, int[][] lock) {
        // 회전
        for (int rot = 0; rot < 4; rot++) {
            boolean match = true;
            // lock 위치 기준
            outer:
            for (int r =0; r < n ; r++) {
                for (int c = 0; c < n; c++) {
                    int keyValue = 0;
                    int keyCol = c + offsetCol;
                    int keyRow = r + offsetRow;
                    
                    if (keyCol >= 0 && keyCol < m && keyRow >= 0 && keyRow < m) {
                        keyValue = getKeyValue(key, keyCol, keyRow, rot);
                    }
                    
                    if ((lock[r][c] == 1 && keyValue == 1) || lock[r][c] == 0 && keyValue == 0) {
                        match = false;
                        break outer;
                    }
                }
            }
            if (match) return true;
        }
        return false;
    }
    
    int getKeyValue(int[][] key, int c, int r, int rotation) {
        switch (rotation) {
            case 0 : return key[r][c];
            case 1 : return key[c][m-1-r];
            case 2: return key[m-1-r][m-1-c];
            case 3 :return key[m-1-c][r];
        }
        return -1;
    }
}