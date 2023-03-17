import java.util.Arrays;
class Solution {
    public String solution(String s) {
        String answer = "";
        String[] temp = s.split(" ");
        for(int j=0; j<temp.length; j++) {
            for(int i=0; i<temp[j].length(); i++) {
                if(i%2==0) answer += Character.toUpperCase(temp[j].charAt(i));
                else answer += Character.toLowerCase(temp[j].charAt(i));
            }
            temp[j] = answer;
            answer ="";
        }
        int start = 0;
        int count =0;
        for(int i=0; i<temp.length; i++) {
            int len = temp[i].length();
            start += len+count;
            count =0;
            for(int j=start; j<s.length(); j++) {
                if(!Character.isLetter(s.charAt(j))) count++;
                if(Character.isLetter(s.charAt(j))) break;
            }
            answer += temp[i];
            for(int k=0; k<count; k++) answer += " ";
        }
        return answer;
    }
}