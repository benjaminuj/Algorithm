class Solution {
    public String[] solution(String my_str, int n) {
        int remainder = 0;
        if(my_str.length()%n != 0) remainder = 1;
        String[] answer = new String[my_str.length()/n + remainder];
        int start = 0;
        int end = n;
        for(int i=0; i<answer.length; i++) {
            answer[i] = my_str.substring(start,end);
            start = end;
            end += n;
            if(end>=my_str.length()) end = my_str.length();
        }
        return answer;
    }
}