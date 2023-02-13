class Solution {
    public int solution(String my_string) {
        int answer = 0;
        String number = "";
        boolean check = false;
        for(int i=0; i<my_string.length(); i++) {
            char ch = my_string.charAt(i);
            while(!Character.isLetter(ch)) {
                number += ch;
                check = true;
                if(i+1 < my_string.length())ch = my_string.charAt(++i);
                else break;
            }
            if(check) answer += Integer.parseInt(number);
            check = false;
            number = "";
        }
        return answer;
    }
}