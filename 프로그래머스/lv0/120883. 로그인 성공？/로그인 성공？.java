class Solution {
    public String solution(String[] id_pw, String[][] db) {
        String answer = "fail";
        boolean id, pw = false;
        for(int i=0; i<db.length; i++) {
            if(id_pw[0].equals(db[i][0])) {
                id = true;
                answer = "wrong pw";
                if(id_pw[1].equals(db[i][1])) {
                    pw = true;
                    answer = "login";
                    break;
                }
            }
        }
        return answer;
    }
}