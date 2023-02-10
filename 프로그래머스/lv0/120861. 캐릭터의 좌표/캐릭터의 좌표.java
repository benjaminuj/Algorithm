class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int x = 0;
        int y = 0;
        for(int i=0; i<keyinput.length; i++) {
            if(x > board[0]/2) x = board[0]/2;
            if(x < -(board[0]/2)) x = -(board[0]/2);
            if(y > board[1]/2) y = board[1]/2;
            if(y < -(board[1]/2)) y = -(board[1]/2);
            switch(keyinput[i]) {
                case "left" : x--; break;
                case "right" : x++; break;
                case "up" : y++; break;
                case "down" : y--; break;
            }
        }
        if(x > board[0]/2) x = board[0]/2;
        if(x < -(board[0]/2)) x = -(board[0]/2);
        if(y > board[1]/2) y = board[1]/2;
        if(y < -(board[1]/2)) y = -(board[1]/2);
        int[] answer = {x,y};
        return answer;
    }
}