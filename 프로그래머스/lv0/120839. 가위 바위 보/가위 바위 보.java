class Solution {
    public String solution(String rsp) {
        String answer = "";
        for(int i=0; i<rsp.length(); i++) {
            char check = rsp.charAt(i);
            if(check == '2') answer += '0';
            else if(check == '0') answer += '5';
            else answer += '2';
        }
        return answer;
    }
}