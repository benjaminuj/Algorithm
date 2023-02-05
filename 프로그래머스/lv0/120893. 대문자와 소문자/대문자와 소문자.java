class Solution {
    public String solution(String my_string) {
        String answer = "";
        for(int i=0; i<my_string.length(); i++) {
            char alphabet = my_string.charAt(i);
            if(Character.isUpperCase(alphabet)) {
                answer+=Character.toLowerCase(alphabet);
            }else {
                answer+=Character.toUpperCase(alphabet);
            }
        }
        return answer;
    }
}