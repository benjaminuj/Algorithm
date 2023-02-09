class Solution {
    public int solution(String str1, String str2) {
        int answer = 2;
        for(int i=0; i<=str1.length() - str2.length(); i++) {
            String temp = str1.substring(i, i+str2.length());
            if(temp.equals(str2)) {
                answer = 1;
                break;
            }
        }
        return answer;
    }
}