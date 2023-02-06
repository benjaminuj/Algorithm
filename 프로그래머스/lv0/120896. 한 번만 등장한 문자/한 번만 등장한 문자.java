import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] alpha = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        List<String> list = Arrays.stream(s.split("")).collect(Collectors.toList());
        for(int i=0; i<26;i++) {
            if(Collections.frequency(list,alpha[i])==1) answer += alpha[i];
        }
        return answer;
    }
}