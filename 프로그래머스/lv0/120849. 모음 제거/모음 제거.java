class Solution {
    public String solution(String my_string) {
        String answer = "";
        for(int i=0; i<my_string.length(); i++) {
            char alphabet = my_string.charAt(i);
            if(alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u') continue;
            answer += alphabet;
        }
        return answer;
    }
}