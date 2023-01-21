import java.util.Arrays;

class Solution {
    public String solution(String letter) {
        String answer = "";
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        String[] inputLetter = letter.split(" ");
        for(int i=0; i<inputLetter.length; i++) {
            int letterIndex = Arrays.asList(morse).indexOf(inputLetter[i]);
            char alpha = (char)(letterIndex + 97);
            answer += alpha;
        }
        return answer;
    }
}