class Solution {
    public String solution(String my_string) {
        String answer="";
        int[] alpha = new int[26];
        my_string = my_string.toLowerCase();
        for(int i=0; i<my_string.length(); i++) {
            alpha[my_string.charAt(i)-97]++;
        }
        for(int i=0; i<26; i++) {
            while(alpha[i]>0) {
                answer += (char)(97+i);
                alpha[i]--;
            }
        }
        return answer;
    }
}