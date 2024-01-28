class Solution {
    /* (C : 파티션 없을 경우 사람이 앉으면 안되는 위치, P : 검사할 사람 기준)
      C
     CCC
    CCPCC
     CCC
      C
    */
    // 맨해튼 거리가 2이하인 방향 
    int[][] dr = {{-2, 0}, {-1, -1}, {-1, 0}, {-1, 1}, {0, -2}, {0, -1}, {0, 1}, {0, 2}, {1, -1}, {1, 0}, {1, 1}, {2, 0}}; 
    String[][] place;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        
        for (int t = 0; t < 5; t++) {
            String[] now = places[t];
            place = new String[5][5];
            
            for (int j = 0;  j < 5; j++) {
                String line = now[j];
                
                for (int i = 0; i < line.length(); i++) {
                    place[j][i] = Character.toString(line.charAt(i));
                }
            }
            
            answer[t] = run(0, 0);
        }
        return answer;
    }
    
    public int run(int r, int c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i][j].equals("P")) {
                    if (checkAround(i, j) == 0) {
                        return 0;
                    }
                }
            }
        }
        
        return 1;
    }
    
    public int checkAround(int r, int c) {
        for (int[] d : dr) {
            int nR = r + d[0];
            int nC = c + d[1];
            
            if (inRange(nR, nC) && place[nR][nC].equals("P")) {
                if (getManhattan(r, c, nR, nC) == 1) { // 응시자가 옆에 딱 붙어있는 경우 
                    return 0;
                } else { // 파티션 체크 필요 
                    if (r == nR && c != nC) { // 같은 행에서 맨해튼 거리가 2인 경우
                        if (c < nC) {
                            if (place[r][c+1].equals("X"))  { // 사이에 파티션 있는 경우
                                continue;
                            } else {
                                return 0;
                            }
                        } else {
                            if (place[r][c-1].equals("X")) { // 사이에 파티션 있는 경우
                                continue;
                            } else {
                                return 0;
                            }
                        }
                    } else if (c == nC && r != nR) { // 같은 열에서 맨해튼 거리가 2인 경우 
                        if (r < nR) {
                            if (place[r+1][c].equals("X")) {
                                continue;
                            } else {
                                return 0;
                            }
                        } else {
                            if (place[r-1][c].equals("X")) {
                                continue;
                            } else {
                                return 0;
                            }
                        }
                    } else { // 대각선에 있는 경우 
                        if (r < nR) {
                            if (c < nC) { // 위치 11
                                if (place[r+1][c].equals("X") && place[r][c+1].equals("X")) {
                                    continue;
                                } else {
                                    return 0;
                                }
                            } else { // 위치 9
                                if (place[r+1][c].equals("X") && place[r][c-1].equals("X")) {
                                    continue;
                                } else {
                                    return 0;
                                }
                            }
                        } else {
                            if (c < nC) { // 위치 4
                                if (place[r-1][c].equals("X") && place[r][c+1].equals("X")) {
                                    continue;
                                } else {
                                    return 0;
                                }
                            } else { // 위치 2
                                if (place[r-1][c].equals("X") && place[r][c-1].equals("X")) {
                                    continue;
                                } else {
                                    return 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }
    
    public int getManhattan(int r, int c, int nR, int nC) {
        return Math.abs(r-nR) + Math.abs(c - nC);
    }

    
    public boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < 5 && c < 5;
    }
}