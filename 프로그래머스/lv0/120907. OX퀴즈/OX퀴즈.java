class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        for(int i=0; i<quiz.length; i++) {
            String[] str = quiz[i].split(" ");
            int num1 = Integer.parseInt(str[0]);
            int num2 = Integer.parseInt(str[2]);
            int result = Integer.parseInt(str[4]);
            int real = str[1].equals("+") ? num1+num2 : num1-num2;
            answer[i] = result == real ?  "O" :  "X";
        }
        return answer;
    }
}