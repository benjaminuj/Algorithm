class Solution {
    public String solution(String my_string) {
        String answer = "";
        char[] charArr = my_string.toCharArray();
        for(int i=0; i<charArr.length; i++) {
            if(answer.indexOf(charArr[i]) == -1){
                answer += charArr[i];
            }
        }
        return answer;
    }
}